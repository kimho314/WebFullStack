package com.luna.hellospock.service;

import com.luna.hellospock.entity.Book;
import com.luna.hellospock.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryService {
    private final BookRepository bookRepository;
    private final PushService pushService;

    public LibraryService(BookRepository bookRepository, PushService pushService) {
        this.bookRepository = bookRepository;
        this.pushService = pushService;
    }

    public boolean isBookAvailable(String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        return book.map(Book::isAvailable).orElse(false);
    }

    public Optional<String> borrowBook(String isbn) {
        return bookRepository
                .findByIsbn(isbn)
                .filter(Book::isAvailable)
                .map(
                        book -> {
                            pushService.notification("대출 완료 : " + book.getTitle());
                            return book.getTitle();
                        });
    }

}
