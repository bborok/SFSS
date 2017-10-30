package com.zeta.Controllers;

import com.zeta.Data.TimeCard.TimeCardData;
import com.zeta.Models.Task;
import com.zeta.Models.TimeCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TimeCardController {

    TimeCardData timeCardData;

    @Autowired
    public TimeCardController(TimeCardData timeCardData) {
        this.timeCardData = timeCardData;
    }

    @RequestMapping(value = "/timecard", method = RequestMethod.GET)
    public String getTimeCard(Model m, String username, Long shiftId) {

        // To be removed, only for testing
        username = "user1";
        shiftId = (long)1;

        TimeCard timeCard = timeCardData.getTimeCard(username, shiftId);

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

        timeCard.setUsername("user2");
        timeCard.setShiftId(10);


        if(!timeCardData.addTimeCard(timeCard)){
            System.out.println("this is not working");
        }


        m.addAttribute("timeCard", timeCard);

        return "timecard";
    }
}
