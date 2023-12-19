package br.hall.healhub.api.repositories;

import br.hall.healhub.api.model.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;


// import java.util.List;

public interface UsuarioRepository extends JpaRepository <Usuario, Long>{
    Optional<Usuario> findByNome(String nome);
    boolean existsByNome(String nome);
      
}