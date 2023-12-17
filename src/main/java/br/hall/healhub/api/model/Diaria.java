package br.hall.healhub.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_diaria")
public class Diaria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String dataDia;
    private String emocao;
    private Number coposDAgua;
    private String exercicios;
    private String adicional;

    public long getId() {
        return id;
    }

    public String getDataDia() {
        return dataDia;
    }

    public void setDatadia (String dataDia) {
        this.dataDia = dataDia;
    }

    public String getEmocao() {
        return emocao;
    }

    public void setEmocao (String emocao) {
        this.emocao = emocao;
    }

    public Number getCoposDAgua() {
        return coposDAgua;
    }

    public void setCoposDAgua (Number coposDAgua) {
        this.coposDAgua = coposDAgua;
    }

    public String getExercicios() {
        return exercicios;
    }

    public void setExercicios (String exercicios) {
        this.exercicios = exercicios;
    }

    public String getAdicional() {
        return adicional;
    }

    public void setAdicional (String  adicional) {
        this.adicional = adicional;
    }

    
}