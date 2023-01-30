package desafioTecnico.dao;

import desafioTecnico.model.Funcionarios;

import java.util.List;
import java.util.Optional;

public interface IFuncionariosDAO {
    Funcionarios create(Funcionarios funcionarios);

    void update();

    void delete(String nome);

    List<Funcionarios> findAll();
    List<String> findByBirthday();
}
