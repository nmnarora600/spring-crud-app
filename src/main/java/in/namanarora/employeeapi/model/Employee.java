package in.namanarora.employeeapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long empid;
    @Column(name = "emp_name")
    private String empName;
    @Column(name = "emp_salary")
    private float empSalary;
    @Column(name = "emp_age")
    private int empAge;
    @Column(name = "emp_city")
    private String empCity;


    public Employee(long empid, String empName, float empSalary, int empAge, String empCity) {
        this.empid = empid;
        this.empName = empName;
        this.empSalary = empSalary;
        this.empAge = empAge;
        this.empCity = empCity;
    }

    public Employee(){
        super();
    }

    public long getEmpid() {
        return empid;
    }

    public void setEmpid(long empid) {
        this.empid = empid;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public float getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(float empSalary) {
        this.empSalary = empSalary;
    }

    public int getEmpAge() {
        return empAge;
    }

    public void setEmpAge(int empAge) {
        this.empAge = empAge;
    }

    public String getEmpCity() {
        return empCity;
    }

    public void setEmpCity(String empCity) {
        this.empCity = empCity;
    }
}
