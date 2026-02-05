package com.joaopedroamaral.taskManager.Repository;

import com.joaopedroamaral.taskManager.Entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, String> {
}
