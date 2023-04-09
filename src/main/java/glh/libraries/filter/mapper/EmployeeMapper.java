package glh.libraries.filter.mapper;

import glh.libraries.filter.dto.EmployeeDTO;
import glh.libraries.filter.entities.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper extends AbstractMapper<Employee, EmployeeDTO>{
    public EmployeeMapper() {
        super(Employee.class, EmployeeDTO.class);
    }
}
