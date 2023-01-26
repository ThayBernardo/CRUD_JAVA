package desafioTecnico;

import desafioTecnico.dao.FuncionariosDAO;
import desafioTecnico.model.Funcionarios;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Principal {

    public static void main(String[] args) {

        FuncionariosDAO funcionariosDAO = new FuncionariosDAO();

        Funcionarios funcionarios = new Funcionarios();
        funcionarios.setNome("Maria");
        funcionarios.setNascimento(LocalDate.of(2000, 10, 18));
        funcionarios.setSalario(BigDecimal.valueOf(2009.44));
        funcionarios.setFuncao("Operador");

        funcionariosDAO.create(funcionarios);
    }
}
