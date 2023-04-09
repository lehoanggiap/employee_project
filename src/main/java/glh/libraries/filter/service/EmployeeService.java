package glh.libraries.filter.service;

import glh.libraries.filter.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    Integer saveEmployee(EmployeeDTO employee);

    void updateEmployee(EmployeeDTO employee);

    void deleteEmployee(EmployeeDTO employee);

    EmployeeDTO getEmployeeById(Integer id);

    List<EmployeeDTO> findAll();

    List<Integer> updateEmployees(List<EmployeeDTO> employees);

}
