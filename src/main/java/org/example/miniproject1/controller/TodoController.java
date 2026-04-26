package org.example.miniproject1.controller;

import jakarta.validation.Valid;
import org.example.miniproject1.dto.TodoDTO;
import org.example.miniproject1.model.Todo;
import org.example.miniproject1.repository.TodoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@Controller
@RequestMapping
public class TodoController {
    private final TodoRepository repo;
    public TodoController(TodoRepository repo) {
        this.repo = repo;
    }
    @GetMapping
    public String Home(Model model){
        model.addAttribute("todos",repo.findAll());
      return "home";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("todo", new TodoDTO());
        return "add";
    }

    @PostMapping("/handleAdd")
    public String handleAdd(@Valid @ModelAttribute("todo") TodoDTO dto,
                            BindingResult result) {

        if (result.hasErrors()) {
            return "add";
        }

        Todo todo = new Todo();
        todo.setContent(dto.getContent());
        todo.setDueDate(dto.getDueDate());
        todo.setStatus(dto.getStatus());
        todo.setPriority(dto.getPriority());


        repo.save(todo);

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Todo> optional = repo.findById(id);

        if (optional.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("todo", optional.get());
        return "edit";
    }

    @PostMapping("/handleEdit")
    public String handleEdit(@RequestParam Long id,
                             @Valid @ModelAttribute("todo") TodoDTO dto,
                             BindingResult result) {

        if (result.hasErrors()) {
            return "edit";
        }

        Todo old = repo.findById(id).orElse(null);
        if (old == null) return "redirect:/";

        old.setContent(dto.getContent());
        old.setStatus(dto.getStatus());
        old.setPriority(dto.getPriority());
        old.setDueDate(dto.getDueDate());

        repo.save(old);

        return "redirect:/";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        if (!repo.existsById(id)) {
            return "redirect:/";
        }

        repo.deleteById(id);
        return "redirect:/";
    }
}
