package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.User;
import com.cg.spb_houseforrent.model.dto.UserDTO;
import com.cg.spb_houseforrent.repository.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserService extends IGenericService<User> {
    User saveUserDTO (UserDTO userDTO);
    void changeRole(List<UserDTO> userDTOS);

}
