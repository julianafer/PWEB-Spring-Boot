package br.hall.healhub.api.controller;

import br.hall.healhub.api.model.Diaria;
import br.hall.healhub.api.model.DiariaUsuarioRequest;
import br.hall.healhub.api.model.Usuario;
import br.hall.healhub.api.service.DiariaService;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.Authentication;
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
   public Diaria inserirDiaria(@RequestBody DiariaUsuarioRequest request){
        Diaria diaria = request.getDiaria();
        Usuario usuario = request.getUsuario();

       return this.diariaService.inserir(diaria, usuario);
   }

   @PutMapping("/diarias/{id}")
   public Diaria atualizarDiaria(@RequestBody Diaria diaria){
       return this.diariaService.atualizar(diaria);
   }

   @DeleteMapping("/diarias/{id}")
   public void apagarDiaria(@PathVariable("id") Long id) {
       this.diariaService.apagar(id);
   }

   @PostMapping("/diarias/{id}/usuario")
   public ResponseEntity<?> associarUsuarioDiaria(@PathVariable("id") Long diariaId, @RequestBody Usuario usuario) {
    System.out.println(usuario); 
        try {
           Diaria diaria = diariaService.associarUsuarioDiaria(diariaId, usuario);
           return ResponseEntity.ok(diaria);
       } catch (EntityNotFoundException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diaria não encontrada");
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao associar usuário à diária");
       }
   }
}
