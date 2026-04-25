package org.example.miniproject1.repository;


import org.example.miniproject1.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository  extends JpaRepository<Todo,Long> {
}