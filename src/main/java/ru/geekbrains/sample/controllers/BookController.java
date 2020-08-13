package ru.geekbrains.sample.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.geekbrains.sample.services.BookService;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class BookController {

    public final BookService bookService;

    @GetMapping("/books/{id}")
    public String getBookPage(@PathVariable String id, Model model) {
        model.addAttribute("book", bookService.getOne(UUID.fromString(id)));
        return "book";
    }
}
