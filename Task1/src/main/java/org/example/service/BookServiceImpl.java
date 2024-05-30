package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Book;
import org.example.repository.Bookrepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final Bookrepository bookrepository;

    public Book post(Book book){
        return bookrepository.post(book);
    }

    public Book put(Book book,Long id){
        return bookrepository.put(book,id);
    }

    public Book get(Long id){
        return bookrepository.get(id);
    }

    public void delete(Long id){
        bookrepository.delete(id);
    }
}
