package com.trippaw.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class UserUpdateRequestDTO {

    private String name;
    private String phone;
    private String profileImage;
    private LocalDate birthDate;

}
