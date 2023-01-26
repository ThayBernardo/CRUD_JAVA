package desafioTecnico.dao;

import desafioTecnico.infra.ConnectionFactory;
import desafioTecnico.model.Funcionarios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FuncionariosDAO implements IFuncionariosDAO{
    @Override
    public Funcionarios create(Funcionarios funcionarios) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO funcionarios (nome, data_nascimento, salario, funcao) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, funcionarios.getNome());
            preparedStatement.setDate(2, Date.valueOf(funcionarios.getNascimento()));
            preparedStatement.setBigDecimal(3, funcionarios.getSalario());
            preparedStatement.setString(4, funcionarios.getFuncao());

            preparedStatement.executeUpdate();

        } catch (SQLException error) {
            throw new RuntimeException(error);
        } ;
        return funcionarios;
    }

    @Override
    public Funcionarios update(Funcionarios funcionarios) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Funcionarios> findAll() {
        return null;
    }

    @Override
    public Optional<Funcionarios> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Funcionarios> findByName(String nome) {
        return null;
    }
}
