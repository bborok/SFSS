package com.zeta.Controllers;

import com.zeta.Data.User.UserData;
import com.zeta.Models.Campus;
import com.zeta.Models.Role;
import com.zeta.Models.Training;
import com.zeta.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserData userData;

    @Autowired
    public UserController(UserData userData) {
        this.userData = userData;
    }

    //Tested with URL:
    //localhost:8080/user/add?studentNumber=36&name=Eric&email=eric@sfu.ca&phoneNumber=656456789&role=team_lead&campus=surrey&accountCode=654
    @PostMapping("/add")
    public @ResponseBody ResponseEntity addUserToDatabase(
            @RequestParam("username") String username,
            @RequestParam("studentNumber") long studentNumber,
            @RequestParam("name") String name,
            @RequestParam("email") String email ,
            @RequestParam("phoneNumber") long phoneNumber,
            @RequestParam("role") String role,
            @RequestParam("preferredCampus") String campus,
            @RequestParam("callSign") String callSign,
            @RequestParam("training") List<Training> training,
            @RequestParam("isDeactivated") Boolean isDeactivated){

        User u = new User(
                username,
                studentNumber,
                name,
                email,
                phoneNumber,
                Role.valueOf(role.toUpperCase()),
                Campus.valueOf(campus.toUpperCase()),
                callSign,
                training,
                isDeactivated);

        if (userData.addUser(u)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{username}")
    public String showUser(@PathVariable("username") String username, Model model){
        User user = userData.getUser(username);
        model.addAttribute("user", user);
        return "/user/show";
    }

    @GetMapping("/showAll")
    public String allUsers(Model model){
        List<User> userList = userData.getAllUsers();
        model.addAttribute(userList);
        return "/user/showAll";
    }

    @GetMapping("/test")
    public String testPage(){
        return "/user/testPage";
    }
}
