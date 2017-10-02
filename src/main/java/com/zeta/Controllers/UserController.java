package com.zeta.Controllers;

import com.zeta.Models.Campus;
import com.zeta.Models.Role;
import com.zeta.Models.User;
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

    //Tested with URL:
    //localhost:8080/user/add?studentNumber=36&name=Eric&email=eric@sfu.ca&phoneNumber=656456789&role=team_lead&campus=surrey&accountCode=654
    @PostMapping("/add")
    public String addUserToDatabase(
            @RequestParam("sfuid") String sfuId,
            @RequestParam("studentNumber") long studentNumber,
            @RequestParam("name") String name,
            @RequestParam("email") String email ,
            @RequestParam("phoneNumber") long phoneNumber,
            @RequestParam("role") String role,
            @RequestParam("campus") String campus,
            @RequestParam("accountCode") long accountCode){

        User u = new User(
                sfuId,
                studentNumber,
                name,
                email,
                phoneNumber,
                Role.valueOf(role.toUpperCase()),
                Campus.valueOf(campus.toUpperCase()),
                accountCode
            );
        service.addUser(u);
        return "redirect:" + "/user/" + u.getSfuId().trim();
    }

    @GetMapping("/{sfuid}")
    public String showUser(@PathVariable("sfuid") String sfuid, Model model){
        User user = service.getUserFromSFUId(sfuid);
        model.addAttribute("user", user);
        return "/user/show";
    }

    @GetMapping("/test")
    public String testPage(){
        return "/user/testPage";
    }
}
