package task.bookstore.service;

import task.bookstore.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto createBook(BookDto bookDto);

    List<BookDto> findAllByUserId(int id);

    BookDto updateBook(BookDto bookDto, int id);

    void deleteBookById(int id);
}
