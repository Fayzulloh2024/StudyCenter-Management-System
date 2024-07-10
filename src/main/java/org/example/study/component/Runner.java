package org.example.study.component;

import lombok.RequiredArgsConstructor;
import org.example.study.entity.Role;
import org.example.study.entity.User;
import org.example.study.repo.RoleRepo;
import org.example.study.repo.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Override
    public void run(String... args) throws Exception {
        if (ddl.equals("create")) {
            Role role1= Role.builder().name("ROLE_USER").build();
            Role role2=Role.builder().name("ROLE_ADMIN").build();
            roleRepo.save(role1);
            roleRepo.save(role2);
            userRepo.save(
                    User.builder()
                            .username("admin")
                            .password(passwordEncoder.encode("123"))
                            .role(role2)
                            .build()
            );
            userRepo.save(
                    User.builder()
                            .username("teacher")
                            .password(passwordEncoder.encode("123"))
                            .role(role1)
                            .build()
            );

        }
    }
}
