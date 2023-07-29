package org.example.application.role;

import org.example.domaine.user.Role;
import org.example.infrastructure.repository.role.IRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {RoleServiceImpl.class, IRoleRepository.class})
public class RoleServiceTest {
    @MockBean
    IRoleRepository roleRepository;

    @Autowired
    IRoleService service;

    Role mockedRole;

    @BeforeEach
    public void init(){
        mockedRole = new Role();
        mockedRole.setId(2L);
        mockedRole.setName("ROLE_USER");

        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(mockedRole));
    }
    @Test
    public void test_should_findRoleUser_when_findByNameRoleUser(){
        assertThat(service.findByName("ROLE_USER")).isEqualTo(mockedRole);

    }
}
