package com.mahdi.website.controller;

import com.mahdi.website.dto.AuthorDTO;

import com.mahdi.website.service.interfaces.AuthorServiceInterface;
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
public class AuthorController {

    private final AuthorServiceInterface authorService;

    @GetMapping("/author-management")
    public String showAuthorManagement(Model model) {
        model.addAttribute("authorDTO", new AuthorDTO());
        model.addAttribute("searchPerformed", Boolean.FALSE);
        return "author-management";
    }

    @PostMapping("/author-management/search")
    public String searchAuthors(@ModelAttribute AuthorDTO authorDTO, Model model) {
        List<AuthorDTO> authorDTOList = authorService.searchAuthor(authorDTO);
        if (authorDTOList.isEmpty()) {
            model.addAttribute("message", "No results found.");
            model.addAttribute("searchPerformed", Boolean.FALSE);
        } else {
            model.addAttribute("authorDTOList", authorDTOList);
            model.addAttribute("searchPerformed", Boolean.TRUE);
        }
        return "author-management";
    }

    @GetMapping("/add-author")
    public String showAddAuthorPage(Model model) {
        model.addAttribute("authorDTO", new AuthorDTO());
        return "add-author";
    }

    @PostMapping("/save-author")
    public String saveAuthor(@ModelAttribute AuthorDTO authorDTO, Model model) {
        authorService.saveAuthor(authorDTO);
        model.addAttribute("message", "the author has been successfully saved.");
        model.addAttribute("authorDTO", new AuthorDTO());
        return "author-management";
    }

    @GetMapping("/author-management/deactivate-author/{authorId}")
    public String deactivateAuthor(@PathVariable Long authorId, Model model) {
        authorService.deactivateAuthorById(authorId);
        model.addAttribute("message", "the author has been successfully deactivated.");
        model.addAttribute("authorDTO", new AuthorDTO());
        return "redirect:/author-management";
    }

    @GetMapping("/author-management/update-author/{authorId}")
    public String viewUpdateAuthorPage(@PathVariable Long authorId, Model model) {
        AuthorDTO authorDTO = authorService.findAuthorDTOById(authorId);
        model.addAttribute("authorDTO", authorDTO);
        return "update-author";
    }

    @PostMapping("/author-management/update-author/{authorId}")
    public String updateAuthor(@PathVariable Long authorId, @ModelAttribute AuthorDTO authorDTO, Model model) {
        authorService.updateAuthor(authorId, authorDTO);
        model.addAttribute("message", "the author has been successfully updated.");
        model.addAttribute("authorDTO", new AuthorDTO());
        return "redirect:/author-management";
    }
}
