package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.BookRequest;
import org.example.dto.BookResponse;
import org.example.mapper.BookMapper;
import org.example.model.Book;
import org.example.service.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    private final BookMapper bookMapper;

    @Operation(summary = "создание")
    @PostMapping
    public BookResponse post(@Valid @RequestBody BookRequest bookRequest) {
        Book book = bookMapper.toBook(bookRequest);
        book=bookService.post(book);
        return bookMapper.toBookResponse(book);
    }

    @Operation(summary = "обновление")
    @PutMapping(value = "/{id}")
    public BookResponse put(@Valid @RequestBody BookRequest bookRequest,@PathVariable Long id) {
        Book book = bookMapper.toBook(bookRequest);
        book=bookService.put(book,id);
        return bookMapper.toBookResponse(book);
    }

    @Operation(summary = "чтение")
    @GetMapping(value = "/{id}")
    public BookResponse get(@PathVariable Long id) {
        Book book=bookService.get(id);
        return bookMapper.toBookResponse(book);
    }

    @Operation(summary = "удаление")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

}
