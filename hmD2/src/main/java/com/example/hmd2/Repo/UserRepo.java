package com.example.hmd2.Repo;

import com.example.hmd2.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository

public interface UserRepo extends JpaRepository <User,Integer> {

User findUserByid(Integer id);
User findByid(Integer id);


User findUserByEmail(String email);

    @Query("SELECT a FROM User a WHERE a.age > ?1")
User findUserByAge(Integer age);

    User findUserByPassword(String password);
    User findUserByUserName(String userName);

    Integer countAllByRole(String role);
    Boolean existsUserByUserNameAndPassword(String userName, String password);

    boolean existsUserByIdAndRole(Integer id, String role);
    Boolean existsUserByIdAndJoiningYearIsOrJoiningYearIsBefore(Integer id, LocalDate joiningYear,LocalDate joiningYearr);

    User getUserByJoiningYearIsOrJoiningYearAfter(LocalDate joiningYear,LocalDate joiningYearr);

    User getUsersByAgeIsAgeAndJoiningYearOrJoiningYearBefore(Integer age, LocalDate joiningYear);

}
