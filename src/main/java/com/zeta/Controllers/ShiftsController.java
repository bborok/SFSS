package com.zeta.Controllers;

import com.zeta.Data.ShiftInterface;
import com.zeta.Models.Shift;
import com.zeta.Models.ShiftRaw;
import com.zeta.Models.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Shift>> shifts() {
        return new ResponseEntity<>(shiftInterface.getShifts(), HttpStatus.OK);
    }

    @GetMapping("/shifts/{id}")
    public ResponseEntity<Shift> shift(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(shiftInterface.getShift(id), HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @PostMapping("/shifts/save")
    public ResponseEntity<Shift> saveShift(@RequestBody ShiftRaw shiftRaw) {
//        System.out.println("Received post request");
        Shift shift = shiftInterface.saveShift(shiftRaw);
        return new ResponseEntity<>(shift, HttpStatus.OK);
//        System.out.println(shiftRaw.getUsername());
//        return new ResponseEntity<>("What a memer", HttpStatus.OK);
    }

    //TODO: Delete shift by ID

}