package ControlEmployees;

import ControlEmployees.dao.EmployeeDAO;
import ControlEmployees.model.Employee;

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

        EmployeeDAO employeeDAO = new EmployeeDAO();

//        ### CRIA FUNCIONARIOS ###
        createEmployee(employeeDAO);

//        ### RETORNA TODOS FUNCIONÁRIOS E SEUS ATRÍBUTOS ###
        allEmployees(employeeDAO);

//        ### REMOVE DO BANCO DE DADOS NOME DESEJADO ###
        remove("João", employeeDAO);

//        ### ATUALIZA SALÁRIOS EM MAIS 10% ###
        updateSalary(employeeDAO);

//        ### RETORNA FUNCIONÁRIOS E SUAS FUNÇÕES ###
        functionEmployee(employeeDAO);

//        ### RETORNA NOMES ORDENADOS ###
        orderedNames(employeeDAO);

//        ### RETORNA TOTAL DOS SALARIOS ###
        totalSalary(employeeDAO);

//        ### RETORNA QUANTIDADE DE SALÁRIOS MINÍMOS CADA FUNCIONÁRIO RECEBE ###
        minSalary(employeeDAO);

//        ### RETORNA ANIVERSARIANTES DOS MESES 10 E 12 ###
        birthdays(employeeDAO);

//        ### RETORNA PESSOA MAIS VELHA ###
        maxAge(employeeDAO);
    }

    public static void createEmployee(EmployeeDAO employeeDAO) {
        List<Employee> employeesList = Arrays.asList(
            new Employee("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44), "Operador"),
            new Employee("João", LocalDate.of(1990, 5, 12), BigDecimal.valueOf(2284.38), "Operador"),
            new Employee("Caio", LocalDate.of(1961, 5, 2), BigDecimal.valueOf(9836.14), "Coordenador"),
            new Employee("Miguel", LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19119.88), "Diretor"),
            new Employee("Alice", LocalDate.of(1995, 1, 5), BigDecimal.valueOf(2234.68), "Recepcionista"),
            new Employee("Heitor", LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1582.72), "Operador"),
            new Employee("Arthur", LocalDate.of(1993, 3, 31), BigDecimal.valueOf(4071.84), "Contador"),
            new Employee("Laura", LocalDate.of(1994, 7, 8), BigDecimal.valueOf(3017.45), "Gerente"),
            new Employee("Heloísa", LocalDate.of(2003, 5, 24), BigDecimal.valueOf(1606.85), "Eletricista"),
            new Employee("Helena", LocalDate.of(1996, 9, 2), BigDecimal.valueOf(2799.93), "Gerente")
        );

        for (Employee funcionario : employeesList) {
            employeeDAO.create(funcionario);
        }
    }


    public static void allEmployees(EmployeeDAO employeeDAO){
        List<Employee> employees = employeeDAO.findAll();

        System.out.printf("%10s %20s %15s %15s","nome", "data_nascimento", "salario", "funcao");
        System.out.println();
        for(Employee employee : employees){
            String formatted = employee.getBirth().format(DateTimeFormatter.ofPattern(("dd/MM/yyyy")));
            BigDecimal salary = employee.getSalary();
            DecimalFormat df = new DecimalFormat("#,##0.00");
            System.out.format("%10s, %20s, %15s, %15s", employee.getName(), formatted, df.format(salary), employee.getFunction());
            System.out.println();
        }
    }

    public static void remove(String name, EmployeeDAO employeeDAO){
        employeeDAO.delete(name);
    }

    public static void updateSalary(EmployeeDAO employeeDAO){
        employeeDAO.update();
    }

    public static void functionEmployee(EmployeeDAO employeeDAO) {
        Map<String, List<String>> functionsToEmployees = new HashMap<>();
        List<Employee> employees = employeeDAO.findAll();

        for(Employee employee : employees) {
            String currentEmployee = employee.getName();
            String function = employee.getFunction();

            if(!functionsToEmployees.containsKey(function)){
                functionsToEmployees.put(function, new ArrayList<>());
            }

            functionsToEmployees.get(function).add(currentEmployee);
        }
        System.out.print(functionsToEmployees);
    }

    public static void orderedNames(EmployeeDAO employeeDAO){
        ArrayList<String> listNames = new ArrayList<String>();
        List<Employee> employees = employeeDAO.findAll();

        for(Employee employee : employees){
            listNames.add(employee.getName());
        }

        Collections.sort(listNames);
        System.out.println(listNames);
    }

    public static void totalSalary(EmployeeDAO employeeDAO){
        List<Employee> employees = employeeDAO.findAll();
        BigDecimal total = BigDecimal.valueOf(0);

        for(Employee employee : employees){
            total = total.add(employee.getSalary());
        }

        DecimalFormat df = new DecimalFormat("#,##0.00");
        System.out.println(df.format(total));
    }

    public static void minSalary(EmployeeDAO employeeDAO){
        List<Employee> employees = employeeDAO.findAll();
        BigDecimal total = BigDecimal.valueOf(0);

        for(Employee employee : employees){
            BigDecimal salaryMin = new BigDecimal(1212);
            BigDecimal salary = employee.getSalary();

            total = salary.divide(salaryMin, new MathContext(2, RoundingMode.HALF_EVEN));

            System.out.println("Funcionário: " + employee.getName() + ", Salários Minimo: " + total);
        }
    }

        public static void birthdays(EmployeeDAO employeeDAO){
        List<Employee> employees = employeeDAO.findAll();

        for(Employee employee : employees){
            int month = employee.getBirth().getMonthValue();

            if(month == 10 || month == 12) {
                System.out.println("Nome: " + employee.getName() + ", Aniversário: " + employee.getBirth());
            }
        }
    }

    public static void maxAge(EmployeeDAO employeeDAO){
        List<Employee> employees = employeeDAO.findAll();
        Period age = Period.ZERO;
        String name = "";

        for(Employee employee : employees){
            LocalDate today = LocalDate.now();
            LocalDate birth = employee.getBirth();
            Period currentAge = Period.between(birth, today);

            if(age.toTotalMonths() < currentAge.toTotalMonths()){
                age = currentAge;
                name = employee.getName();
            }
        }
        System.out.print("Nome: " + name + ", Idade: " + age.getYears());
    }

}
