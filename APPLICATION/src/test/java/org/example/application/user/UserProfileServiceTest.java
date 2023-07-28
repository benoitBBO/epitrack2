package org.example.application.user;

import org.example.domaine.exceptions.InputMissingException;
import org.example.domaine.exceptions.ResourceAlreadyExistsException;
import org.example.domaine.exceptions.ResourceNotFoundException;
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

    @BeforeEach
    public void init(){
        mockedUserProfile = new UserProfile();
        mockedUserProfile.setId(65L);
        mockedUserProfile.setUserName("TestUsername");
        mockedUserProfile.setPassword("TestPassword");
        mockedUserProfile.setEmail("test@test.fr");
        //...
        when(userProfileRepository.findByUserName("TestUsername")).thenReturn(Optional.of(mockedUserProfile));
        when(userProfileRepository.findByUserName("NotFoundUsername")).thenReturn(Optional.of(new UserProfile()));
    }
    @Test
    public void test_should_inputMissingException_when_findByUsernameEmpty(){
        assertThrows(InputMissingException.class,
                () -> userProfileService.findUserProfileByUsername(""));
    }
    @Test
    public void test_should_resourceNotFoundException_when_findByUsernameNotExist(){
        assertThrows(ResourceNotFoundException.class,
                () -> userProfileService.findUserProfileByUsername("NotFoundUserName"));
    }
    @Test
    public void test_should_return_UserProfile_when_findByUsernameOK(){
        assertThat(userProfileService.findUserProfileByUsername("TestUsername")).isEqualTo(mockedUserProfile);
    }

    @Test
    public void test_should_resourceAlreadyExistsException_when_createExistingUser(){
        assertThrows(ResourceAlreadyExistsException.class,
                () -> userProfileService.createUserProfile(mockedUserProfile));
    }
}
