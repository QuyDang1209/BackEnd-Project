package com.cg.spb_houseforrent.controller;

import com.cg.spb_houseforrent.model.User;
import com.cg.spb_houseforrent.model.dto.UserDTO;
import com.cg.spb_houseforrent.model.dto.res.RentalHistoryDTORes;
import com.cg.spb_houseforrent.model.dto.res.UserActiveRes;
import com.cg.spb_houseforrent.repository.IUsersRepository;
import com.cg.spb_houseforrent.service.IUserService;
import com.cg.spb_houseforrent.service.RentalService;
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
    @Autowired
    private RentalService rentalService;
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
    public ResponseEntity<?> changePassword(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        try {
            User user = userService.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
            if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
                return new ResponseEntity<>("Invalid password", HttpStatus.BAD_REQUEST);
            }
            userService.updatePassword(id, userDTO.getPassword());
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
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
    @GetMapping("/active/{email}")
    private ResponseEntity<?> checkActiveById(@PathVariable String email){
        Optional<User> userOptional = Optional.of(userService.findByEmail(email).get());
        User user = userOptional.get();
        boolean isActive = user.getActive().getId() == 2;
        if(isActive){
            return new ResponseEntity<>(new UserActiveRes(false),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new UserActiveRes(true),HttpStatus.OK);
        }
    }
    @GetMapping("/filter")
    public Iterable<User> getUsersByRole(@RequestParam("roleId") Long roleId) {
        return userService.getUsersByRoleId(roleId);
    }
    @GetMapping("/rental-history")
    public List<RentalHistoryDTORes> getUserRentalHistory(@RequestParam User users) {
        return rentalService.getUserRentalHistory(users);
    }

    @PostMapping("/cancel-rental")
    public void cancelRental(@RequestBody Long rentalId) {
        rentalService.cancelRental(rentalId);
    }
}
