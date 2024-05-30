package org.example.service;

import org.example.model.Book;

public interface BookService {

    Book post(Book book);

    Book put(Book book,Long id);

    Book get(Long id);

    void delete(Long id);
}
