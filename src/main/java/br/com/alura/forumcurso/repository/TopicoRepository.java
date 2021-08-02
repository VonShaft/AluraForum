package br.com.alura.forumcurso.repository;

import br.com.alura.forumcurso.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    List<Topico> findByCurso_Nome(String nomeCurso);

//    @Query("SELECT t FROM Topico t WHERE Topico.curso.nome = :nomeCurso")
//    List<Topico> carregarPorNomeDoCurso(@Param("nomeCurso") String nomeCurso);
}
