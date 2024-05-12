package com.springboot.todolist.todolistapp.todo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class TodoSpringSecurityConfiguration {

    //InMemoryUserDetailsManager is used to configure the user with a username and password and with a password encoder
    //its constructor requires an instance of UserDetails which we create or rather build with
    //functional programming by setting the encoder we want, username,password and the roles assigned to the user


    @Bean
    public InMemoryUserDetailsManager userDetManager(){
        UserDetails userDetails01=createNewUser("Bharath","hello");
        UserDetails userDetails02=createNewUser("Mailary","playboy");


        return new InMemoryUserDetailsManager(userDetails01,userDetails02);

    }

    private UserDetails createNewUser(String username, String password) {
//        Function<String, String> encoder= input-> passwordEncoder().encode(input);//password encoder method(bean) is called from below which returns a bcrypt encoder
      return User.builder().passwordEncoder(input-> passwordEncoder().encode(input)).username(username).password(password).roles("USER","ADMIN").build();
    }
    //Here we use the builder() function of the User class which returns an instance  of UserBuilder, which we use its methods username,password,roles,passwordEncoder and if the build()
    //is called then it returns the instance of Userdetails with its fields set by the build method. the passwordEncoder method requires a variable of type passwordEncoder
    //which implements an encoder(bcrypt) returned from the bean below

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(auth->auth.anyRequest().authenticated());
        httpSecurity.formLogin(withDefaults());
        httpSecurity.csrf(csrf->csrf.disable());
        httpSecurity.headers(header->header.disable());
        return httpSecurity.build();
    }
}


//    @Bean
//    public InMemoryUserDetailsManager userDetManager(){
//        UserDetails userDetails = User.withDefaultPasswordEncoder().username("Bharath").password("hello").roles("USER","ADMIN").build();
//        return new InMemoryUserDetailsManager(userDetails);
//    }
// Overview of whats happening over here(if we use the default encoder method)
//The User is a class(inbuilt) which has a method called as withDefaultPasswordEncoder
//and it returns an instance of UserBuilder class, using the object of UserBuilder Class we call its methods
//username,password,roles and other methods using a method called method chaining and finally we call the build method
//which return an instance of the UserDetails class by setting fields internally.


//The inmemoryUserdetails manager is a class which contains a constructor which accepts an array of UserDetails(the syntax method_name(UserDetails... user)s means a variable arguments
// that you can pass any number of objects as parameters and it is treated as an array of type UserDetails  inside the method) and creates a new user for each object passed
// to the constructor.

//The methods are seperated to create multiple users by just calling the method with the name and password as parameters


//To be able to access h2 console we must do the following
//Protect all URL's in other words authorise all http requests
//A login form must be shown for unauthorised requests
//disable CSRF(Cross site request forgery)
//Enable frames cuz spring by default does not allows frames


