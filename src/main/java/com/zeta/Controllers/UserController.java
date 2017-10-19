package com.zeta.Controllers;

import com.zeta.Models.Campus;
import com.zeta.Models.Role;
import com.zeta.Models.User;
import com.zeta.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    //Tested with URL:
    //localhost:8080/user/add?studentNumber=36&name=Eric&email=eric@sfu.ca&phoneNumber=656456789&role=team_lead&campus=surrey&accountCode=654
    @GetMapping("/add")
    public String addUserToDatabase(
            @RequestParam("username") String username,
            @RequestParam("studentNumber") long studentNumber,
            @RequestParam("name") String name,
            @RequestParam("email") String email ,
            @RequestParam("phoneNumber") long phoneNumber,
            @RequestParam("role") String role,
            @RequestParam("campus") String campus,
            @RequestParam("callSign") String callSign,
            @RequestParam("training") String training){

        User u = new User(
                username,
                studentNumber,
                name,
                email,
                phoneNumber,
                Role.valueOf(role.toUpperCase()),
                Campus.valueOf(campus.toUpperCase()),
                callSign,
                training
            );
        service.addUser(u);
        return "redirect:" + "/user/" + u.getUsername().trim();
    }

    @GetMapping("/{username}")
    public String showUser(@PathVariable("username") String username, Model model){
        User user = service.getUserFromUsername(username);
        model.addAttribute("user", user);
        return "/user/show";
    }

    @GetMapping("/showAll")
    public String allUsers(Model model){
        List<User> userList = service.getListOfAllUsers();
        model.addAttribute(userList);
        return "/user/showAll";
    }

    @GetMapping("/test")
    public String testPage(){
        return "/user/testPage";
    }
}
