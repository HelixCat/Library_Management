package com.mahdi.website.controller.impl;

import com.mahdi.website.dto.BookDTO;

import com.mahdi.website.service.interfaces.BookServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class BookController {

    private final BookServiceInterface bookService;

    @GetMapping("/book-management")
    public String showBookManagement(Model model) {
        model.addAttribute("bookDTO", new BookDTO());
        model.addAttribute("searchPerformed", Boolean.FALSE);
        return "book-management";
    }

    @PostMapping("/book-management/search")
    public String searchBooks(@ModelAttribute BookDTO bookDTO, Model model) {
        List<BookDTO> bookDTOList = bookService.searchBook(bookDTO);
        if (bookDTOList.isEmpty()) {
            model.addAttribute("message", "No results found.");
            model.addAttribute("searchPerformed", Boolean.FALSE);
        } else {
            model.addAttribute("bookDTOList", bookDTOList);
            model.addAttribute("searchPerformed", Boolean.TRUE);
        }
        return "book-management";
    }

    @GetMapping("/add-book")
    public String showAddBookPage(Model model) {
        model.addAttribute("bookDTO", new BookDTO());
        return "add-book";
    }

    @PostMapping("/save-book")
    public String saveBook(@ModelAttribute BookDTO bookDTO, Model model) {
        bookService.saveBook(bookDTO);
        model.addAttribute("message", "the book has been successfully saved.");
        model.addAttribute("bookDTO", new BookDTO());
        return "book-management";
    }

    @GetMapping("/book-management/deactivate-book/{bookId}")
    public String deactivateBook(@PathVariable Long bookId, Model model) {
        bookService.deactivateBookById(bookId);
        model.addAttribute("message", "the book has been successfully deactivated.");
        model.addAttribute("bookDTO", new BookDTO());
        return "redirect:/book-management";
    }

    @GetMapping("/book-management/update-book/{bookId}")
    public String viewUpdateBookPage(@PathVariable Long bookId, Model model) {
        BookDTO bookDTO = bookService.findBookDTOById(bookId);
        model.addAttribute("bookDTO", bookDTO);
        return "update-book";
    }

    @PostMapping("/book-management/update-book/{bookId}")
    public String updateBook(@PathVariable Long bookId, @ModelAttribute BookDTO bookDTO, Model model) {
        bookService.updateBook(bookId, bookDTO);
        model.addAttribute("message", "the book has been successfully updated.");
        model.addAttribute("bookDTO", new BookDTO());
        return "redirect:/book-management";
    }
}
