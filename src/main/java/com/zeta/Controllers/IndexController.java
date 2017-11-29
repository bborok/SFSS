package com.zeta.Controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zeta.Data.Training.TrainingData;
import com.zeta.Models.Role;
import com.zeta.Data.Announcements.AnnouncementsData;
import com.zeta.Models.Announcement;
import com.zeta.Data.Task.TaskData;
import com.zeta.Models.Campus;
import com.zeta.Models.Task;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.zeta.Data.User.UserData;
import com.zeta.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.*;
import java.util.stream.Collectors;



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
    public String users(HttpServletRequest request, Model m) throws IOException {
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

        List<String> languages = getLanguages();

        m.addAttribute("users", users);
        m.addAttribute("roles", Role.values());
        m.addAttribute("languages", languages);
        return "users";
    }

    private List<String> getLanguages() throws IOException {
        String requestURL = "http://ws.detectlanguage.com/0.2/languages";
        URL url = new URL(requestURL);
        InputStream inputStream = url.openStream();

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode[] objectNode = mapper.readValue(inputStream, ObjectNode[].class);

        List<String> languages = new ArrayList<String>();
        for (JsonNode jNode : objectNode) {
            String language = jNode.get("name").asText().toLowerCase();
            language = language.substring(0,1).toUpperCase() + language.substring(1);
            languages.add(language);
        }

        return languages;
    }
}