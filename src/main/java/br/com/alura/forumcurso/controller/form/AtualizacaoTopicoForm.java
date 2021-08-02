package br.com.alura.forumcurso.controller.form;

import br.com.alura.forumcurso.controller.dto.TopicoDto;
import br.com.alura.forumcurso.modelo.Topico;
import br.com.alura.forumcurso.repository.TopicoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtualizacaoTopicoForm {

    @NotNull
    @NotEmpty
    @Length(min = 5, max = 15)
    private String titulo;
    @NotNull
    @NotEmpty
    @Length(min = 10, max = 25)
    private String mensagem;


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Topico atualizar(Long id, TopicoRepository topicoRepository) {
        Topico topico = topicoRepository.getById(id);
        topico.setMensagem(this.mensagem);
        topico.setTitulo(this.titulo);

        return topico;
    }
}
