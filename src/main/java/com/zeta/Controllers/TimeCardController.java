package com.zeta.Controllers;

import com.zeta.Data.TimeCard.TimeCardData;
import com.zeta.Models.Task;
import com.zeta.Models.TimeCard;
import com.zeta.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import com.zeta.Models.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class TimeCardController {

    TimeCardData timeCardData;

    @Autowired
    public TimeCardController(TimeCardData timeCardData) {
        this.timeCardData = timeCardData;
    }

    @RequestMapping(value = "/timecard", method = RequestMethod.GET)
    public String getTimeCard(Model m, HttpServletRequest request) {
        List<String> shiftId = new ArrayList<String>();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
//        if (u == null) return "timecard";
//        if (u.getRole() == Role.MEMBER) {
//            shiftId = (long) 1;
//        }



//        TimeCard timeCard = timeCardData.getTimeCard(username, shiftId);
        TimeCard timeCard = new TimeCard();
        m.addAttribute("timeCard", timeCard);
        return "timecard";
    }

    @RequestMapping(value = "/timecard", method = RequestMethod.POST)
    public String timeCard(Model m, @ModelAttribute("timeCard") TimeCard timeCard, BindingResult bindingResult) {
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


        if (!timeCardData.addTimeCard(timeCard)) {
            System.out.println("this is not working");
        }

        m.addAttribute("timeCard", timeCard);

        return "timecard";
    }
}
