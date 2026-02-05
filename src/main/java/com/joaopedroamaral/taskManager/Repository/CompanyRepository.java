package com.joaopedroamaral.taskManager.Repository;

import com.joaopedroamaral.taskManager.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByCompanyName(String companyName);
}
