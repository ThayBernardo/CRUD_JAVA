package desafioTecnico.dao;

import desafioTecnico.infra.ConnectionFactory;
import desafioTecnico.model.Funcionarios;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    public void update() {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "UPDATE funcionarios SET salario = ROUND(salario + salario * 0.1, 2)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.executeUpdate();

        } catch (SQLException error) {
            throw new RuntimeException(error);
        } ;
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
            String sql = "SELECT id, nome, to_char(data_nascimento, 'DD/MM/YYYY') as data_nascimento, to_char(salario, 'fm999G999D99') as salario, funcao FROM funcionarios";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                String nome = rs.getString("nome");
                String dataNascimento = rs.getString("data_nascimento");
                String salario = rs.getString("salario");
                String funcao = rs.getString("funcao");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate localDate = LocalDate.parse(dataNascimento, formatter);

                BigDecimal bigDecimal = new BigDecimal(salario.replaceAll(",", ""));

                Funcionarios funcionario = new Funcionarios(nome, localDate, bigDecimal, funcao);
                funcionarios.add(funcionario);
            }

        } catch (SQLException error) {
            throw new RuntimeException(error);
        } ;

        return funcionarios;
    }

//    ----------------- METODO PARA RETORNAR PESSOA MAIS VELHA -----------------
//    @Override
//    public String maxAge() {
//        String funcionario = "";
//        String idade = "";
//
//        try (Connection connection = ConnectionFactory.getConnection()) {
//            String sql = "SELECT nome, date_part('year', age(data_nascimento)) as idade\n" +
//                    "FROM funcionarios\n" +
//                    "ORDER BY data_nascimento\n" +
//                    "LIMIT 1";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            while(rs.next()){
//                funcionario = rs.getString("nome");
//                idade = rs.getString("idade").toString();
//            }
//
//        } catch (SQLException error) {
//            throw new RuntimeException(error);
//        } ;
//
//        return "nome: " + funcionario + "\n" + "idade: " + idade;
//    }

//    ----------------- METODO PARA RETORNAR PESSOAS QUE FAZEM ANIVERSÁRIOS NOS MESES 10 E 12 -----------------
//    @Override
//    public List<String> findByBirthday() {
//        List<String> funcionarios = new ArrayList<>();
//
//        try (Connection connection = ConnectionFactory.getConnection()) {
//            String sql = "SELECT nome, data_nascimento FROM funcionarios WHERE EXTRACT(MONTH FROM data_nascimento) = 10 OR EXTRACT(MONTH FROM data_nascimento) = 12";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            while(rs.next()){
//                String nome = rs.getString("nome");
//
//                funcionarios.add(nome);
//            }
//
//        } catch (SQLException error) {
//            throw new RuntimeException(error);
//        } ;
//
//        return funcionarios;
//    }
}
