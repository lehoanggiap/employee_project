package glh.libraries.filter.controller;

import glh.libraries.filter.dto.EmployeeDTO;
import glh.libraries.filter.dto.response.ResponseDTO;
import glh.libraries.filter.exception.BadRequestException;
import glh.libraries.filter.exception.ResourceNotFoundException;
import glh.libraries.filter.service.EmployeeService;
import glh.libraries.filter.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    EmployeeService employeeService;

    @PostMapping(value = "", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO<Integer> createEmployee(@RequestBody EmployeeDTO employee){
        Integer id = employeeService.saveEmployee(employee);
        return ResponseDTO
                .<Integer>builder()
                    .status(HttpStatus.CREATED)
                    .msg(Constants.CREATE_EMPLOYEE_SUCCESSFULLY)
                    .data(id)
                .build();
    }

    @PutMapping(value = "", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO<Object> updateEmployee(@RequestBody EmployeeDTO employee){
        employeeService.updateEmployee(employee);
        return ResponseDTO.
                <Object>builder()
                    .status(HttpStatus.OK)
                    .msg(Constants.UPDATE_EMPLOYEE_SUCCESSFULLY)
                .build();
    }

    @PutMapping(value = "/update-batch", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO<Integer> updateEmployees(@RequestBody List<EmployeeDTO> employees){
        List<Integer> updateSuccessIds = employeeService.updateEmployees(employees);
        return ResponseDTO
                .<Integer>builder()
                    .status(HttpStatus.OK)
                    .msg("Number of employees is updated: " + updateSuccessIds.size())
                    .dataList(updateSuccessIds)
                .build();
    }

    @DeleteMapping(value = "", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO<Object> deleteEmployee(@RequestBody EmployeeDTO employee){
        employeeService.deleteEmployee(employee);
        return ResponseDTO
                .<Object>builder()
                    .status(HttpStatus.OK)
                    .msg(Constants.DELETE_EMPLOYEE_SUCCESSFULLY)
                .build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO<EmployeeDTO> getEmployeeById(@PathVariable Integer id){
        return ResponseDTO
                .<EmployeeDTO>builder()
                    .status(HttpStatus.OK)
                    .data(employeeService.getEmployeeById(id))
                .build();
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO<EmployeeDTO> findAll(){
        return ResponseDTO
                .<EmployeeDTO>builder()
                    .status(HttpStatus.OK)
                    .dataList(employeeService.findAll())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class, BadRequestException.class})
    public ResponseDTO<Object> handleValidationExceptions(
            Exception ex) {
        return ResponseDTO
                .builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .msg(Constants.BAD_REQUEST_MSG)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseDTO<Object> handleResourceNotFoundException(
            ResourceNotFoundException ex) {
        return ResponseDTO
                .builder()
                    .status(HttpStatus.NOT_FOUND)
                    .msg(ex.getMessage())
                .build();
    }

}
