package desafioTecnico.dao;

import desafioTecnico.model.Funcionarios;

import java.util.List;
import java.util.Optional;

public interface IFuncionariosDAO {
    Funcionarios create(Funcionarios funcionarios);
    Funcionarios update(Funcionarios funcionarios);
    void delete(Long id);
    List<Funcionarios> findAll();
    Optional<Funcionarios> findById(Long id);
    List<Funcionarios> findByName(String nome);
}
