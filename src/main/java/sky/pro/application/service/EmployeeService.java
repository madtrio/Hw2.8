package sky.pro.application.service;

import org.springframework.stereotype.Service;
import sky.pro.application.exception.EmployeeAlreadyAddedException;
import sky.pro.application.exception.EmployeeNotFoundException;
import sky.pro.application.exception.EmployeeStorageIsFullException;
import sky.pro.application.model.Employee;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

  private static final int LIMIT = 10;

  private final List<Employee> employees = new ArrayList<>();
  private final ValidatorService validatorService;

  public EmployeeService(ValidatorService validatorService) {
    this.validatorService = validatorService;
  }

  public Employee add(String name,
                      String surname,
                      int department,
                      int salary) {
    Employee employee = new Employee(
            validatorService.validateName(name),
            validatorService.validateSurname(surname),
            department,
            salary);
    if (employees.contains(employee)) {
      throw new EmployeeAlreadyAddedException();
    }
    if (employees.size() < LIMIT) {
      employees.add(employee);
      return employee;
    }
    throw new EmployeeStorageIsFullException();
  }

  public Employee remove(String name,
      String surname,
      int department,
      int salary) {
    Employee employee = new Employee(name, surname, department, salary);
    if (!employees.contains(employee)) {
      throw new EmployeeNotFoundException();
    }
    employees.remove(employee);
    return employee;
  }

  public Employee find(String name,
      String surname,
      int department,
      int salary) {
    Employee employee = new Employee(name, surname, department, salary);
    if (!employees.contains(employee)) {
      throw new EmployeeNotFoundException();
    }
    return employee;
  }

  public List<Employee> getAll() {
    return new ArrayList<>(employees);
  }

}
