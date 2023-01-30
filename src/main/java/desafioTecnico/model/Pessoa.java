package desafioTecnico.model;

import java.time.LocalDate;

public abstract class Pessoa {
    public Pessoa(Long id, String nome, LocalDate nascimento) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
    }

    protected Long id;
    protected String nome;
    protected LocalDate nascimento;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }
}
