package br.hall.healhub.api.service;

import br.hall.healhub.api.repositories.DiariaRepository;
import br.hall.healhub.api.repositories.UsuarioRepository;
import br.hall.healhub.api.model.CustomException;
import br.hall.healhub.api.model.Diaria;
import br.hall.healhub.api.model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
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
            usuarioRepository.save(usuario);
        }
        Diaria diariaInserida = this.diariaRepository.save(diaria);

        if (diariaInserida.getCoposDAgua().intValue() < 0) {
            throw new CustomException("Quantidade de copos inválida");
        }
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
            .orElseThrow(() -> new CustomException("Diaria não encontrada"));
    
        diaria.setUsuario(usuario);
        Diaria diariaAtualizada = diariaRepository.save(diaria);

        return diariaAtualizada;
}



    public void apagar(Long id) {
        this.diariaRepository.deleteById(id);
    }
}
