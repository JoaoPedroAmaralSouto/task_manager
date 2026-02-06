package com.joaopedroamaral.taskManager.Service;

import com.joaopedroamaral.taskManager.DTO.UserResponseDTO;
import com.joaopedroamaral.taskManager.DTO.VerificationTokenDTO;
import com.joaopedroamaral.taskManager.Entity.User;
import com.joaopedroamaral.taskManager.Entity.VerificationToken;
import com.joaopedroamaral.taskManager.Repository.UserRepository;
import com.joaopedroamaral.taskManager.Repository.VerificationTokenRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class VerificationTokenService {

    private final UserRepository userRepository;

    private final VerificationTokenRepository verificationTokenRepository;

    public VerificationTokenService(UserRepository userRepository, VerificationTokenRepository verificationTokenRepository){
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public String createToken(){
        return UUID.randomUUID().toString();
    }

    public void Authenticate(String token, String email){
        VerificationToken verificationToken = verificationTokenRepository.findById(token).
                orElseThrow(()-> new RuntimeException("Invalid token"));
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEnabled(true);
        userRepository.save(user);
        verificationTokenRepository.deleteById(verificationToken.getToken());
    }

    @Scheduled(fixedRate = 300000)
    public void verifyIfTokenExpired(){
        List<VerificationToken> allTokens = verificationTokenRepository.findAll();
        for(VerificationToken token : allTokens){
            if (LocalDateTime.now().isAfter(token.getLocalDateTime().plusMinutes(15)))
                verificationTokenRepository.deleteById(token.getToken());
        }
    }
}
