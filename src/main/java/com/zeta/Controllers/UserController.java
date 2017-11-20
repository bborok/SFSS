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

@RestController
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
    public ResponseEntity addUserToDatabase(@RequestBody User user){
        if (userData.getUser(user.getUsername()) == null) {
            if (userData.addUser(user)) {
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        } else {
            if (userData.updateUser(user)) {
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping("/remove")
    public ResponseEntity removeUserFromDatabase(@RequestBody String username) {
        if (userData.removeUser(username)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/{username}")
//    public String showUser(@PathVariable("username") String username, Model model){
//        User user = userData.getUser(username);
//        model.addAttribute("user", user);
//        return "/user/show";
//    }
//
//    @GetMapping("/showAll")
//    public String allUsers(Model model){
//        List<User> userList = userData.getAllUsers();
//        model.addAttribute(userList);
//        return "/user/showAll";
//    }
//
//    @GetMapping("/test")
//    public String testPage(){
//        return "/user/testPage";
//    }
}
