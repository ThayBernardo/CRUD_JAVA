package desafioTecnico;

import desafioTecnico.dao.FuncionariosDAO;
import desafioTecnico.model.Funcionarios;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Principal {

    public static void main(String[] args) {

        FuncionariosDAO funcionariosDAO = new FuncionariosDAO();

//        ### IMPRIME ANIVERSARIANTES ###
//        List<String> aniversariantes = funcionariosDAO.findByBirthday();
//        for(String aniversariante : aniversariantes){
//            System.out.println(aniversariante);
//        }

//        ### METODO PARA ADICIONAR 10% NO SALARIO ###
//        funcionariosDAO.update();

//        #### RETORNA TABELA ####
//        List<Funcionarios> funcionarios = funcionariosDAO.findAll();
//
//        System.out.printf("%10s %20s %15s %15s","nome", "data_nascimento", "salario", "funcao");
//        System.out.println();
//        for(Funcionarios funcionario : funcionarios){
//            String formatted = funcionario.getNascimento().format(DateTimeFormatter.ofPattern(("dd/MM/yyyy")));
//            BigDecimal salario = funcionario.getSalario();
//            DecimalFormat df = new DecimalFormat("#,##0.00");
//            System.out.format("%10s, %20s, %15s, %15s", funcionario.getNome(), formatted, df.format(salario), funcionario.getFuncao());
//            System.out.println();
//        }

//        #### DELETA ####
//        funcionariosDAO.delete("João");

//        #### ADICIONANDO NA TABELA ####
//        Funcionarios funcionarios = new Funcionarios();
//        funcionarios.setNome("Maria");
//        funcionarios.setNascimento(LocalDate.of(2000, 10, 18));
//        funcionarios.setSalario(BigDecimal.valueOf(2009.44));
//        funcionarios.setFuncao("Operador");

//        funcionarios.setNome("João");
//        funcionarios.setNascimento(LocalDate.of(1990, 5, 12));
//        funcionarios.setSalario(BigDecimal.valueOf(2284.38));
//        funcionarios.setFuncao("Operador");
//
//        funcionarios.setNome("Caio");
//        funcionarios.setNascimento(LocalDate.of(1961, 5, 2));
//        funcionarios.setSalario(BigDecimal.valueOf(9836.14));
//        funcionarios.setFuncao("Coordenador");
//
//        funcionarios.setNome("Miguel");
//        funcionarios.setNascimento(LocalDate.of(1988, 10, 14));
//        funcionarios.setSalario(BigDecimal.valueOf(19119.88));
//        funcionarios.setFuncao("Diretor");
//
//        funcionarios.setNome("Alice");
//        funcionarios.setNascimento(LocalDate.of(1995, 1, 5));
//        funcionarios.setSalario(BigDecimal.valueOf(2234.68));
//        funcionarios.setFuncao("Recepcionista");
//
//        funcionarios.setNome("Heitor");
//        funcionarios.setNascimento(LocalDate.of(1999, 11, 19));
//        funcionarios.setSalario(BigDecimal.valueOf(1582.72));
//        funcionarios.setFuncao("Operador");
//
//        funcionarios.setNome("Arthur");
//        funcionarios.setNascimento(LocalDate.of(1993, 3, 31));
//        funcionarios.setSalario(BigDecimal.valueOf(4071.84));
//        funcionarios.setFuncao("Contador");
//
//        funcionarios.setNome("Laura");
//        funcionarios.setNascimento(LocalDate.of(1994, 7, 8));
//        funcionarios.setSalario(BigDecimal.valueOf(3017.45));
//        funcionarios.setFuncao("Gerente");
//
//        funcionarios.setNome("Heloísa");
//        funcionarios.setNascimento(LocalDate.of(2003, 5, 24));
//        funcionarios.setSalario(BigDecimal.valueOf(1606.85));
//        funcionarios.setFuncao("Eletricista");
//
//        funcionarios.setNome("Helena");
//        funcionarios.setNascimento(LocalDate.of(1996, 9, 2));
//        funcionarios.setSalario(BigDecimal.valueOf(2799.93));
//        funcionarios.setFuncao("Gerente");

//        funcionariosDAO.create(funcionarios);


    }
}
