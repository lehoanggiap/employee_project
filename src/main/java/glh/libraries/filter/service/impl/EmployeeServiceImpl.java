package glh.libraries.filter.service.impl;

import glh.libraries.filter.dto.EmployeeDTO;
import glh.libraries.filter.entities.Employee;
import glh.libraries.filter.exception.BadRequestException;
import glh.libraries.filter.exception.ResourceNotFoundException;
import glh.libraries.filter.mapper.EmployeeMapper;
import glh.libraries.filter.repo.EmployeeRepository;
import glh.libraries.filter.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;

    EmployeeMapper employeeMapper;

    @Override
    public Integer saveEmployee(EmployeeDTO employee) {
        checkForException(employee);
        Employee savedEmployee = employeeRepository.save(employeeMapper.mapDTOtoEntity(employee));
        return savedEmployee.getId();
    }

    @Override
    public void updateEmployee(EmployeeDTO employee) {
        checkForException(employee);
        checkForException(employee.getId());
        Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());
        if(optionalEmployee.isEmpty()){
            throw new ResourceNotFoundException("Employee is not existed");
        }
        Employee employeeFromDb = optionalEmployee.get();
        BeanUtils.copyProperties(employee, employeeFromDb);
        employeeRepository.save(employeeFromDb);
    }

    @Override
    public void deleteEmployee(EmployeeDTO employee) {
        checkForException(employee);
        checkForException(employee.getId());
        Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());
        if(optionalEmployee.isEmpty()){
            throw new ResourceNotFoundException("Employee is not existed");
        }
        employeeRepository.deleteById(employee.getId());
    }

    @Override
    public EmployeeDTO getEmployeeById(Integer id) {
        checkForException(id);
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isEmpty()){
            throw new ResourceNotFoundException("Employee is not existed");
        }
        return employeeMapper.mapEntityToDTO(optionalEmployee.get());
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return employeeMapper.mapListEntitiesToDTOs(employeeRepository.findAll());
    }

    @Override
    public List<Integer> updateEmployees(List<EmployeeDTO> employees) {
        if(employees == null || employees.isEmpty()){
            throw new BadRequestException();
        }
        List<Integer> updateSuccessIds = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            try {
                EmployeeDTO employee = employees.get(i);
                updateEmployee(employee);
                updateSuccessIds.add(employee.getId());
            }catch (Exception e){
                log.error("Faild to update employee");
            }
        }
        return updateSuccessIds;
    }

    private <T> void checkForException(T obj){
        if(obj == null){
            throw new BadRequestException();
        }
    }
}
