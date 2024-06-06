package com.baeldung.crud;
import com.baeldung.crud.controllers.RegisterUserController;
import com.baeldung.crud.repositories.RegisterUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(RegisterUserController.class)
public class RegisterUserControllerTest {
    @Autowired
    private RegisterUserController registerUserController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegisterUserRepository registerUserRepository;


    @Test
    public void toLoginTest() throws Exception {
        /*RegisterUserDTO registerUserDTOTest = new RegisterUserDTO(1,"tianyue",
                "sun","sty1999","123456",
                20,"suntianyue@163.com");*/
        mockMvc.perform(post("/public/toLogin")
                .param("password","123456")
                                .param("username","zhangrui"))
                .andExpect(status().isOk());
           //   .andExpect(content().string("The user name or password is incorrect!"));
    }

}
