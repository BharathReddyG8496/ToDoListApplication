package com.springboot.todolist.todolistapp.todo;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ToDoService {
    private static List<ToDoClass> todos= new ArrayList<>();
    private static int tcount=0;

    static{
        todos.add(new ToDoClass(++tcount,"Bharath","Complete the homework", LocalDate.now().plusMonths(6l),false));
        todos.add(new ToDoClass(++tcount,"Shashank","Learn dancing", LocalDate.now().plusMonths(3l),false));
        todos.add(new ToDoClass(++tcount,"Mailary","learn Football", LocalDate.now().plusMonths(6l),false));
        todos.add(new ToDoClass(++tcount,"Playboy","Finish project", LocalDate.now().plusDays(25),false));
    }

    public List<ToDoClass> retrieve_todos(){
        return todos;
    }

    public void addNewTodo(String username,String desc, LocalDate date){
        todos.add(new ToDoClass(++tcount,username,desc,date,false));
    }

    public void deleteatodo(int id){
     Predicate<? super ToDoClass> predicate= todo-> todo.getId()==id;
      todos.removeIf(predicate);
    }

    public ToDoClass findTodo(int id){
        Predicate<? super ToDoClass> predicate=toDoClass ->toDoClass.getId()==id;
        return todos.stream().filter(predicate).findFirst().get();
    }
    public void updateTodo(ToDoClass todo){
        Predicate<? super ToDoClass> predicate= todoyay-> todoyay.getId()== todo.getId();
        ToDoClass todo1=todos.stream().filter(predicate).findFirst().get();
        todo1.setUsername(todo.getUsername());
        todo1.setDescription(todo.getDescription());
        todo1.setTargetDate(todo.getTargetDate());
        todo1.setDone(todo.isDone());
    }

    public String ExtractLoggedUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();

    }
    //This method is used to extract the name of the user who has logged in
}
