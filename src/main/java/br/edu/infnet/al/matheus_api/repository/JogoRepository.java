package br.edu.infnet.al.matheus_api.repository;

import br.edu.infnet.al.matheus_api.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {

    List<Jogo> findByIsFinalizadoFalse();
    List<Jogo> findAllByOrderByNotaDesc();
}
