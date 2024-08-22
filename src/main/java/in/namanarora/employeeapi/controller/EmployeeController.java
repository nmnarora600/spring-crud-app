package in.namanarora.employeeapi.controller;

import in.namanarora.employeeapi.model.Employee;
import in.namanarora.employeeapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping
    public String createNewEmployee(@RequestBody Employee employee){
        employeeRepository.save(employee);
        return "Employee Created..!!";
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(value = "city", required = false) String empCity,
                                                          @RequestParam(value = "age", required = false) Integer empAge){
        List<Employee> empList= new ArrayList<Employee>();
        if(empCity!=null && empAge != null){
            empList=employeeRepository.findByEmpCityAndEmpAge(empCity, empAge);
        }
        else if(empAge != null){
            empList=employeeRepository.findByEmpAge(empAge);
        }
        else if(empCity != null){
            empList=employeeRepository.findByEmpCity(empCity);
        }
        else{
        employeeRepository.findAll().forEach(empList::add);
        }
        return new ResponseEntity<>(empList,HttpStatus.OK);
    }

    @GetMapping("/{empid}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long empid){
        Optional<Employee> emp= employeeRepository.findById(empid);
        if(emp.isPresent()){
            return new ResponseEntity<Employee>(emp.get(),HttpStatus.FOUND);
        }
        return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{empid}")
    public ResponseEntity<String> updateEmployee(@PathVariable long empid,@RequestBody Employee employee){
       Optional<Employee> emp= employeeRepository.findById(empid);
       if(emp.isPresent()){
           Employee existingEmployee=emp.get();
           existingEmployee.setEmpAge(employee.getEmpAge());
           existingEmployee.setEmpCity(employee.getEmpCity());
           existingEmployee.setEmpName(employee.getEmpName());
           existingEmployee.setEmpSalary(employee.getEmpSalary());
           employeeRepository.save(existingEmployee);
           return new ResponseEntity<String>("Details Updated", HttpStatus.OK);
       }
       else{
           return new ResponseEntity<String>("Details doesnot exists for given id",HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping("/{empid}")
    public ResponseEntity<String> deleteOneEmployee(@PathVariable long empid){
        Optional<Employee> emp=employeeRepository.findById(empid);
        if(emp.isPresent()){
            employeeRepository.deleteById(empid);
            return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Employee Does not Exist", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteAllEmployees(){
        employeeRepository.deleteAll();
        return new ResponseEntity<String>("Deleted ALl Tuples Successfully", HttpStatus.OK);
    }


}
