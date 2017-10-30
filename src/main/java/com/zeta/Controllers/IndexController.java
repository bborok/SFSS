package com.zeta.Controllers;

import com.zeta.Data.User.UserData;
import com.zeta.Models.Login;
import com.zeta.Models.Role;
import com.zeta.Models.User;
import com.zeta.Models.Task;
import com.zeta.Models.TimeCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;


// Test function found in tutorial for setting up this project, can be discarded
@Controller
public class IndexController {

    private UserData userData;

    @Autowired
    public IndexController(UserData userData) {
        this.userData = userData;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(Model m) {
        Login login = new Login();
        m.addAttribute("login", login);

        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String loginUser(HttpServletRequest request, @ModelAttribute("login") Login login, BindingResult bindingResult) {
        User user = userData.getUserByLogin(login);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return "dashboard";
        }
        return "index";
    }

    @RequestMapping(value = "/timecard", method = RequestMethod.GET)
    public String getTimeCard(Model m) {
        TimeCard timeCard = new TimeCard();

        m.addAttribute("timeCard", timeCard);
        return "timecard";
    }

    @RequestMapping(value = "/timecard", method = RequestMethod.POST)
    public String timeCard(Model m, @ModelAttribute("timeCard") TimeCard timeCard, BindingResult bindingResult) {
        Task SPTotal = new Task("SPTotal");
        SPTotal.setCount(Integer.parseInt(timeCard.getSPTotal()));
        timeCard.addToTasks(SPTotal);
        Task TPTotal = new Task("TPTotal");
        TPTotal.setCount(Integer.parseInt(timeCard.getTPTotal()));
        timeCard.addToTasks(TPTotal);

        Task PCTotal = new Task("PCTotal");
        PCTotal.setCount(Integer.parseInt(timeCard.getPCTotal()));
        timeCard.addToTasks(PCTotal);

        Task SWTotal = new Task("SWTotal");
        SWTotal.setCount(Integer.parseInt(timeCard.getSWTotal()));
        timeCard.addToTasks(SWTotal);

        Task HSRTotal = new Task("HSRTotal");
        HSRTotal.setCount(Integer.parseInt(timeCard.getHSRTotal()));
        timeCard.addToTasks(HSRTotal);

        Task ASTotal = new Task("ASTotal");
        ASTotal.setCount(Integer.parseInt(timeCard.getASTotal()));
        timeCard.addToTasks(ASTotal);

        System.out.println(timeCard.getTasks());

        m.addAttribute("timeCard", timeCard);

        return "timecard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model m) {
        return "dashboard";
    }

    @GetMapping("/log")
    public String log(Model m) {
        m.addAttribute("someAttribute", "someValue");
        return "log";
    }

    @GetMapping("/payroll")
    public String payroll(Model m) {
        m.addAttribute("someAttribute", "someValue");
        return "payroll";
    }

    @GetMapping("/profile")
    public String profile(Model m) {
        List<User> users = userData.getAllUsers();
        m.addAttribute("users", users);

        return "profile";
    }

    @GetMapping("/schedule")
    public String schedule(Model m) {
        m.addAttribute("someAttribute", "someValue");
        return "schedule";
    }

    @GetMapping("/statistics")
    public String statistics(Model m) {
        m.addAttribute("someAttribute", "someValue");
        return "statistics";
    }

    @GetMapping("/statistics_info_lf")
    public String statistics_info_lf(Model m) {
        m.addAttribute("someAttribute", "someValue");
        return "statistics_info_lf";
    }

    @GetMapping("/statistics_public_contact")
    public String statistics_public_contact(Model m) {
        m.addAttribute("someAttribute", "someValue");
        return "statistics_public_contact";
    }

    @GetMapping("/temp_schedule")
    public String temp_schedule(Model m) {
        m.addAttribute("someAttribute", "someValue");
        return "temp_schedule";
    }

    @GetMapping("/users")
    public String users(HttpServletRequest request, Model m) {
        List<User> users;
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        if (u == null) return "users"; //Exit the request if user info can't get fetched
        //Filter the list users depending on the currently logged in users role.
        if (u.getRole() == Role.TEAM_LEADER) {
            //Filter the users based on the team leaders preferred campus.
            users = userData.getAllUsers().stream()
                    .filter(user -> user.getPreferredCampus() == u.getPreferredCampus())
                    .filter(user -> (user.getRole() == Role.MEMBER || user.getRole() == Role.VOLUNTEER))
                    .collect(Collectors.toList());
        } else {
            users = userData.getAllUsers();
        }

        m.addAttribute("users", users);
        return "users";
    }
}