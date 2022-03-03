package task.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import task.bookstore.dto.BookDto;
import task.bookstore.model.Book;
import task.bookstore.model.User;
import task.bookstore.model.enums.UserType;
import task.bookstore.repositories.BookRepository;
import task.bookstore.repositories.UserRepository;
import task.bookstore.security.CurrentUser;

import java.util.List;

@Controller
public class AuthController {


    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    @Autowired
    public AuthController(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }


    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Book> listBooks = bookRepository.findAll();
        User user = userRepository.findOneByEmail(currentUser.getUsername());
        model.addAttribute("currentUser", user);
        model.addAttribute("listBooks", listBooks);
        if (user.getUserType() == UserType.ADMIN) {
            List<User> userList = userRepository.findAll();
            model.addAttribute("userList", userList);
        }
        return "index";
    }

    @GetMapping("/newBook")
    public String newBookPage(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        BookDto book = new BookDto();
        model.addAttribute(book);
        User user = userRepository.findOneByEmail(currentUser.getUsername());
        model.addAttribute("currentUser", user);
        return "newBook";
    }

    @RequestMapping("/editBook/{id}")
    public ModelAndView showEditBookPage(@PathVariable(name = "id") int id, @AuthenticationPrincipal CurrentUser currentUser) {
        ModelAndView mav = new ModelAndView("editBook");
        Book book = bookRepository.getById(id);
        BookDto bookDto = BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .description(book.getDescription())
                .price(book.getPrice())
                .authorName(book.getAuthorName())
                .userId(book.getUser().getId())
                .build();
        User user = userRepository.findOneByEmail(currentUser.getUsername());
        mav.addObject("currentUser", user);
        mav.addObject("book", bookDto);
        return mav;
    }
}
