package com.example.mutilbatis1.mapper2;

import com.example.mutilbatis1.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface BookMapper2 {
    List<Book> getAllBooks();
}
