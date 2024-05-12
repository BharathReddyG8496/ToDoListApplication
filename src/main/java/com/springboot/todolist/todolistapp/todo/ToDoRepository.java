package com.springboot.todolist.todolistapp.todo;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ToDoRepository {

    @Autowired
    private ToDoSpringJPA object;


   public List<ToDoClass> getTodos(){
      return object.findAll();
   }
    public void addTodo(ToDoClass toDoClass){
        object.save(toDoClass);
    }
    public ToDoClass FindTodo(int id){
       Optional<ToDoClass> to= object.findById(id);
       if (to.isPresent()){
        return to.get();
        }
else return null;
    }

   public void deleteTodo(int id){
       object.deleteById(id);
   }
   public void updateTodo(ToDoClass toDoClass){
       Optional<ToDoClass> todo=object.findById(toDoClass.getId());
      if (todo.isPresent()){
          todo.get().setUsername(toDoClass.getUsername());
          todo.get().setTargetDate(toDoClass.getTargetDate());
          todo.get().setDescription(toDoClass.getDescription());
          todo.get().setDone(toDoClass.isDone());
          object.save(todo.get());
      }



   }


}
// Optional<ToDoClass> to= object.findById(id); the reason why we use the Optional<> class is that
//the findById may or may not return objects so, it is recommended to use Optional class
//it contains of methods you can call to check such as isPresent() or to get the object using get() etc


//other point to remember is that of the id of the saving object is in the table then the contents of it
// are updated else if different then, it is added newly