package ru.geekbrains.sample.services;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import ru.geekbrains.sample.persistence.entities.Book;
import ru.geekbrains.sample.persistence.repositories.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getOne(UUID id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> getCountBooks(int firstNumber, int count) {
        List<Book> listFromBase = bookRepository.findAll();
        List<Book> returnList = new ArrayList<>();

        for (int i = firstNumber; i <= count; i++) {
            if(i <= listFromBase.size()){
                returnList.add(listFromBase.get(i));
            }
        }
        return returnList;
    }

}