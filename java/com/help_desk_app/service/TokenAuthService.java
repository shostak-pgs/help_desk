package com.help_desk_app.service;

import com.help_desk_app.config.security.CustomUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class TokenAuthService {
    private static final String AUTH = "authorization";

    private TokenHandler tokenHandler;
    private UserDetailsService userDetailsService;

    public TokenAuthService(TokenHandler tokenHandler, CustomUserDetailsService userDetailsService) {
        this.tokenHandler = tokenHandler;
        this.userDetailsService = userDetailsService;
    }

    public Optional<UsernamePasswordAuthenticationToken> getAuthentication(HttpServletRequest request) {
        String authToken = getToken(request);
        System.out.println(authToken);

        if (authToken != null) {
            String email = TokenHandler.extractUserCredentials(authToken).get().getEmail();
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            if (tokenHandler.validateToken(authToken, userDetails)) {
                return Optional.of(new UsernamePasswordAuthenticationToken(
                        userDetails.getPassword(), userDetails.getAuthorities()));
            }
        }
        return Optional.empty();
    }

    private String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTH);
        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }

/*
        return Optional.ofNullable(request.getHeader(AUTH_HEADER_NAME))
                .flatMap(TokenHandler::extractUserCredentials)
                .flatMap( userDetailsService::loadUserByUsername(u))
                .map(UserAuthentication::new);

        if (StringUtils.hasText(authToken)) {
            String username = tokenHandler.extractUserCredentials(a)

            UserDetails userDetails = userDetailsService.loadUserByUsername(tokenHandler.extractUserCredentials);

            if (TokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,
                        userDetails.getPassword(), userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
    }*/


}
