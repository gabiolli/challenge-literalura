package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNomeAutorIgnoreCase(String nomeAutor);

    @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :anoBuscado AND (a.anoFalecimento IS NULL OR a.anoFalecimento >= :anoBuscado)")
    List<Autor> buscarAutoresVivosNoAno(Integer anoBuscado);
}