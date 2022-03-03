package task.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.bookstore.dto.BookDto;
import task.bookstore.exception.BookNotFoundException;
import task.bookstore.model.Book;
import task.bookstore.model.User;
import task.bookstore.repositories.BookRepository;
import task.bookstore.repositories.UserRepository;
import task.bookstore.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }


    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = mapToEntity(bookDto);
        User user = userRepository.getById(bookDto.getUserId());
        book.setUser(user);
        Book newBook = bookRepository.save(book);
        return mapToDTO(newBook);
    }


    @Override
    public List<BookDto> findAllByUserId(int id) {
        List<Book> books = bookRepository.findAllByUserId(id);
        List<BookDto> collect = books.stream().map(this::mapToDTO).collect(Collectors.toList());
        return collect;
    }

    @Override
    public BookDto updateBook(BookDto bookDto, int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book", "id", id));
        book.setTitle(bookDto.getTitle());
        book.setDescription(bookDto.getDescription());
        book.setPrice(bookDto.getPrice());
        book.setAuthorName(bookDto.getAuthorName());
        Book updatedBook = bookRepository.save(book);
        return mapToDTO(updatedBook);
    }

    @Override
    public void deleteBookById(int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book", "id", id));
        bookRepository.delete(book);
    }


    private BookDto mapToDTO(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .authorName(book.getAuthorName())
                .description(book.getDescription())
                .price(book.getPrice())
                .title(book.getTitle())
                .userId(book.getUser().getId()).build();

    }


    private Book mapToEntity(BookDto bookDto) {
        return Book.builder()
                .title(bookDto.getTitle())
                .description(bookDto.getDescription())
                .price(bookDto.getPrice())
                .authorName(bookDto.getAuthorName()).build();

    }
}

