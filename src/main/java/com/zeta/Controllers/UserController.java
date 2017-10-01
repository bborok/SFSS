package com.zeta.Controllers;

import com.zeta.Models.User;
import com.zeta.Repositories.UserRepository;
import com.zeta.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    //Tested with URL: localhost:8080/user/add?studentNumber=36&name=Eric&email=eric@sfu.ca
    @PostMapping("/add")
    public String addUserToDatabase(
            @RequestParam("studentNumber") int studentNumber,
            @RequestParam("name") String name,
            @RequestParam("email") String email /*,
            @RequestParam("phoneNumber") int phoneNumber,
            @RequestParam("role") String role,
            @RequestParam("campus") String campus,
            @RequestParam("accountCode") int accountCode*/){

        User u = new User(
                studentNumber,
                name,
                email
            );
        service.addUser(u);
        return "redirect:" + "/user/" + u.getStudentNumber();
    }

    @GetMapping("/{studentNumber}")
    public String showUser(@PathVariable("studentNumber") long studentNumber, Model model){
        User user = service.getUserFromStudentNumber(studentNumber);
        model.addAttribute("user", user);
        return "/user/show";
    }

    @GetMapping("/test")
    public String testPage(){
        return "/user/testPage";
    }
}
