package com.springboot.todolist.todolistapp.Hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
public class HelloWorldController {
    @RequestMapping("say-hello")
    @ResponseBody
    public String SayHello(){
        return "Hello you crazy motherfucker!!!!";
    }

    @RequestMapping("say-hello-html")
    @ResponseBody
    public String sayHelloHtml() {
        //A string buffer is used instead of string because since string is immutable, but stringbuffer can be modified by
        //its methods
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title> My First HTML Page - Changed</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("My first html page with body - Changed");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
    @RequestMapping("say-hello-jsp")
    public String sayHelloJsp(@RequestParam String name, ModelMap modelMap) {
        modelMap.put("name",name);
    return "sayhello";
    //normally this returns a view so we used response body to return the string as it is but we are
        //redirecting the request to the jsp files which are views so there is no need for response body
        //the prefix and suffix is defined in the properties file

    }



}
