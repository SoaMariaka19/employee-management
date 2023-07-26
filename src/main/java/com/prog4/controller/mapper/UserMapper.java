package com.prog4.controller.mapper;

import com.prog4.controller.model.ModelUSer;
import com.prog4.entity.Member;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserMapper {
    private BCryptPasswordEncoder passwordEncoder;

    public Member toEntity(ModelUSer modelUSer){
        return Member.builder()
                .enabled(modelUSer.isEnabled())
                .password(passwordEncoder.encode(modelUSer.getPassword()))
                .username(modelUSer.getUsername())
                .build();
    }
}
