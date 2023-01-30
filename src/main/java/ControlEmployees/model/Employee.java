package ControlEmployees.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee extends Person {
    private BigDecimal salary;
    private String function;

    public Employee(String name, LocalDate birth, BigDecimal salary, String function) {
        super(name, birth);
        this.salary = salary;
        this.function = function;
    }

    public Employee() {
        super();
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String funcao) {
        this.function = function;
    }
}
