package com.zeta.Controllers;

import com.zeta.Data.Shift.ShiftData;
import com.zeta.Data.TimeCard.TimeCardData;
import com.zeta.Models.Shift;
import com.zeta.Models.Task;
import com.zeta.Models.TimeCard;
import com.zeta.Models.User;
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

    @Autowired
    public TimeCardController(TimeCardData timeCardData, ShiftData shiftData) {
        this.timeCardData = timeCardData;
        this.shiftData = shiftData;
    }


    @RequestMapping(value = "/timecard", method = RequestMethod.GET)
    public String getTimeCard(Model m, HttpServletRequest request) {
        List<String> shiftId = new ArrayList<String>();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        TimeCard timeCard = new TimeCard();
        boolean newCard = true;
        m.addAttribute("timeCard", timeCard);
        m.addAttribute("newCard", newCard);
        return "timecard";
    }

    @RequestMapping(value = "/timecard_edit", method = RequestMethod.GET)
    public String getEditTimeCard(Model m, HttpServletRequest request, @RequestParam("shift_id") long shift_id,@RequestParam("user_id") String username) {

        TimeCard timeCard = timeCardData.getTimeCard(username, shift_id);
        List<String> shiftId = new ArrayList<String>();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        boolean edit = true;
        m.addAttribute("timeCard", timeCard);
        m.addAttribute("editCard", edit);
        return "timecard";
    }

    @RequestMapping(value = "/timecard", method = RequestMethod.POST, params = { "save" })
    public String saveTimeCard(Model m, @ModelAttribute("timeCard") TimeCard timeCard, BindingResult bindingResult) {
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

        timeCard.setUsername("user3");
        timeCard.setShiftId(7);


        if (!timeCardData.saveTimeCard(timeCard)) {
            System.out.println("this is not working");
        }

        m.addAttribute("timeCard", timeCard);

        return "timecard";
    }

    @RequestMapping(value = "/timecard", method = RequestMethod.POST, params = { "submit" })
    public String submitTimeCard(Model m, @ModelAttribute("timeCard") TimeCard timeCard, BindingResult bindingResult) {
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

        timeCard.setUsername("admin1");
        timeCard.setShiftId(96);


        if (!timeCardData.submitTimeCard(timeCard)) {
            System.out.println("this is not working");
        }

        m.addAttribute("timeCard", timeCard);

        return "timecard";
    }

    @RequestMapping(value = "/timecard_edit", method = RequestMethod.POST, params = { "edit" })
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

        timeCard.setUsername("admin1");
        timeCard.setShiftId(96);


        if (!timeCardData.updateTimeCard(timeCard)) {
            System.out.println("this is not working");
        }

        m.addAttribute("timeCard", timeCard);

        return "redirect:/timecard_list";
    }

    @RequestMapping(value = "/timecard_list", method = RequestMethod.GET)
    public String getTimeCardList(Model m, HttpServletRequest request) {
       // List<Shift> shifts = shiftData.getShifts();
        List<Shift> userShifts = shiftData.getShiftsByUser("admin1");
//        ResponseEntity<List<Shift>>  allShifts = new ResponseEntity<>(shifts, HttpStatus.OK);
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user" );
//        if (u == null) return "timecard";
//        if (u.getRole() == Role.MEMBER) {
//            shiftId = (long) 1;
//        }
//        TimeCard timeCard = timeCardData.getTimeCard(username, shiftId);

        m.addAttribute("shifts", userShifts);
        return "timecard_list";
    }
}
