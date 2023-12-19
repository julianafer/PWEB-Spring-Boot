package br.hall.healhub.api.model;

public class DiariaUsuarioRequest {
    private Diaria diaria;
    private Usuario usuario;

    public Diaria getDiaria() {
        return diaria;
    }

    public void setDiaria(Diaria diaria) {
        this.diaria = diaria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

