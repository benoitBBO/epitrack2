package org.example.exposition.configSecurity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.exposition.user.dto.UserLoginDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        UserLoginDto userLoginDto = null;
        try {
            userLoginDto = new ObjectMapper().readValue(request.getInputStream(), UserLoginDto.class);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(),
                        userLoginDto.getPassword()));
    }


    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        User springUser = (User) authResult.getPrincipal();
        List<String> roles = new ArrayList<>();
        springUser.getAuthorities().forEach(au -> {roles.add(au.getAuthority());});
        String jwt = JWT.create().withSubject(springUser.getUsername())
                .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
                .sign(Algorithm.HMAC256(Constants.SECRET));

        //objet json pour pouvoir mettre le jeton et le username dans le body
        Map<String, Object> body = new HashMap<>();
        body.put("token", "Bearer "+jwt);
        body.put("username", springUser.getUsername());
        System.out.println("succesfullAuthentication ; token = "+jwt);
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType("application/json");
    }
}
