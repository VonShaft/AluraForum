package br.com.alura.forumcurso.controller.dto;

public class TokenDto {
    private String token;
    private String tipoValidation;

    public TokenDto(String token, String tipoValidation) {
        this.token = token;
        this.tipoValidation = tipoValidation;
    }

    public String getToken() {
        return token;
    }

    public String getTipoValidation() {
        return tipoValidation;
    }
}
