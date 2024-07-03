package com.cg.spb_houseforrent.config.service;

import com.cg.spb_houseforrent.config.UserPrinciple;
import com.cg.spb_houseforrent.model.User;
import com.cg.spb_houseforrent.repository.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private IUsersRepository userRepository;

    public User findUserByUsername(String email){
        return userRepository.findUserByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return UserPrinciple.build(userRepository.findUserByEmail(email));
    }
}
