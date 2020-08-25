package com.capstone.remotestart.repositories;

import com.capstone.remotestart.models.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, String> {
    PasswordResetToken findByPasswordResetToken(String passwordResetToken);
}
