package br.com.alura.forumcurso.controller;

import br.com.alura.forumcurso.controller.dto.DetalhesTopicoDto;
import br.com.alura.forumcurso.controller.dto.TopicoDto;
import br.com.alura.forumcurso.controller.form.AtualizacaoTopicoForm;
import br.com.alura.forumcurso.controller.form.TopicoForm;
import br.com.alura.forumcurso.modelo.Topico;
import br.com.alura.forumcurso.repository.CursoRepository;
import br.com.alura.forumcurso.repository.TopicoRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<List<TopicoDto>> lista(String nomeCurso) {
        List<Topico> topicos;
        if (nomeCurso == null) {
            topicos = topicoRepository.findAll();
        } else {
            topicos = topicoRepository.findByCurso_Nome(nomeCurso);
        }
        return ResponseEntity.ok(TopicoDto.converter(topicos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesTopicoDto> detalhar(@PathVariable Long id) {
        Optional<Topico> optional =  topicoRepository.findById(id);
        return optional.map(topico -> ResponseEntity.ok(new DetalhesTopicoDto(topico))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriBuilder) {
        Topico topico = topicoForm.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm atualizacaoTopicoForm) {
        Optional<Topico> optional =  topicoRepository.findById(id);

        if (optional.isPresent()) {
            Topico topico = atualizacaoTopicoForm.atualizar(id, topicoRepository);
            return ResponseEntity.ok(new TopicoDto(topico));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Topico> optional =  topicoRepository.findById(id);
        if (optional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
