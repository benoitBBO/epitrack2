package org.example.exposition.configSecurity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JWTTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String bearerToken = request.getHeader("Authorization");
        if (bearerToken==null || !bearerToken.startsWith("Bearer ")){
            System.out.println("JWTToken token null");
            filterChain.doFilter(request, response);
            return;
        }
        //décode token
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(Constants.SECRET)).build();
        String token = bearerToken.substring("Bearer ".length());
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();

        //on récupère les rôles
        List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (String r : roles){
            authorities.add(new SimpleGrantedAuthority(r));
        }

        //authentifie l'utilisateur
        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(user);
        filterChain.doFilter(request, response);

    }
}
