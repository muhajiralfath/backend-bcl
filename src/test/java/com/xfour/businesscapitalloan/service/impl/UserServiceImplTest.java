package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.security.UserSecurity;
import com.xfour.businesscapitalloan.service.FileService;
import com.xfour.businesscapitalloan.service.ProfilePictureService;
import com.xfour.businesscapitalloan.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureWebMvc
@AutoConfigureMockMvc
@Import({UserSecurity.class, /* Add other necessary imports here */})
class UserServiceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private ProfilePictureService profilePictureService;

    @MockBean
    private FileService fileService;

    @MockBean
    private UserSecurity userSecurity;


    @Test
    void deleteProfilePicture() throws Exception {

    }
}
