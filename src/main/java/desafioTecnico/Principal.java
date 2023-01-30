package desafioTecnico;

import desafioTecnico.dao.FuncionariosDAO;
import desafioTecnico.model.Funcionarios;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Principal {

    public static void main(String[] args) {

        FuncionariosDAO funcionariosDAO = new FuncionariosDAO();

//        ### RETORNA TODOS FUNCIONÁRIOS E SEUS ATRÍBUTOS ###
        allEmployees(funcionariosDAO);

//        ### REMOVE DO BANCO DE DADOS NOME DESEJADO ###
        remove("João", funcionariosDAO);

//        ### ATUALIZA SALÁRIOS EM MAIS 10% ###
        updateSalary(funcionariosDAO);

//        ### RETORNA FUNCIONÁRIOS E SUAS FUNÇÕES ###
        functionEmployee(funcionariosDAO);

//        ### RETORNA NOMES ORDENADOS ###
        orderedNames(funcionariosDAO);

//        ### RETORNA TOTAL DOS SALARIOS ###
        totalSalary(funcionariosDAO);

//        ### RETORNA QUANTIDADE DE SALÁRIOS MINÍMOS CADA FUNCIONÁRIO RECEBE ###
        minSalary(funcionariosDAO);

//        ### RETORNA ANIVERSARIANTES DOS MESES 10 E 12 ###
        birthdays(funcionariosDAO);

    }

//    public static void create(FuncionariosDAO funcionariosDAO){
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
//
//        funcionariosDAO.create(funcionarios);
//    }
//
//    public static void create(String nome, String nascimento, String salario, String funcao, FuncionariosDAO funcionariosDAO){
//        Funcionarios funcionario = new Funcionarios();
//        funcionario.setNome(nome);
//        funcionario.setNascimento(LocalDate.parse(nascimento));
//        funcionario.setSalario(new BigDecimal(salario));
//        funcionario.setFuncao(funcao);
//
//        funcionariosDAO.create(funcionario);
//    }
    public static void allEmployees(FuncionariosDAO funcionariosDAO){
        List<Funcionarios> employees = funcionariosDAO.findAll();

        System.out.printf("%10s %20s %15s %15s","nome", "data_nascimento", "salario", "funcao");
        System.out.println();
        for(Funcionarios employee : employees){
            String formatted = employee.getNascimento().format(DateTimeFormatter.ofPattern(("dd/MM/yyyy")));
            BigDecimal salario = employee.getSalario();
            DecimalFormat df = new DecimalFormat("#,##0.00");
            System.out.format("%10s, %20s, %15s, %15s", employee.getNome(), formatted, df.format(salario), employee.getFuncao());
            System.out.println();
        }
    }

    public static void remove(String nome, FuncionariosDAO funcionariosDAO){
        funcionariosDAO.delete(nome);
    }

    public static void updateSalary(FuncionariosDAO funcionariosDAO){
        funcionariosDAO.update();
    }

    public static void functionEmployee(FuncionariosDAO funcionariosDAO) {
        Map<String, List<String>> functionsToEmployees = new HashMap<>();
        List<Funcionarios> employees = funcionariosDAO.findAll();

        for(Funcionarios employee : employees) {
            String currentEmployee = employee.getNome();
            String function = employee.getFuncao();

            if(!functionsToEmployees.containsKey(function)){
                functionsToEmployees.put(function, new ArrayList<>());
            }

            functionsToEmployees.get(function).add(currentEmployee);
        }
        System.out.print(functionsToEmployees);
    }

    public static void orderedNames(FuncionariosDAO funcionariosDAO){
        ArrayList<String> listNames = new ArrayList<String>();
        List<Funcionarios> employees = funcionariosDAO.findAll();

        for(Funcionarios employee : employees){
            listNames.add(employee.getNome());
        }

        Collections.sort(listNames);
        System.out.println(listNames);
    }

    public static void totalSalary(FuncionariosDAO funcionariosDAO){
        List<Funcionarios> employees = funcionariosDAO.findAll();
        BigDecimal total = BigDecimal.valueOf(0);

        for(Funcionarios employee : employees){
            total = total.add(employee.getSalario());
        }

        DecimalFormat df = new DecimalFormat("#,##0.00");
        System.out.println(df.format(total));
    }

    public static void minSalary(FuncionariosDAO funcionariosDAO){
        List<Funcionarios> funcionarios = funcionariosDAO.findAll();
        BigDecimal total = BigDecimal.valueOf(0);

        for(Funcionarios funcionario : funcionarios){
            BigDecimal salaryMin = new BigDecimal(1212);
            BigDecimal salary = funcionario.getSalario();

            total = salary.divide(salaryMin, new MathContext(2, RoundingMode.HALF_EVEN));

            System.out.println("Funcionário: " + funcionario.getNome() + ", Salarios Minimo: " + total);
        }
    }

    public static void maxAge(FuncionariosDAO funcionariosDAO){
        List<Funcionarios> funcionarios = funcionariosDAO.findAll();
        Period age = Period.ZERO;
        String name = "";

        for(Funcionarios funcionario : funcionarios){
            LocalDate today = LocalDate.now();
            LocalDate birth = funcionario.getNascimento();
            Period currentAge = Period.between(birth, today);

            if(age.toTotalMonths() < currentAge.toTotalMonths()){
                age = currentAge;
                name = funcionario.getNome();
            }
        }
        System.out.print("Nome: " + name + ", Idade: " + age);
    }

    public static void birthdays(FuncionariosDAO funcionariosDAO){
        List<Funcionarios> funcionarios = funcionariosDAO.findAll();

        for(Funcionarios funcionario : funcionarios){
            int month = funcionario.getNascimento().getMonthValue();

            if(month == 10 || month == 12) {
                System.out.println("Nome: " + funcionario.getNome() + ", Aniversário: " + funcionario.getNascimento());
            }
        }
    }
}
