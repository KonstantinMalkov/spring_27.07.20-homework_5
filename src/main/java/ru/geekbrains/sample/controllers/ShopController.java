package ru.geekbrains.sample.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.sample.services.BookService;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ShopController {

    public final BookService bookService;
    public final int countRows = 3;

    @GetMapping("/")
    public String getIndexPage(Model model) {
        int firstNumber = 0;
        model.addAttribute("books", bookService.getCountBooks(firstNumber, countRows)); // bookService.getAllBooks()
        model.addAttribute("firstNumber", 0);
        return "index";
    }

    @PostMapping("/some/{id}")
    public String getBookPage1(@PathVariable String id, Model model) {
        model.addAttribute("book", bookService.getOne(UUID.fromString(id)));
        return "book";
    }

    @PostMapping("/some2/{id}/{firstNumber}")
    public String getBookPage2(@PathVariable String id, @PathVariable String firstNumber, Model model) {
        String direction = "";
        int countR = 0;
        if(id.equals("123")) { // "back"
            countR = Integer.parseInt(firstNumber) - countRows;
            if(countR < 0){countR = 0;}
            model.addAttribute("books", bookService.getCountBooks(countR, countRows));
        }else if(id.equals("456")){ // "forth"
            countR = Integer.parseInt(firstNumber) + countRows;
            model.addAttribute("books", bookService.getCountBooks(countR, countR + countRows - 1));
        }
        model.addAttribute("firstNumber", countR);
        return "index";
    }

    @GetMapping("/some3")
    public String getBookPage3(@RequestParam(value = "paramName", required = false) String paramName) {
//        model.addAttribute("books", bookService.getCountBooks(0, countRows));// bookService.getAllBooks()
//        model.addAttribute("firstNumber", 0);
        //String paramName = request.getParameter("paramName");
        System.out.println(paramName);
        return "index";
    }

}