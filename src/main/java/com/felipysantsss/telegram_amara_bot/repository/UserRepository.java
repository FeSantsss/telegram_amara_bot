package com.felipysantsss.telegram_amara_bot.repository;

import com.felipysantsss.telegram_amara_bot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByChatId(String chatId);
}
