package br.hall.healhub.api.repositories;

import br.hall.healhub.api.model.Diaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface DiariaRepository extends JpaRepository <Diaria, Long>{

    
}