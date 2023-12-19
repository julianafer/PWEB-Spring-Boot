package br.hall.healhub.api.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String senha;
    private Number metaDAgua;
    private Number metros;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha (String senha) {
        this.senha = senha;
    }

    public Number getMetaDAgua() {
        return metaDAgua;
    }

    public void setMetaDAgua (Number metaDAgua) {
        this.metaDAgua = metaDAgua;
    }

    public Number getMetros() {
        return metros;
    }

    public void setMetros (Number metros) {
        this.metros = metros;
    }
    
}
