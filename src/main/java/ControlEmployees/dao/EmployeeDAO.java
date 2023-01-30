package ControlEmployees.dao;

import ControlEmployees.infra.ConnectionFactory;
import ControlEmployees.model.Employee;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements IEmployeeDAO {
    @Override
    public Employee create(Employee employee) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO funcionarios (nome, data_nascimento, salario, funcao) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setDate(2, Date.valueOf(employee.getBirth()));
            preparedStatement.setBigDecimal(3, employee.getSalary());
            preparedStatement.setString(4, employee.getFunction());

            preparedStatement.executeUpdate();

        } catch (SQLException error) {
            throw new RuntimeException(error);
        } ;
        return employee;
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
    public void delete(String name) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM funcionarios WHERE nome = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

            preparedStatement.executeUpdate();

        } catch (SQLException error) {
            throw new RuntimeException(error);
        } ;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "SELECT id, nome, to_char(data_nascimento, 'DD/MM/YYYY') as data_nascimento, to_char(salario, 'fm999G999D99') as salario, funcao FROM funcionarios";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                String name = rs.getString("nome");
                String birth = rs.getString("data_nascimento");
                String salary = rs.getString("salario");
                String function = rs.getString("funcao");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate localDate = LocalDate.parse(birth, formatter);

                BigDecimal bigDecimal = new BigDecimal(salary.replaceAll(",", ""));

                Employee funcionario = new Employee(name, localDate, bigDecimal, function);
                employees.add(funcionario);
            }

        } catch (SQLException error) {
            throw new RuntimeException(error);
        } ;

        return employees;
    }

//    ----------------- METODO PARA RETORNAR PESSOA MAIS VELHA -----------------
//    @Override
//    public String maxAge() {
//        String employee = "";
//        String age = "";
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
//                employee = rs.getString("nome");
//                age = rs.getString("idade").toString();
//            }
//
//        } catch (SQLException error) {
//            throw new RuntimeException(error);
//        } ;
//
//        return "Nome: " + employee + "\n" + "Idade: " + age;
//    }

//    ----------------- METODO PARA RETORNAR PESSOAS QUE FAZEM ANIVERSÁRIOS NOS MESES 10 E 12 -----------------
//    @Override
//    public List<String> findByBirthday() {
//        List<String> employees = new ArrayList<>();
//
//        try (Connection connection = ConnectionFactory.getConnection()) {
//            String sql = "SELECT nome, data_nascimento FROM funcionarios WHERE EXTRACT(MONTH FROM data_nascimento) = 10 OR EXTRACT(MONTH FROM data_nascimento) = 12";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            while(rs.next()){
//                String name = rs.getString("nome");
//
//                employees.add(name);
//            }
//
//        } catch (SQLException error) {
//            throw new RuntimeException(error);
//        } ;
//
//        return employees;
//    }

//    ----------------- METODO PARA RETORNAR NOMES ORDENADOS -----------------
//    public List<String> findAllNames() {
//        List<String> orderedNames = new ArrayList<>();
//
//        try (Connection connection = ConnectionFactory.getConnection()) {
//            String sql = "SELECT nome FROM funcionarios ORDER BY nome";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            while(rs.next()){
//                String name = rs.getString("nome");
//
//                orderedNames.add(name);
//            }
//
//        } catch (SQLException error) {
//            throw new RuntimeException(error);
//        } ;
//
//        return orderedNames;
//    }

    //    ----------------- METODO PARA RETORNAR TOTAL DOS SALARIOS -----------------
//    public Integer totalSalary() {
//        Integer total = null;
//        try (Connection connection = ConnectionFactory.getConnection()) {
//            String sql = "SELECT SUM(salario) as total_salarios FROM funcionarios";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            while (rs.next()) {
//                total = rs.getInt("total_salarios");
//            }
//
//        } catch (SQLException error) {
//            throw new RuntimeException(error);
//        }
//        ;
//
//        return total;
//    }
}
