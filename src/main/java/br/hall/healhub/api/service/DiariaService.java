package br.hall.healhub.api.service;

import br.hall.healhub.api.repositories.DiariaRepository;
import br.hall.healhub.api.repositories.UsuarioRepository;
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

@Service
public class DiariaService {

    private DiariaRepository diariaRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
     public DiariaService(DiariaRepository diariaRepository, UsuarioRepository usuarioRepository) {
         this.diariaRepository = diariaRepository;
         this.usuarioRepository = usuarioRepository;
     }

    public List<Diaria> getDiarias() {
        return this.diariaRepository.findAll();
    }

    public Diaria getDiariaPorId(Long idDiaria) {
        return this.diariaRepository.findById(idDiaria).orElse(null);
    }

    @Transactional
    public Diaria inserir(Diaria diaria, Usuario usuario) {
        if (usuario != null && usuario.getId() == null) {
            // Usuário não persistido, salve-o primeiro
            usuarioRepository.save(usuario);
        }

        Diaria diariaInserida = this.diariaRepository.save(diaria);

        if (diariaInserida.getCoposDAgua().intValue() < 0) {
            throw new RuntimeException("Quantidade de copos inválida");
        }

        // Chama o método para associar usuário à diária, se o usuário não for nulo
        if (usuario != null) {
            associarUsuarioDiaria(diariaInserida.getId(), usuario);
        }

        return diariaInserida;
    }

     @Transactional
    public Diaria atualizar(Diaria diaria){
        return this.diariaRepository.save(diaria);
    }


    @Transactional
    public Diaria associarUsuarioDiaria(Long diariaId, Usuario usuario) {
        Diaria diaria = diariaRepository.findById(diariaId)
            .orElseThrow(() -> new EntityNotFoundException("Diaria não encontrada"));
    
    // Associa o usuário à diária
    diaria.setUsuario(usuario);

    // Salva as alterações no banco de dados
    Diaria diariaAtualizada = diariaRepository.save(diaria);

    return diariaAtualizada;
}



    public void apagar(Long id) {
        this.diariaRepository.deleteById(id);
    }
}
