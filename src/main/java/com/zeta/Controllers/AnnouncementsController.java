package com.zeta.Controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zeta.Data.Announcements.AnnouncementsData;
import com.zeta.Models.Announcements;

import com.zeta.Models.Campus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/announcements")
public class AnnouncementsController {
    private AnnouncementsData announcementsData;

    @Autowired
    public AnnouncementsController(AnnouncementsData announcementsData) {
        this.announcementsData = announcementsData;
    }

    @GetMapping("announcements/add")
    public String addAnnouncement(
            @RequestParam("username") String username,
            @RequestParam("title") String title,
            @RequestParam("message") String message,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
            @RequestParam("date") Date date,
            @RequestParam("campus") Campus campus,
            @RequestParam("id") int id) {
        Announcements a = new Announcements(
                username,
                title,
                message,
                date,
                campus,
                id);
        return "redirect:" + "/announcements/" + a.getId();
    }

    @GetMapping("/showAllAnnouncements")
    public String showAllAnnouncements(Model model){
        List<Announcements> announcementsList = announcementsData.getAllAnnouncements();
        model.addAttribute(announcementsList);
        return "/announcements/showAllAnnouncements";
    }

    @GetMapping("/{id}")
    public String showAnnouncement(@PathVariable("id") int id, Model model){
        Announcements a = announcementsData.getAnnouncements(id);
        model.addAttribute("a", a);
        return "/announcements/show";
    }
}
