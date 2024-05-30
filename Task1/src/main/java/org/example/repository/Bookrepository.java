package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.exceptions.BadRequestException;
import org.example.exceptions.DataNotFoundException;
import org.example.model.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
@RequiredArgsConstructor
public class Bookrepository {

    private final JdbcTemplate template;

    @Transactional
    public Book post(Book book){
        String sql = String.format(
                """
                        INSERT INTO books (title,author,publicationYear)
                        VALUES ('%s','%s',%s)
                        """, book.getTitle(), book.getAuthor(), book.getPublicationYear()

        );
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            return pst;
        }, keyHolder);
        int id = keyHolder.getKey().intValue();
        return get((long)id);
    }

    @Transactional
    public Book get(Long id){
        String sql = String.format(
                """
                        SELECT *
                        FROM books
                        WHERE id=%s
                        """, id

        );
        return template.query(sql, new BeanPropertyRowMapper<>(Book.class))
                .stream().findFirst().orElseThrow(() -> new DataNotFoundException("Нет книги с данным id= "+id));
    }

    @Transactional
    public Book put(Book book,Long id){
        String sql = String.format(
                """
                        UPDATE books SET title='%s',author='%s',publicationYear=%s
                        WHERE id=%s
                        """, book.getTitle(), book.getAuthor(), book.getPublicationYear(),id

        );
        int answerUpdate=template.update(sql);
        checkUpdate(answerUpdate,id);
        book.setId(id);
        return book;
    }

    @Transactional
    public void delete(Long id){
        String sql = String.format(
                """
                        DELETE FROM books 
                        WHERE id=%s
                        """,id

        );
        int answerUpdate=template.update(sql);
        checkUpdate(answerUpdate,id);
    }

    private void checkUpdate(int answerUpdate,Long id){
        if (answerUpdate == 0) {
            throw new DataNotFoundException("Нет книги с данным id= "+id);
        } else if (answerUpdate != 1) {
            throw new BadRequestException("Ошибка с базой данных, изменение касалось "+answerUpdate+" строк, а не одной");
        }
    }
}
