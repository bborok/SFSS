package com.zeta.Controllers;

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
    public String schedule(Model m) {
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

    @GetMapping("/statistics/info_lf")
    public String statistics_info_lf(Model m) {
        return "statistics_info_lf";
    }


    @RequestMapping(value = "/statistics/info_lf/data/get", produces="application/json", method = RequestMethod.GET)
    @ResponseBody
    public String get_statistics_info_lf_data(String campus) {
        if (campus == null)
            campus = "Burnaby";
        StatisticsData sd = new StatisticsDao();
        String[] strs = {"directions", "lost & found", "payments", "phone services", "key services", "other inquiries"};
        int[][] array = sd.getDataForInfoLF(campus, strs);
        JSONObject ret = new JSONObject();
        JSONArray title_json = new JSONArray();
        Date currDate = new Date();
        Calendar ca = Calendar.getInstance();
        ca.setTime(currDate);
        int currYear = ca.get(Calendar.YEAR);
        String[] title_strs = {String.valueOf(currYear), "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        for (String str : title_strs) {
            title_json.add(str);
        }
        ArrayList<JSONArray> vals = new ArrayList<JSONArray>();
        for (int j = 0; j < 6; j++) {
            JSONArray tmp = new JSONArray();
            for (int i = 0; i < 12; i++) {
                tmp.add(array[j][i]);
            }
            vals.add(tmp);
        }
        ret.put("title", title_json);
        ret.put(strs[0], vals.get(0));
        ret.put(strs[1], vals.get(1));
        ret.put(strs[2], vals.get(2));
        ret.put(strs[3], vals.get(3));
        ret.put(strs[4], vals.get(4));
        ret.put(strs[5], vals.get(5));
        return ret.toString();
    }

    @RequestMapping(value = "/statistics/info_lf/data/post", produces="application/json", method = RequestMethod.POST)
    @ResponseBody
    public String post_statistics_info_lf_data(String campus, String data) {
        if (campus == null)
            campus = "Burnaby";
        StatisticsData sd = new StatisticsDao();
        JSONArray jsonArray = JSONArray.fromObject(data);
        System.out.println(jsonArray.toString());
        String[] titles = {"directions", "lost & found", "payments", "phone services", "key services", "other inquiries"};
        for (int i = 0; i < 6; i++) {
            int[] tmp_arr = new int[12];
            for (int j = 0; j < 12; j++) {
                System.out.println(jsonArray.getInt(i*12 + j));
                tmp_arr[j] = jsonArray.getInt(i * 12 + j);
            }
            sd.updateDataForInfoLF(campus, titles[i], tmp_arr);
        }
        JSONObject retObject = new JSONObject();
        retObject.put("result", "success");
        retObject.put("message", "success");
        return retObject.toString();
    }

    @GetMapping("/statistics/public_contact")
    public String statistics_public_contact(Model m) {
        return "statistics_public_contact";
    }

    @RequestMapping(value="/statistics/public_contact/data", produces="application/json", method = RequestMethod.GET)
    @ResponseBody
    public String statistics_public_contact_data(String campus) {
        if (campus == null)
            campus = "Burnaby";
        StatisticsData sd = new StatisticsDao();
        Date currDate = new Date();
        Calendar ca = Calendar.getInstance();
        ca.setTime(currDate);
        int currYear = ca.get(Calendar.YEAR);
        String[] strs = {"Smoke Prevention", "Theft Prevention", "Public Contact", "Safe Walk", "Hazard/Service Request", "Assist Security"};
        int[][] array = sd.getDataForPublicContact(campus, strs);
        JSONObject ret = new JSONObject();
        JSONArray title_json = new JSONArray();
        String[] title_strs = {String.valueOf(currYear), "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        for (String str : title_strs) {
            title_json.add(str);
        }

        ArrayList<JSONArray> vals = new ArrayList<JSONArray>();
        for (int j = 0; j < 6; j++) {
            JSONArray tmp = new JSONArray();
            for (int i = 0; i < 12; i++) {
                tmp.add(array[j][i]);
            }
            vals.add(tmp);
        }
        ret.put("title", title_json);
        ret.put(strs[0], vals.get(0));
        ret.put(strs[1], vals.get(1));
        ret.put(strs[2], vals.get(2));
        ret.put(strs[3], vals.get(3));
        ret.put(strs[4], vals.get(4));
        ret.put(strs[5], vals.get(5));
        return ret.toString();
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