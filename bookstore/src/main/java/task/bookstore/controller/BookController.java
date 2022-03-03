package task.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import task.bookstore.dto.BookDto;
import task.bookstore.service.BookService;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/all/{id}")
    @ResponseBody
    public List<BookDto> getAllBooksByUserId(@PathVariable(name = "id") int id) {
        return bookService.findAllByUserId(id);
    }

    @PostMapping("/save")
    public String createBook(@ModelAttribute("book") BookDto bookDto) {
        bookService.createBook(bookDto);
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String updateBook(@ModelAttribute("book") BookDto bookDto) {
        bookService.updateBook(bookDto, bookDto.getId());
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable(name = "id") int id) {
        bookService.deleteBookById(id);
        return "redirect:/";
    }
}

