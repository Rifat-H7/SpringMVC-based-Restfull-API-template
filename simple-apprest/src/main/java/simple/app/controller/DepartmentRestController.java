package simple.app.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import simple.app.domain.Department;
import simple.app.service.DepartmentService;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentRestController {

    private DepartmentService departmentService;

    public DepartmentRestController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/list")
    public List<Department> list(Model model) throws SQLException {
        return departmentService.list();
    }

    @PostMapping("/store")
    public String store(@Valid @RequestBody Department department, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "Invalid Request";
        }
        departmentService.create(department);
        return "Success";
    }

    @PutMapping("/update")
    public String update(@Valid @RequestBody Department department, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "Invalid Request";
        }
        departmentService.update(department);
        return "Success";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam("departmentId") Long departmentId) throws SQLException {
        departmentService.delete(departmentId);
        return "Success";
    }
}
