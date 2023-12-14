package br.hall.healhub.service;

import br.hall.healhub.repositories.DiiariaRepository;
import br.hall.healhub.model.Diaria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiariaService {

   @Autowired
   private DiariaRepository diariaRepository;

   public List<Diaria> getDiarias() {
       return this.diariaRepository.findAll();
   }

   public Diaria getDiariaPorId(Long idDiaria) {
       return this.diariaRepository.findById(idDiaria).orElse(null);
   }

   @Transactional
   public Diaria inserirOuAtualizar(Diaria diaria) {
       Diaria diariaInserida = this.diariaRepository.save(diaria);
       if (diaria.getCoposDAgua() < 0) {
           throw new RuntimeException("Quantidade de copos inválida");
       }
       return usuarioInserido;
   }

   public void apagar(Long id) {
       this.diarioRepository.deleteById(id);
   }
}
