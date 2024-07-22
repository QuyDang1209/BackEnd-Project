package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.User;
import com.cg.spb_houseforrent.repository.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LandlordService {
    @Autowired
    private IUsersRepository usersRepository;
    @Autowired
    private EmailService emailService;
    public void approveLandlord(Long id, String reason) {
        User user = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Landlord not found"));
        usersRepository.save(user);

        String subject = "Landlord Registration Approved";
        String message = "Dear " + user.getName() + ",\n\nYour registration as a landlord has been approved.\n\nReason: " + reason;
        emailService.sendEmail(user.getEmail(), subject, message);
    }

    public void rejectLandlord(Long id, String reason) {
        User user = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Landlord not found"));

        if ("Rejected".equals(user.getActive())) {
            throw new IllegalStateException("Landlord is already rejected");
        }
        usersRepository.save(user);

        String subject = "Landlord Registration Rejected";
        String message = "Dear " + user.getName() + ",\n\nYour registration as a landlord has been rejected.\n\nReason: " + reason;
        emailService.sendEmail(user.getEmail(), subject, message);
    }

}
