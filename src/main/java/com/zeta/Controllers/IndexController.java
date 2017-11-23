package com.zeta.Controllers;

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
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static javax.swing.JOptionPane.showMessageDialog;


@Controller
public class IndexController {

    private UserData userData;
    private AnnouncementsData announcementsData;
    private TaskData taskData;

    @Autowired
    public IndexController(UserData userData, TaskData taskData,AnnouncementsData announcementsData) {
        this.userData = userData;
        this.taskData = taskData;
        this.announcementsData = announcementsData;
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
        List<User> users = userData.getAllUsers();
        m.addAttribute("users", users);

        List<Task> allTasks = taskData.getTasks();
        List<Task> surreyTasks = taskData.getTasks(Campus.SURREY);
        List<Task> vancouverTasks = taskData.getTasks(Campus.VANCOUVER);
        List<Task> burnabyTasks = taskData.getTasks(Campus.BURNABY);

        m.addAttribute("ALLTASKS", allTasks);
        m.addAttribute("SURREYTASKS", surreyTasks);
        m.addAttribute("VANCOUVERTASKS", vancouverTasks);
        m.addAttribute("BURNABYTASKS", burnabyTasks);

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

    @RequestMapping(value = "/statistic/data", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public String testjson(String campus) {
        if (campus == null)
            campus = "Burnaby";
        StatisticsData sd = new StatisticsDao();
        Date currDate = new Date();
        Calendar ca = Calendar.getInstance();
        ca.setTime(currDate);
        int currYear = ca.get(Calendar.YEAR);
        int[][] array = new int[6][12];
        String[] strs = {"Smoke Prevention", "Theft Prevention", "Public Contact", "Safe Walk", "Hazard/Service Request", "Assist Security"};
        for (int t = 0; t < 6; t++) {
            for (int i = 0; i < 12; i++) {
                String str = String.valueOf(currYear) + "-" + String.valueOf(i + 1) + "-01";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = sdf.parse(str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                array[t][i] = sd.getTaskCountMonth(strs[t], campus, calendar);
            }
        }

        for (int t = 0; t < 6; t++) {
            for (int i = 0; i < 12; i++) {
                System.out.print(array[t][i]);
            }
            System.out.print("\n");
        }

        JsonArrayBuilder title = Json.createArrayBuilder();
        String[] title_strs = {String.valueOf(currYear), "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        for (String str : title_strs) {
            title.add(str);
        }
        JsonArrayBuilder val0 = Json.createArrayBuilder();
        JsonArrayBuilder val1 = Json.createArrayBuilder();
        JsonArrayBuilder val2 = Json.createArrayBuilder();
        JsonArrayBuilder val3 = Json.createArrayBuilder();
        JsonArrayBuilder val4 = Json.createArrayBuilder();
        JsonArrayBuilder val5 = Json.createArrayBuilder();
        for (int i = 0; i < 12; i++) {
            val0.add(array[0][i]);
            val1.add(array[1][i]);
            val2.add(array[2][i]);
            val3.add(array[3][i]);
            val4.add(array[4][i]);
            val5.add(array[5][i]);
        }

        JsonObject result = Json.createObjectBuilder()
                .add("title", title.build())
                .add(strs[0], val0.build())
                .add(strs[1], val1.build())
                .add(strs[2], val2.build())
                .add(strs[3], val3.build())
                .add(strs[4], val4.build())
                .add(strs[5], val5.build())
                .build();
        return result.toString();
    }

    @GetMapping("/statistics_public_contact")
    public String statistics_public_contact(Model m) {
        return "statistics_public_contact";
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