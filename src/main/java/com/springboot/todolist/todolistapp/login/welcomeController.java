package com.springboot.todolist.todolistapp.login;


import com.springboot.todolist.todolistapp.todo.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("Username")
public class welcomeController {
    @Autowired
    ToDoService todoser;

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String welcome( ModelMap modelMap){
        modelMap.put("Username",todoser.ExtractLoggedUserName());
        return "welcome";
   }




}
