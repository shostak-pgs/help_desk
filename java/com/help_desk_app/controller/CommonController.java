package com.help_desk_app.controller;

import com.help_desk_app.config.security.CustomUserPrincipal;
import com.help_desk_app.converter.UserConverter;
import com.help_desk_app.dto.UserDto;
import com.help_desk_app.entity.User;
import com.help_desk_app.service.CommonService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/")
public class CommonController {

    private final CommonService commonService;
    private final AuthenticationManager authenticationManager;
    private final UserConverter userConverter;

    public CommonController(CommonService commonService,
                            UserConverter userConverter,
                            AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.userConverter = userConverter;
        this.commonService = commonService;
    }

    @GetMapping(value = "/common", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getComments() {
        return ResponseEntity.ok(commonService.getFixedVales());
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserDto> authenticate(@RequestParam("username") final String username, @RequestParam("password") final String password) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(token));
            User user =  ((CustomUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
            return ResponseEntity.ok(userConverter.toDto(user));

}

}
