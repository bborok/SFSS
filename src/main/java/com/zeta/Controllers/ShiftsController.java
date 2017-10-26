package com.zeta.Controllers;

import com.zeta.Data.ShiftInterface;
import com.zeta.Models.Shift;
import com.zeta.Models.ShiftRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Handles API requests for Shift's and ShiftRaw's
 */
@RequestMapping("/api")
@RestController
public class ShiftsController {
    private ShiftInterface shiftInterface;

    @Autowired
    public ShiftsController(ShiftInterface shiftInterface) {
        this.shiftInterface = shiftInterface;
    }


    /**
     * Use this to get a list of Shifts in the database as a Shift object.
     *
     * @return List<Shift>
     */
    @GetMapping("/shifts")
    public ResponseEntity<List<Shift>> shifts() {
        // TODO: change this mapping to handle two query string parameters: start and end
        return new ResponseEntity<>(shiftInterface.getShifts(), HttpStatus.OK);
    }

    /**
     * Use this to get a Shift as a Shift object.
     *
     * @param id id of Shift to get
     * @return Shift
     */
    @GetMapping("/shifts/{id}")
    public ResponseEntity<Shift> shift(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(shiftInterface.getShift(id), HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    /**
     * Use this when needing to add a new row to the Shift table by passing in a ShiftRaw
     *
     * @param shiftRaw 'stringified' ShiftRaw JSON object
     * @return Status code 200 if successful, 400 if not
     */
    @PostMapping("/shifts/save")
    public ResponseEntity<Object> saveShift(@RequestBody ShiftRaw shiftRaw) {
        System.out.println(shiftRaw.toString());
        if (shiftInterface.saveShift(shiftRaw)) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    /**
     * Use this to get a list of all Shifts in the database as a ShiftRaw object.
     *
     * @return List<ShiftRaw>
     */
    @GetMapping("/shiftraws")
    public ResponseEntity<List<ShiftRaw>> shiftRaws() {
        return new ResponseEntity<>(shiftInterface.getShiftRaws(), HttpStatus.OK);
    }

    /**
     * Use this to delete a Shift from the database.
     *
     * @param id id of Shift to delete
     * @return Status code 200 if successful, 400 if not
     */
    @DeleteMapping("/shifts/delete/{id}")
    public ResponseEntity<Object> deleteShift(@PathVariable long id) {
        if (shiftInterface.deleteShift(id)) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

}