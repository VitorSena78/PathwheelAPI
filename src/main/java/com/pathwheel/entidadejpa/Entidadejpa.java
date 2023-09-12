package com.pathwheel.entidadejpa;

import jakarta.persistence.*;

@Table(name = "medicos")
@Entity(name = "Entidadejpa")
public class Entidadejpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String especialidade;

    public Entidadejpa(DadosCadastroRecord json) {
        this.nome = json.nome();
        this.email = json.email();
        this.crm = json.crm();
        this.especialidade = json.especialidade();
    }
}
