package com.felipysantsss.telegram_amara_bot.model;


import com.felipysantsss.telegram_amara_bot.enums.Plans;
import com.felipysantsss.telegram_amara_bot.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

// usuario que irá comprar os planos e receber as fotos.
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String chatId;

    private String userName;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Enumerated(EnumType.STRING)
    private Plans userPlan;

    private LocalDateTime planExpiresAt;

}
