package br.com.alura.forumcurso.controller;

import br.com.alura.forumcurso.config.security.TokenService;
import br.com.alura.forumcurso.controller.dto.TokenDto;
import br.com.alura.forumcurso.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    // No AuthenticationManager o SpringBoot não consegue fazer a injeção de dependência por isso ela foi feita no
    // SecurityConfigurations em config.security
    private AuthenticationManager authManager;


    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();

        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));

        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
