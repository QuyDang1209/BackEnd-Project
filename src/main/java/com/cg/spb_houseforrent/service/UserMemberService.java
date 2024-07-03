package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.ActiveStatus;
import com.cg.spb_houseforrent.model.Role;
import com.cg.spb_houseforrent.model.User;
import com.cg.spb_houseforrent.model.dto.UserDTO;
import com.cg.spb_houseforrent.repository.IActiveStatusRepository;
import com.cg.spb_houseforrent.repository.IRolesRepository;
import com.cg.spb_houseforrent.repository.IUsersRepository;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserMemberService implements IUserService{
    @Autowired
    private IUsersRepository usersRepository;
    @Autowired
    private IActiveStatusRepository activeStatusRepository;
    @Autowired
    private IRolesRepository rolesRepository;

    @Override
    public Iterable<User> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return usersRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return usersRepository.save(user);
    }

    @Override
    public void remove(Long id) {
    usersRepository.deleteById(id);
    }


    @Override
    public User saveUserDTO(UserDTO userDTO) {
        User user = null;
        if(userDTO.getId() == null){
            user = new User();
        }
        else {
            user = usersRepository.findById(userDTO.getId()).get();
        }
        user.setName(userDTO.getName());
        user.setDob(userDTO.getDob());
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getEmail());
        user.setPassword( BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt(12)) );
        user.setPhone(userDTO.getPhone());
        user.setActive(activeStatusRepository.findById(userDTO.getActive()).get());
        Set<Role> roles = new HashSet<>();
        roles.add(rolesRepository.findById(userDTO.getRole()).get());
        user.setRole(roles);
        usersRepository.save(user);
        return user ;
    }

    @Override
    public void changeRole(List<UserDTO> userDTOS) {
        for(UserDTO us : userDTOS){
            User user = usersRepository.findById(us.getId()).orElse(null);
            if(user != null){
                Role role = rolesRepository.findById(us.getRole()).orElse(null);
                Set<Role> roles = new HashSet<>();
                roles.add(role);
                user.setRole(roles);
                usersRepository.save(user);
            }
        }
    }
    @Override
    public void changeActive(List<UserDTO> userDTOS) {
        for(UserDTO us : userDTOS){
            User user = usersRepository.findById(us.getId()).orElse(null);
            if(user != null){
                ActiveStatus activeStatus = activeStatusRepository.findById(us.getActive()).orElse(null);
                user.setActive(activeStatus);
                usersRepository.save(user);
            }
        }
    }
}
