package com.springboot.todolist.todolistapp.todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoSpringJPA extends JpaRepository<ToDoClass, Integer> {


}
