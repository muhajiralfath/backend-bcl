package com.xfour.businesscapitalloan;

import com.xfour.businesscapitalloan.model.request.AuthRequest;
import com.xfour.businesscapitalloan.service.AdminService;
import com.xfour.businesscapitalloan.service.AuthService;
import com.xfour.businesscapitalloan.service.UserCredentialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AppAutoInit implements CommandLineRunner {
    private final AdminService adminService;
    private final AuthService authService;
    private final UserCredentialService userCredentialService;

    @Value(value = "${admin.email}")
    String email;
    @Value(value = "${admin.password}")
    String password;

    @Override
    public void run(String... args) throws Exception {
        log.info("Checking Admin empty or not");
        Boolean isAdminEmpty = adminService.isAdminEmpty();
        Boolean adminEmpty = userCredentialService.isAdminEmpty(email);

        if (isAdminEmpty && adminEmpty) {
            log.info("Admin is empty, Creating Admin . . . ");
            authService.registerAdmin(AuthRequest.builder()
                    .email(email)
                    .password(password)
                    .build());
        log.info("First Admin was created. login with email : admin@email.com | pass : passwordadmin");
        } else {
            log.info("Admin already create, login with email : admin@email.com | pass : passwordadmin");
        }
    }
}
