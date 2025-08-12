package com.example.MasterData.Controller;

import com.example.MasterData.Model.State;
import com.example.MasterData.Model.User;
import com.example.MasterData.Service.UserService;
import com.example.MasterData.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers(){
        try{
            List<User> users=userService.getAllUsers();
            ApiResponse<List<User>> response=new ApiResponse<>(true,"DATA FIELDS FETCHED SUCCESSFULLY!",users);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<List<User>> response=new ApiResponse<>(true,"DATA FIELDS NOT FETCHED!",null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user){

        try{
            User user1=userService.createUser(user);
            ApiResponse<User> response=new ApiResponse<>(true,"DATA FIELDS CREATED SUCCESSFULLY!",user1);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<User> response=new ApiResponse<>(false,"DATA FIELDS NOT CREATED!"+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable int id,@RequestBody User user){
        try{
            User user1=userService.updateUser(id,user);
            ApiResponse<User> response=new ApiResponse<>(true,"DATA FIELD UPDATED SUCCESSFULLY!",user1);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<User> response=new ApiResponse<>(false,"DATA FIELDS NOT UPDATED!"+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<User>> deleteUser(@PathVariable int id){
        try{
            User user=userService.deleteUser(id);
            ApiResponse<User> response=new ApiResponse<>(true,"DATA FIELD UPDATED SUCCESSFULLY!",user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<User> response=new ApiResponse<>(false,"DATA FIELDS NOT UPDATED! "+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
