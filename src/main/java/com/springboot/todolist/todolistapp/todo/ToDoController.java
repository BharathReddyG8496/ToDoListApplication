package com.springboot.todolist.todolistapp.todo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("Username")
public class ToDoController {
    @Autowired
    ToDoRepository toDoRepository;


    public ToDoController(ToDoRepository toDoService) {
        this.toDoRepository = toDoService;
    }

    @RequestMapping(value = "/list-todos")
       public String retrieveTodos(ModelMap modelMap){
        List<ToDoClass> todos=toDoRepository.getTodos();
        modelMap.addAttribute("todos",todos);
          return "ToDos";
     }
    @RequestMapping(value = "/add-todo")
    public String showAddTodoPage(ModelMap modelMap){
     ToDoClass toDoClass= new ToDoClass(0,"Username","", LocalDate.now(),false);
      modelMap.put("toDoClass",toDoClass);
        return "add-todo";
    }

    @RequestMapping(value = "/add-todo" ,method= RequestMethod.POST)
    public String submitNewTodo(@Valid ToDoClass toDoClass, BindingResult res){
        if (res.hasErrors()){
            return "add-todo";
        }
        else {
            toDoClass.setId(null);
            toDoRepository.addTodo(toDoClass);
            return "redirect:list-todos";
        }
    }

    @RequestMapping(value = "/delete-todo")
    public String deleteTodo(@RequestParam int id){
          toDoRepository.deleteTodo(id);
          return "redirect:list-todos";
    }

    @RequestMapping(value = "/MarkDone-todo",method=RequestMethod.GET)
    public String markAsDone(@RequestParam int id){
        ToDoClass todo=toDoRepository.FindTodo(id);
        todo.setDone(true);
        toDoRepository.addTodo(todo);
        return "redirect:list-todos";
    }


    @RequestMapping(value = "/update-todo" ,method = RequestMethod.GET)
    public String showUpdatePage(@RequestParam int id,ModelMap modelMap){
        ToDoClass toDoClass=toDoRepository.FindTodo(id);
        modelMap.addAttribute("toDoClass",toDoClass);
        return "add-todo";
    }

    @RequestMapping(value="/update-todo",method = RequestMethod.POST)
    public String updateTodo(@Valid ToDoClass toDoClass,BindingResult result){
        if (result.hasErrors()) {
            System.out.println("validation errors: " + result.getAllErrors());
            return "add-todo";
        }
        else {
            toDoRepository.addTodo(toDoClass);
            return "redirect:list-todos";
        }
    }
    /* Since the user is having the to-do list there is no need to take username from the add to-do page but here if you want to get the
       string username which you used at login it can be retrieved by adding the ModelMap to the parameters and
        using the method model.get("name") to get the name put into it during the login in login controller
        you can get now because it was declared as a session attribute so it will be there in all controllers
        in the modelmap where @SessionAttribute annotation is present*/

    /* But what i have done here is that i have used the same in the value attribute of the input html tag in the
    * add-to-do page so if the user wants he can change it or else the default username is taken  */

    /* What we are doing here is that we are creating a to-do object in the showaddtodopage method and setting some default values
    * to it and adding it to the model this means that the tags in the jsp file are binded to the fields of the object(two way
    * binding means when the default values are changed they reflect on the form and the values entered in the form are set to the object's fields
    * because we have used the object-linked model name in the modelAttribute attribute of the form tag. So when the submit button
    * is clicked the to-doClass object reference is created with the same name and its getter methods are used to extract values
    * of the object    */
 }
