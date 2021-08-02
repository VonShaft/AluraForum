package br.com.alura.forumcurso.controller.dto;

import br.com.alura.forumcurso.modelo.Resposta;
import br.com.alura.forumcurso.modelo.Usuario;

import java.time.LocalDateTime;

public class RespostaDto {

    private Long id;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;
    private Boolean solucao;

    public RespostaDto(Resposta resposta) {
        this.id = resposta.getId();
        this.mensagem = resposta.getMensagem();
        this.dataCriacao = resposta.getDataCriacao();
        this.nomeAutor = resposta.getAutor().getNome();
        this.solucao = resposta.getSolucao();
    }

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public Boolean getSolucao() {
        return solucao;
    }
}
