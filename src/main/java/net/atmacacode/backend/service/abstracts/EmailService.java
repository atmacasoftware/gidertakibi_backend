package net.atmacacode.backend.service.abstracts;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    @PostConstruct
    void initialize();

    void sendActivationEmail(String email, String activationToken);
}
