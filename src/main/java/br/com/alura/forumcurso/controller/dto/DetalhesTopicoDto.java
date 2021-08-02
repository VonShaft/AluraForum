package br.com.alura.forumcurso.controller.dto;

import br.com.alura.forumcurso.modelo.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetalhesTopicoDto {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private StatusTopico status;
    private String autor;
    private String nomeCurso;
    private List<RespostaDto> respostas;

    public DetalhesTopicoDto(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.status = topico.getStatus();
        this.autor = topico.getAutor().getNome();
        this.nomeCurso = topico.getCurso().getNome();
        this.respostas = new ArrayList<>();
        this.respostas.addAll(topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public String getAutor() {
        return autor;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public List<RespostaDto> getRespostas() {
        return respostas;
    }
}
