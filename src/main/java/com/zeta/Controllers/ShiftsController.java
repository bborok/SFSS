package com.zeta.Controllers;

import com.zeta.Data.Shift.ShiftData;
import com.zeta.Models.ShiftRaw;
import com.zeta.Models.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;


/**
 * Handles API requests for Shift's and ShiftRaw's
 */
@RequestMapping("/api")
@RestController
public class ShiftsController {
    private ShiftData shiftData;

    @Autowired
    public ShiftsController(ShiftData shiftData) {
        this.shiftData = shiftData;
    }

    /**
     * Use this when needing to add a new row to the Shift table by passing in a ShiftRaw
     *
     * @param shiftRaw 'stringified' ShiftRaw JSON object
     * @return Status code 200 if successful, 400 if not
     */
    @PostMapping("/shifts/save")
    public ResponseEntity<Object> saveShift(@RequestBody ShiftRaw shiftRaw, HttpServletRequest httpServletRequest) {
        System.out.println(shiftRaw.toString());
        if (shiftData.saveShiftRaw(shiftRaw)){
            System.out.println("Shift saved successfully.");
            return ResponseEntity
                    .created(URI.create(httpServletRequest.getRequestURI()))
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("Success");
        }
        else
            return ResponseEntity.badRequest().build();
    }

    /**
     * Use this to get a list of all Shifts in the database as a ShiftRaw object.
     *
     * @return List<ShiftRaw>
     */
    @GetMapping("/shiftraws")
    public ResponseEntity<List<ShiftRaw>> shiftRaws() {
        List<ShiftRaw> shiftRaws = shiftData.getShiftRaws();
        return new ResponseEntity<>(shiftRaws, HttpStatus.OK);
    }

    /**
     * Use this to get a ShiftRaw object based on the id
     *
     * @param id id of Shift
     * @return ShiftRaw object
     */
    @GetMapping("/shiftraws/{id}")
    public ResponseEntity<ShiftRaw> getShiftRaw(@PathVariable long id) {
        ShiftRaw shiftRaw = shiftData.getShiftRaw(id);
        if (shiftRaw == null) return ResponseEntity.notFound().build();
        return new ResponseEntity<>(shiftRaw, HttpStatus.OK);
    }

    /**
     * Use this to delete a Shift from the database.
     *
     * @param id id of Shift to delete
     * @return Status code 200 if successful, 400 if not
     */
    @DeleteMapping("/shifts/delete/{id}")
    public ResponseEntity<Object> deleteShift(@PathVariable long id) {
        if (shiftData.deleteShift(id)) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

}