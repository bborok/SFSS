package com.zeta.Controllers;

import com.zeta.Data.User.UserData;
import com.zeta.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserData userData;
    private final String ROOT_PATH = System.getProperty("catalina.home");
    private final String IMAGE_PATH = ROOT_PATH + File.separator + "images" + File.separator + "profiles";
    private final File PROFILE_DIR = new File(IMAGE_PATH);

    @Autowired
    public UserController(UserData userData) {
        this.userData = userData;
    }

    //Tested with URL:
    //localhost:8080/user/add?studentNumber=36&name=Eric&email=eric@sfu.ca&phoneNumber=656456789&role=team_lead&campus=surrey&accountCode=654
    @PostMapping("/add")
    public ResponseEntity addUserToDatabase(@RequestBody User user, @RequestParam("file")MultipartFile file) {
        uploadImage(file, user.getUsername());
        if (userData.getUser(user.getUsername()) == null) {
            if (userData.addUser(user)) {
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        } else {
            if (userData.updateUser(user)) {
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping("/remove")
    public ResponseEntity removeUserFromDatabase(@RequestBody String username) {
        if (userData.removeUser(username)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addImage")
    public ResponseEntity uploadImageForUser(@RequestParam("userImage") MultipartFile file, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        uploadImage(file, user.getUsername());
        return new ResponseEntity(HttpStatus.OK);
    }

    private void uploadImage(MultipartFile file, String username) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                if (!PROFILE_DIR.exists()) {
                    PROFILE_DIR.mkdirs();
                }

                // Create the file on server
                File serverFile = new File(PROFILE_DIR.getAbsolutePath() + File.separator + username + ".png");
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @RequestMapping("/image/{username}")
    public byte[] getProfileImage(@PathVariable("username") String username) throws IOException {
        File image = new File(PROFILE_DIR.getAbsolutePath() + File.separator + username + ".png");
        return Files.readAllBytes(image.toPath());
    }

//    @GetMapping("/{username}")
//    public String showUser(@PathVariable("username") String username, Model model){
//        User user = userData.getUser(username);
//        model.addAttribute("user", user);
//        return "/user/show";
//    }
//
//    @GetMapping("/showAll")
//    public String allUsers(Model model){
//        List<User> userList = userData.getAllUsers();
//        model.addAttribute(userList);
//        return "/user/showAll";
//    }
//
//    @GetMapping("/test")
//    public String testPage(){
//        return "/user/testPage";
//    }
}
