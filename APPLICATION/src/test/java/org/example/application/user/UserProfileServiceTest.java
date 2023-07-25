package org.example.application.user;

import org.example.domaine.exceptions.InputMissingException;
import org.example.domaine.user.Role;
import org.example.domaine.user.UserProfile;
import org.example.infrastructure.repository.user.IUserProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {UserProfileServiceImpl.class, IUserProfileRepository.class})
public class UserProfileServiceTest {
    @MockBean
    IUserProfileRepository userProfileRepository;
    @Autowired
    IUserProfileService userProfileService;

    UserProfile mockedUserProfile;

    //@BeforeEach
    //public void init(){
    //    mockedUserProfile = new UserProfile();
    //    mockedUserProfile.setId(65L);
    //    //...
    //    when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(mockedRole));
    //}
    @Test
    public void test_should_inputMissingException_when_findByUsernameEmpty(){
        assertThrows(InputMissingException.class, () -> userProfileService.findUserProfileByUsername(""));
    }
}
