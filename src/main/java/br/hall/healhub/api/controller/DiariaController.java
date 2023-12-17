package br.hall.healhub.api.controller;

import br.hall.healhub.api.model.Diaria;
import br.hall.healhub.api.service.DiariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
public class DiariaController {

   @Autowired
   private DiariaService diariaService;

   @GetMapping("/diarias")
   public List<Diaria> getDiarias() {
       return this.diariaService.getDiarias();
   }

   @GetMapping("/diarias/{id}")
   public Diaria getDiariaPorId(@PathVariable("id") Long idDiaria) {
       return this.diariaService.getDiariaPorId(idDiaria);
   }

   @PostMapping("/diarias")
   public Diaria inserirDiaria(@RequestBody Diaria diaria){
       return this.diariaService.inserirOuAtualizar(diaria);
   }

   @PutMapping("/diarias/{id}")
   public Diaria atualizarDiaria(@RequestBody Diaria diaria){
       return this.diariaService.inserirOuAtualizar(diaria);
   }

   @DeleteMapping("/diarias/{id}")
   public void apagarDiaria(@PathVariable("id") Long id) {
       this.diariaService.apagar(id);
   }
}
