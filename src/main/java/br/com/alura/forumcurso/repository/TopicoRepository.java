package br.com.alura.forumcurso.repository;

import br.com.alura.forumcurso.modelo.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


//JpaRepository <Classe, Tipo da chave primária>
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Page<Topico> findByCurso_Nome(String nomeCurso, Pageable pageable);

//    @Query("SELECT t FROM Topico t WHERE Topico.curso.nome = :nomeCurso")
//    List<Topico> carregarPorNomeDoCurso(@Param("nomeCurso") String nomeCurso);
}
