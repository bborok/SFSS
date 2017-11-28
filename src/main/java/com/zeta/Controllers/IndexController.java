package com.zeta.Controllers;

import com.zeta.Data.Training.TrainingData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.zeta.Models.Role;
import com.zeta.Data.Announcements.AnnouncementsData;
import com.zeta.Models.Announcement;
import com.zeta.Data.Task.TaskData;
import com.zeta.Models.Campus;
import com.zeta.Models.Task;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.zeta.Data.Statistics.StatisticsDao;
import com.zeta.Data.Statistics.StatisticsData;
import com.zeta.Data.User.UserData;
import com.zeta.Models.User;
import com.zeta.Models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.*;
import java.util.stream.Collectors;

import static javax.swing.JOptionPane.showMessageDialog;


@Controller
public class IndexController {
    private UserData userData;
    private AnnouncementsData announcementsData;
    private TaskData taskData;
    private TrainingData trainingData;

    @Autowired
    public IndexController(UserData userData, TaskData taskData, AnnouncementsData announcementsData, TrainingData trainingData) {
        this.userData = userData;
        this.taskData = taskData;
        this.announcementsData = announcementsData;
        this.trainingData = trainingData;
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "index";
    }

    @GetMapping("/")
    public String dashboard(HttpServletRequest request, Model m) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HttpSession session = request.getSession();
        List<Announcement> announcements = announcementsData.showAllAnnouncements();
        m.addAttribute("announcements", announcements);

        if (principal instanceof UserDetails) {
            if (session.getAttribute("user") == null) {
                User user = userData.getUser(((UserDetails) principal).getUsername());
                session.setAttribute("user", user);
            }
        }
        return "index";
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
    public String profile(Model m, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User loggedIn = (User) session.getAttribute("user");
        String username = loggedIn.getUsername();

        User user = userData.getUser(username);
        session.setAttribute("user", user);

        m.addAttribute("roles", Role.values());
        return "profile";
    }

    @GetMapping("/schedule")
    public String schedule(HttpServletRequest request, Model m) {
        List<User> usersForSelection;

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        //Exit the request if user info can't get fetched
        if (u == null) return "index";
        //TODO: This shouldn't even be possible. Maybe delete in production?

        //Filter the list users  depending on the currently logged in users role.
        if (u.getRole() == Role.TEAM_LEADER) {
            //Filter the users based on the team leaders preferred campus.
            usersForSelection = userData.getAllUsers().stream()
                    .filter(user -> user.getPreferredCampus() == u.getPreferredCampus())
                    .filter(user -> (user.getRole() == Role.MEMBER || user.getRole() == Role.VOLUNTEER))
                    .collect(Collectors.toList());
        } else if (u.getRole() == Role.ADMIN || u.getRole() == Role.SUPERVISOR) {
            usersForSelection = userData.getAllUsers();
        } else {
            //At this point the currently logged in User must be a MEMBER/VOLUNTEER
            usersForSelection = new ArrayList<>();
        }
        m.addAttribute("users", usersForSelection);

        List<Task> allTasks = taskData.getTasks();
        List<Task> surreyTasks = taskData.getTasks(Campus.SURREY);
        List<Task> vancouverTasks = taskData.getTasks(Campus.VANCOUVER);
        List<Task> burnabyTasks = taskData.getTasks(Campus.BURNABY);

        m.addAttribute("ALLTASKS", allTasks);
        m.addAttribute("SURREYTASKS", surreyTasks);
        m.addAttribute("VANCOUVERTASKS", vancouverTasks);
        m.addAttribute("BURNABYTASKS", burnabyTasks);
        m.addAttribute("TRAININGTYPES", trainingData.getListOfTraining());

        return "schedule";
    }

    @GetMapping("/temp_schedule")
    public String temp_schedule(Model m) {
        m.addAttribute("someAttribute", "someValue");
        return "temp_schedule";
    }


    @GetMapping("/logout")
    public String logout() {
        return "logout";
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
        m.addAttribute("roles", Role.values());
        return "users";
    }
}