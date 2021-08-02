package br.com.alura.forumcurso.controller;

import br.com.alura.forumcurso.controller.dto.DetalhesTopicoDto;
import br.com.alura.forumcurso.controller.dto.TopicoDto;
import br.com.alura.forumcurso.controller.form.TopicoForm;
import br.com.alura.forumcurso.modelo.Topico;
import br.com.alura.forumcurso.repository.CursoRepository;
import br.com.alura.forumcurso.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> lista(String nomeCurso) {
        if (nomeCurso == null) {
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDto.converter(topicos);
        }
        List<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso);
        return TopicoDto.converter(topicos);
    }

    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriBuilder) {
        Topico topico = topicoForm.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public DetalhesTopicoDto detalhar(@PathVariable Long id) {
        Topico topico =  topicoRepository.getById(id);
        return new DetalhesTopicoDto(topico);
    }
}
