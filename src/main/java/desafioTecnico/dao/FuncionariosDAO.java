package desafioTecnico.dao;

import desafioTecnico.infra.ConnectionFactory;
import desafioTecnico.model.Funcionarios;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public void delete(String nome) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM funcionarios WHERE nome = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nome);

            preparedStatement.executeUpdate();

        } catch (SQLException error) {
            throw new RuntimeException(error);
        } ;
    }

    @Override
    public List<Funcionarios> findAll() {
        List<Funcionarios> funcionarios = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "SELECT nome, to_char(data_nascimento, 'DD/MM/YYYY') as data_nascimento, salario, funcao FROM funcionarios";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                String nome = rs.getString("nome");
                LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
                BigDecimal salario = rs.getBigDecimal("salario");
                String funcao = rs.getString("funcao");
            }

        } catch (SQLException error) {
            throw new RuntimeException(error);
        } ;

        return funcionarios;
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
