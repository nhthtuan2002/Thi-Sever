package Controller;

import Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.EmployeeServicelmp;

import javax.websocket.server.PathParam;
import java.util.List;
@RestController
public class EmployeeController {
    @Autowired
    EmployeeServicelmp employeeServicelmp;

    @RequestMapping(value = "employee", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> findAllUser() {
        List<Employee> list = employeeServicelmp.findAll();
        if (list.size() == 0) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
        employeeServicelmp.saveEmployee(employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<Employee> update(@PathParam("id") Integer id, @RequestBody Employee employee) {
        Employee oldEmployee = employeeServicelmp.findById(id);
        oldEmployee.setName(employee.getName());
        oldEmployee.setSalary(employee.getSalary());
        employeeServicelmp.saveEmployee(oldEmployee);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }


    @RequestMapping(value = "deleteU/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Employee> delete(@PathVariable(value = "id") Integer id) {
        employeeServicelmp.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> findUserByName(@PathParam("name") String name) {
        List<Employee> list = employeeServicelmp.findAllByNameContainsIgnoreCase(name);
        if (list.size() == 0) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
    }
}
