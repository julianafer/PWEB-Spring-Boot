package br.hall.healhub.api.service;

import br.hall.healhub.api.repositories.DiariaRepository;
import jakarta.persistence.EntityNotFoundException;
// import br.hall.healhub.api.repositories.UsuarioRepository;
import br.hall.healhub.api.model.Diaria;
// import br.hall.healhub.api.model.Usuario;
import br.hall.healhub.api.model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
// import java.util.Optional;

@Service
public class DiariaService {

    private DiariaRepository diariaRepository;

    @Autowired
     public DiariaService(DiariaRepository diariaRepository) {
         this.diariaRepository = diariaRepository;
     }

    public List<Diaria> getDiarias() {
        return this.diariaRepository.findAll();
    }

    public Diaria getDiariaPorId(Long idDiaria) {
        return this.diariaRepository.findById(idDiaria).orElse(null);
    }

    @Transactional
    public Diaria inserirOuAtualizar(Diaria diaria) {
        Diaria diariaInserida = this.diariaRepository.save(diaria);

        if (diariaInserida.getCoposDAgua().intValue() < 0) {
            throw new RuntimeException("Quantidade de copos inválida");
        }
        return diariaInserida;
    
    }

    public Diaria associarUsuarioDiaria(Long diariaId, Usuario usuario) {
       Diaria diaria = diariaRepository.findById(diariaId)
               .orElseThrow(() -> new EntityNotFoundException("Diaria não encontrada"));
        

       diaria.setUsuario(usuario);
       return diaria;
   }


    public void apagar(Long id) {
        this.diariaRepository.deleteById(id);
    }
}
