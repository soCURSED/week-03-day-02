package com.example.hmd2.Controller;

import com.example.hmd2.Model.Logs;
import com.example.hmd2.Model.User;
import com.example.hmd2.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getALlUsers() {
        return ResponseEntity.status(200).body(userService.getAllTheUsers());
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody @Valid User user, Errors errors) {
        System.out.println("11111111111111111111");
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        System.out.println("222222222222222222222222");
        userService.addUser(user);
        System.out.println("333333333333333333333");

        return ResponseEntity.status(200).body("Student Added!");
    }

    //Create endpoint that takes users id and return the user with this id
    @GetMapping("/get-by-id")
    public ResponseEntity<User> getUserByID(@RequestParam Integer id) {
        return ResponseEntity.status(200).body(userService.getUserByID(id));
    }

    //Create endpoint that takes email and return the user with this email
    @GetMapping("/get-by-email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.status(200).body(userService.getUserByEmail(email));
    }

    //Create endpoint that takes age and return the user with older than this age
    @GetMapping("/get-by-age")
    public ResponseEntity<User> getByAge(@RequestParam Integer age) {
        return ResponseEntity.status(200).body(userService.getByAge(age));
    }

    //Create endpoint that takes role and return the total count having this role
    @GetMapping("/get-by-role/{role}")
    public ResponseEntity getByRole(@PathVariable String role) {
        return ResponseEntity.status(200).body(userService.getByRole(role));
    }

    //Create endpoint that takes username and password and check if it's correct or not
    @GetMapping("/login")
    public ResponseEntity checkInfo(@RequestParam String userName, @RequestParam String password) {


        Boolean isLoggedIn= userService.checkUserPass(userName, password);
        if(!isLoggedIn){
            return ResponseEntity.status(400).body("not logged in");
        }
        return ResponseEntity.status(200).body("Checking..... Correct");


    }

    //Create endpoint that takes userid and user object , update the olduser object with the new object after verifying the userid belong to admin user
    @PostMapping("{id}")
    public ResponseEntity addNewUser(@PathVariable Integer id,@RequestBody User user, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isUpdated = userService.updateOldUser(id,user);
        if (!isUpdated){
            return ResponseEntity.status(400).body("User not updated");
        }
        return ResponseEntity.status(200).body("User updated");

    }

    //Create endpoint that takes newPassword and userid , update the olduser password with the new Password
    @PostMapping("/new-password{id}")
    public ResponseEntity updatePass(@PathVariable Integer id, @RequestBody String pass, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.updatePass(id,pass);
        return ResponseEntity.status(200).body("User Password updated");

    }


    //Create endpoint that takes joiningYear and userid , and return if this user joined with the date that being sent or not
    @GetMapping("/checkifDate{id}")
    public ResponseEntity checkifDate(@PathVariable Integer id, @RequestBody LocalDate joiningYear, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.checkifDate(id,joiningYear );
        return ResponseEntity.status(200).body("Done!");

    }


//    Create endpoint that takes joiningYear and return all the users who joined in this date or after
    @GetMapping("/afterJoinYear")
    public ResponseEntity<List<User>> joinedAfterJoinYear(@RequestBody LocalDate joiningYear){

        userService.afterJoinYear(joiningYear );
        return ResponseEntity.status(200).body(userService.afterJoinYear(joiningYear));

    }


    //    Create endpoint that takes age and joiningYear and return all the users who joined in this date or before and all have the same age
    @GetMapping("/AgeYear")
    public ResponseEntity<List<User>> SameAgeandYearBefore(@RequestBody Integer age,@RequestBody LocalDate joiningYear){

        userService.AgeAndYear(age,joiningYear);
        return ResponseEntity.status(200).body(userService.AgeAndYear(age,joiningYear));

    }


    }









