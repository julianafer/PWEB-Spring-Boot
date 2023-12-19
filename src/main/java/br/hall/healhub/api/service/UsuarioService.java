package br.hall.healhub.api.service;

import br.hall.healhub.api.repositories.UsuarioRepository;
import br.hall.healhub.api.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getUsuarios() {
        return this.usuarioRepository.findAll();
    }

    public Usuario getUsuarioPorId(Long idUsuario) {
        return this.usuarioRepository.findById(idUsuario).orElse(null);
    }

    @Transactional
    public Usuario inserirOuAtualizar(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new RuntimeException("O nome do usuário não pode ser vazio!");
        }
        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            throw new RuntimeException("A senha do usuário não pode ser vazia!");
        }
        if (this.usuarioRepository.existsByNome(usuario.getNome())) {
            throw new RuntimeException("Já existe um usuário com este nome!");
        }
        return this.usuarioRepository.save(usuario);
    }

    public void apagar(Long id) {
        this.usuarioRepository.deleteById(id);
    }
}
