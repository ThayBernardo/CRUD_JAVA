package desafioTecnico.dao;

import desafioTecnico.model.Funcionarios;

import java.util.List;

public interface IFuncionariosDAO {
    Funcionarios create(Funcionarios funcionarios);
    void update();
    void delete(String nome);
    List<Funcionarios> findAll();
    List<String> findByBirthday();
    String maxAge();
}
