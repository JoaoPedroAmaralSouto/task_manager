package com.joaopedroamaral.taskManager.Service;

import com.joaopedroamaral.taskManager.DTO.CompanyRequestDTO;
import com.joaopedroamaral.taskManager.DTO.CompanyResponseDTO;
import com.joaopedroamaral.taskManager.Entity.Company;
import com.joaopedroamaral.taskManager.Repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public CompanyResponseDTO create(CompanyRequestDTO dto){
        if(companyRepository.findByCompanyName(dto.companyName()).isPresent()){
            throw new RuntimeException("Company Already Exists");
        }
        Company company = new Company(dto.companyName(), LocalDateTime.now());

        Company savedCompany = companyRepository.save(company);

        return new CompanyResponseDTO(savedCompany.getId(), savedCompany.getCompanyName(), savedCompany.getCreatedAt());
    }
}
