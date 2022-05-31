package com.example.hmd2.Service;

import com.example.hmd2.Model.User;
import com.example.hmd2.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor
public class UserService {

    private ArrayList<User> userList;

    public ArrayList<User> getuser() {
        return userList;
    }

    private final UserRepo userRepo;

    public List<User> getAllTheUsers() {
        return userRepo.findAll();
    }

    public void addUser(User user) {
        userRepo.save(user);


    }

    public User getUserByID(Integer id) {
        return userRepo.findUserByid(id);

    }

    public User getUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    public User getByAge(Integer age) {
        return userRepo.findUserByAge(age);

    }


    public Integer getByRole(String role) {
        return userRepo.countAllByRole(role);
    }

    private User getUserId(Integer getId) {
        return getUserByID(getId);
    }


    public Boolean checkUserPass(String userName, String password) {
        return userRepo.existsUserByUserNameAndPassword(userName, password);

    }

    public Boolean updateOldUser(Integer id, User user) {
        boolean isUserAdmin = userRepo.existsUserByIdAndRole(id,"Admin");
        if (!isUserAdmin){
            return false;
        }
        User oldUser = userRepo.findUserByid(id);
        oldUser.setAge(user.getAge());
        oldUser.setUserName(user.getUserName());
        oldUser.setEmail(user.getEmail());
        oldUser.setJoiningYear(user.getJoiningYear());
        oldUser.setPassword(user.getPassword());
        oldUser.setRole(user.getRole());
        userRepo.save(user);
        return true;
    }

    public void updatePass(Integer id, String pass) {

        User find_user = userRepo.findById(id).get();
        find_user.setPassword(pass);
        userRepo.save(find_user);




    }

    public Boolean checkifDate(Integer id, LocalDate joiningYear) {
        User find_user = userRepo.findById(id).get();
        Boolean checkifDate = userRepo.existsUserByIdAndJoiningYearIsOrJoiningYearIsBefore(id,joiningYear,joiningYear);
      return checkifDate;


    }

    public List<User> afterJoinYear(LocalDate joiningYear) {
        return (List<User>) userRepo.getUserByJoiningYearIsOrJoiningYearAfter(joiningYear,joiningYear);


    }

    public List<User> AgeAndYear(Integer age, LocalDate joiningYear) {
        return (List<User>) userRepo.getUsersByAgeIsAgeAndJoiningYearOrJoiningYearBefore(age,joiningYear);

    }
}








