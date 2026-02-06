package com.joaopedroamaral.taskManager.Service;

import com.joaopedroamaral.taskManager.DTO.*;
import com.joaopedroamaral.taskManager.Entity.Company;
import com.joaopedroamaral.taskManager.Entity.VerificationToken;
import com.joaopedroamaral.taskManager.Repository.CompanyRepository;
import com.joaopedroamaral.taskManager.Repository.UserRepository;
import com.joaopedroamaral.taskManager.Repository.VerificationTokenRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegisterService {

    private final CompanyRepository companyRepository;
    private final CompanyService companyService;
    private final VerificationTokenRepository tokenRepository;
    private final EmailService emailService;
    private final VerificationTokenService verificationTokenService;
    private final UserService userService;
    private final UserRepository userRepository;

    public RegisterService(CompanyRepository companyRepository,
                           CompanyService companyService,
                           VerificationTokenRepository tokenRepository,
                           EmailService emailService,
                           VerificationTokenService verificationTokenService,
                           UserService userService, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.companyService = companyService;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.verificationTokenService = verificationTokenService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Transactional
    public void registerUserAndCompany(RegisterRequestDTO dto){
        register(dto.user());
        registerCompany(dto.company());
    }

    private void registerCompany(CompanyRequestDTO dto){
        companyService.create(dto);
    }

    private void register(UserRequestDTO dto) {

        UserResponseDTO user = userService.register(dto);

        String token = verificationTokenService.createToken();

        VerificationToken verificationToken = new VerificationToken(token, LocalDateTime.now(), user.id());

        tokenRepository.save(verificationToken);

        emailService.sendEmail(user.email(), token);
    }

    public void createUser(UserRequestDTO dto){

        UserResponseDTO user = userService.createUser(dto);

        String token = verificationTokenService.createToken();

        VerificationToken verificationToken = new VerificationToken(token, LocalDateTime.now(), user.id());

        tokenRepository.save(verificationToken);

        emailService.sendEmail(user.email(), token);

        verificationTokenService.Authenticate(token, user.email());
    }
}
