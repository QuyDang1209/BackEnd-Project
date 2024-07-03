package com.cg.spb_houseforrent.controller;

import com.cg.spb_houseforrent.model.User;
import com.cg.spb_houseforrent.model.dto.UserDTO;
import com.cg.spb_houseforrent.repository.IUsersRepository;
import com.cg.spb_houseforrent.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping("")
    public ResponseEntity<Iterable<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/create")
    private ResponseEntity<?> save(@RequestBody UserDTO userDTO){
        userService.saveUserDTO(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    private ResponseEntity<?> findById(@PathVariable Long id){
        Optional<User> userOptional = Optional.ofNullable(userService.findById(id).get());
        if(!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(userOptional.get(),HttpStatus.OK);
        }
    }
    @PatchMapping("/edit/{id}")
    private ResponseEntity<?> edit(@PathVariable Long id, @RequestBody UserDTO userDTO){
        Optional<User> userOptional = Optional.ofNullable(userService.findById(id).get());
        if(!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            userDTO.setId(id);
            userService.saveUserDTO(userDTO);
            return new ResponseEntity<>(userOptional.get(),HttpStatus.OK);
        }
    }
    @PatchMapping("/change-password/{id}")
    private ResponseEntity<?> changePassword(@PathVariable Long id,@RequestBody UserDTO userDTO){
        Optional<User> userOptional = Optional.ofNullable(userService.findById(id).get());
        if(!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            userDTO.setId(id);
            userService.saveUserDTO(userDTO);
            return new ResponseEntity<>(userOptional.get(),HttpStatus.OK);
        }
    }
    @PatchMapping("/change-role")
    private ResponseEntity<?> changeRole(@RequestBody List<UserDTO> userDTO){
        try{
            userService.changeRole(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/change-active")
    private ResponseEntity<?> changeActive(@RequestBody List<UserDTO> userDTO){
        try{
            userService.changeActive(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
