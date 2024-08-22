package in.namanarora.employeeapi.repository;

import in.namanarora.employeeapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
        List<Employee> findByEmpCity(String empCity);

        List<Employee> findByEmpAge(int empAge);

        List<Employee> findByEmpCityAndEmpAge(String empCity,int empAge);
}
