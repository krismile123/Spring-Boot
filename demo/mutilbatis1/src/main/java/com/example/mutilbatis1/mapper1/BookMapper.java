package com.example.mutilbatis1.mapper1;

import com.example.mutilbatis1.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface BookMapper {
    List<Book> getAllBooks();
}
