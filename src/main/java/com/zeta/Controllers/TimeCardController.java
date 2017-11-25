package com.zeta.Controllers;

import com.zeta.Data.Shift.ShiftData;
import com.zeta.Data.TimeCard.TimeCardData;
import com.zeta.Data.User.UserData;
import com.zeta.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class TimeCardController {

    TimeCardData timeCardData;
    ShiftData shiftData;
    private UserData userData;


    @Autowired
    public TimeCardController(TimeCardData timeCardData, ShiftData shiftData, UserData userData) {
        this.timeCardData = timeCardData;
        this.shiftData = shiftData;
        this.userData = userData;
    }


    @RequestMapping(value = "/timecard", method = RequestMethod.GET)
    public String getTimeCard(Model m, HttpServletRequest request,@RequestParam("shift_id") long shift_id, @RequestParam("username") String username) {
        List<String> shiftId = new ArrayList<String>();
        TimeCard timeCard = timeCardData.getTimeCard(username, shift_id);


        boolean newCard = true;
        m.addAttribute("timeCard", timeCard);
        if(timeCardData.timeCardRecordExist(timeCard)){
            newCard = false;
            m.addAttribute("newCard", newCard);
        }else{
            m.addAttribute("newCard", newCard);
        }
        boolean timecardSubmitted = timeCard.getIsTimeCardSubmitted();
        m.addAttribute("timecardSubmitted", timecardSubmitted);

        return "timecard";
    }


    @RequestMapping(value = "/timecard", method = RequestMethod.POST, params = { "save" })
    public String saveTimeCard(Model m, @ModelAttribute("timeCard") TimeCard timeCard) {
        Task SPTotal = new Task("Smoke Prevention");
        SPTotal.setCount(Integer.parseInt(timeCard.getSPTotal()));
        timeCard.addToTasks(SPTotal);
        Task TPTotal = new Task("Theft Prevention");
        TPTotal.setCount(Integer.parseInt(timeCard.getTPTotal()));
        timeCard.addToTasks(TPTotal);

        Task PCTotal = new Task("Public Contact");
        PCTotal.setCount(Integer.parseInt(timeCard.getPCTotal()));
        timeCard.addToTasks(PCTotal);

        Task SWTotal = new Task("Safe Walk");
        SWTotal.setCount(Integer.parseInt(timeCard.getSWTotal()));
        timeCard.addToTasks(SWTotal);

        Task HSRTotal = new Task("Hazard/Service Request");
        HSRTotal.setCount(Integer.parseInt(timeCard.getHSRTotal()));
        timeCard.addToTasks(HSRTotal);

        Task ASTotal = new Task("Assist Security");
        ASTotal.setCount(Integer.parseInt(timeCard.getASTotal()));
        timeCard.addToTasks(ASTotal);


        if (!timeCardData.saveTimeCard(timeCard)) {
            System.out.println("this is not working");
        }

        m.addAttribute("timeCard", timeCard);

        return "redirect:/timecard_list";
    }


    @RequestMapping(value = "/timecard", method = RequestMethod.POST, params = { "submit" })
    public String submitTimeCard(Model m, @ModelAttribute("timeCard") TimeCard timeCard) {

        Task SPTotal = new Task("Smoke Prevention");
        SPTotal.setCount(Integer.parseInt(timeCard.getSPTotal()));
        timeCard.addToTasks(SPTotal);
        Task TPTotal = new Task("Theft Prevention");
        TPTotal.setCount(Integer.parseInt(timeCard.getTPTotal()));
        timeCard.addToTasks(TPTotal);

        Task PCTotal = new Task("Public Contact");
        PCTotal.setCount(Integer.parseInt(timeCard.getPCTotal()));
        timeCard.addToTasks(PCTotal);

        Task SWTotal = new Task("Safe Walk");
        SWTotal.setCount(Integer.parseInt(timeCard.getSWTotal()));
        timeCard.addToTasks(SWTotal);

        Task HSRTotal = new Task("Hazard/Service Request");
        HSRTotal.setCount(Integer.parseInt(timeCard.getHSRTotal()));
        timeCard.addToTasks(HSRTotal);

        Task ASTotal = new Task("Assist Security");
        ASTotal.setCount(Integer.parseInt(timeCard.getASTotal()));
        timeCard.addToTasks(ASTotal);


        if (!timeCardData.submitTimeCard(timeCard)) {
            System.out.println("this is not working");
        }

        m.addAttribute("timeCard", timeCard);

        return "redirect:/timecard_list";
    }


    @RequestMapping(value = "/timecard", method = RequestMethod.POST, params = { "edit" })
    public String EditTimeCard(Model m, @ModelAttribute("timeCard") TimeCard timeCard) {

        Task SPTotal = new Task("Smoke Prevention");
        SPTotal.setCount(Integer.parseInt(timeCard.getSPTotal()));
        timeCard.addToTasks(SPTotal);
        Task TPTotal = new Task("Theft Prevention");
        TPTotal.setCount(Integer.parseInt(timeCard.getTPTotal()));
        timeCard.addToTasks(TPTotal);

        Task PCTotal = new Task("Public Contact");
        PCTotal.setCount(Integer.parseInt(timeCard.getPCTotal()));
        timeCard.addToTasks(PCTotal);

        Task SWTotal = new Task("Safe Walk");
        SWTotal.setCount(Integer.parseInt(timeCard.getSWTotal()));
        timeCard.addToTasks(SWTotal);

        Task HSRTotal = new Task("Hazard/Service Request");
        HSRTotal.setCount(Integer.parseInt(timeCard.getHSRTotal()));
        timeCard.addToTasks(HSRTotal);

        Task ASTotal = new Task("Assist Security");
        ASTotal.setCount(Integer.parseInt(timeCard.getASTotal()));
        timeCard.addToTasks(ASTotal);

        timeCard.setTimeCardSubmitted(true);
        if (!timeCardData.updateTimeCard(timeCard)) {
            System.out.println("this is not working");
        }

        m.addAttribute("timeCard", timeCard);

        return "redirect:/timecard_list";
    }

    @RequestMapping(value = "/timecard_list", method = RequestMethod.GET)
    public String getTimeCardList(Model m, HttpServletRequest request) {

        List<Shift> shifts ;
        HttpSession session = request.getSession();
//        session.setAttribute("user", userData.getUser("admin1"));
        User u = (User) session.getAttribute("user" );

        if (u.getRole() == Role.MEMBER || u.getRole() == Role.VOLUNTEER) {
            if(shiftData.getShiftsByUser(u.getUsername() )== null){
                shifts = new ArrayList<Shift>();
            }else{
                shifts = shiftData.getShiftsByUser(u.getUsername());
            }
        }else{
            shifts = shiftData.getShifts();
        }


        m.addAttribute("shifts", shifts);
        return "timecard_list";
    }
}
