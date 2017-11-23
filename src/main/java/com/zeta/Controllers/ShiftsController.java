package com.zeta.Controllers;

import com.zeta.Data.Shift.ShiftData;
import com.zeta.Models.Role;
import com.zeta.Models.Shift;
import com.zeta.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Handles API requests for Shift's and Shift's
 * TODO: each of the requests made to this API should be validated based on the logged-in users Role
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
     * Use this to get a list of all Shifts in the database as a Shift object.
     *
     * @return List<Shift>
     */
    @GetMapping("/shifts")
    public ResponseEntity<List<Shift>> getShifts(
            HttpServletRequest request,
            @RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
            @RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end
    ) {
        List<Shift> shifts;

        //TODO: uncomment when deploying to server
        //Filter by roles
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        if (u == null) return ResponseEntity.badRequest().build();
        if (u.getRole() == Role.MEMBER || u.getRole() == Role.VOLUNTEER) {
            shifts = shiftData.getShiftsWithUsername(u.getUsername());
        } else {
            shifts = shiftData.getShifts();
        }

        //Filter by start and end query parameters (if available)
        shifts = shifts.stream().filter(shift -> shift.getDate().after(start) && shift.getDate().before(end)).collect(Collectors.toList());
        return new ResponseEntity<>(shifts, HttpStatus.OK);
    }

    /**
     * Use this when needing to add a new row to the Shift table by passing in a Shift
     *
     * @param shift 'stringified' Shift JSON object
     * @return Status code 200 if successful, 400 if not
     */
    @PostMapping("/shift/save")
    public ResponseEntity<Object> saveShift(@RequestBody Shift shift, HttpServletRequest httpServletRequest) {
        System.out.println(shift.toString());
        if (shiftData.saveShift(shift)) {
            System.out.println("Shift saved successfully.");
            return ResponseEntity
                    .created(URI.create(httpServletRequest.getRequestURI()))
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("Success");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    /**
     * Use this to get a Shift object based on the id
     *
     * @param id id of Shift
     * @return Shift object
     */
    @GetMapping("/shift/{id}")
    public ResponseEntity<Shift> getShift(@PathVariable long id) {
        Shift shift = shiftData.getShift(id);
        if (shift == null) return ResponseEntity.notFound().build();
        return new ResponseEntity<>(shift, HttpStatus.OK);
    }

    /**
     * Use this to delete a Shift from the database.
     *
     * @param id id of Shift to delete
     * @return Status code 200 if successful, 400 if not
     */
    @DeleteMapping("/shift/delete/{id}")
    public ResponseEntity<Object> deleteShift(@PathVariable long id) {
        if (shiftData.deleteShift(id)) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }
}