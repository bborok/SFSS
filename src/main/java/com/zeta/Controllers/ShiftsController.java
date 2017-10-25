package com.zeta.Controllers;

import com.zeta.Data.ShiftInterface;
import com.zeta.Models.Shift;
import com.zeta.Models.ShiftRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.POST;
import java.util.List;

@RequestMapping("/eventsAPI")
@RestController
public class ShiftsController {
    private ShiftInterface shiftInterface;

    @Autowired
    public ShiftsController(ShiftInterface shiftInterface) {
        this.shiftInterface = shiftInterface;
    }

    /**
     * TODO: change this mapping to handle two query string parameters: start and end
     * which are dates in the format of YYYY-mm-dd
     */

    @GetMapping("/shifts")
    public List<Shift> shifts(){
        return shiftInterface.getShifts();
    }

    @PostMapping("/shifts")
    public Shift saveShift(ShiftRaw jsonString){


    }
}
