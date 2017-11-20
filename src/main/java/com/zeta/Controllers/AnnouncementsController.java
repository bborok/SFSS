package com.zeta.Controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zeta.Data.Announcements.AnnouncementsData;
import com.zeta.Models.Announcement;

import com.zeta.Models.Campus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.util.List;

@RestController
public class AnnouncementsController {
    private AnnouncementsData announcementsData;

    @Autowired
    public AnnouncementsController(AnnouncementsData announcementsData) {
        this.announcementsData = announcementsData;
    }

    @PostMapping("/announcements/add")
    public ResponseEntity addAnnouncementToDatabase(@RequestBody Announcement a) {
        if (announcementsData.addAnnouncement(a)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
//    @GetMapping("announcements/add")
//    public String addAnnouncement(
//            @RequestParam("username") String username,
//            @RequestParam("title") String title,
//            @RequestParam("message") String message,
//            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//            @RequestParam("date") Date date,
//            @RequestParam("campus") Campus campus)
////            @RequestParam("id") int id)
//    {
//        Announcement a = new Announcement(
//                username,
//                title,
//                message,
//                date,
//                campus);
////                campus,
////                id);
//        return "redirect:" + "/announcements/" + a.getUsername();
//    }
//
//    @GetMapping("/showAllAnnouncements")
//    public String getAllAnnouncements(Model model){
//        List<Announcement> announcementList = announcementsData.showAllAnnouncements();
//        model.addAttribute(announcementList);
//        return "/announcements/showAllAnnouncements";
//    }
//
//    @GetMapping("/{id}")
//    public String showOneAnnouncement(@PathVariable("id") int id, Model model){
//        Announcement a = announcementsData.showAnnouncements(id);
//        model.addAttribute("a", a);
//        return "/announcements/show";
//    }
}
