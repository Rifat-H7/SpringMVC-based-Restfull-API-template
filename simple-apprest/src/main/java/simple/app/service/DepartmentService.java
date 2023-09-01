package simple.app.service;

import org.springframework.stereotype.Service;
import simple.app.domain.Department;
import simple.app.repository.DepartmentRepository;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class DepartmentService {

    private Logger logger = Logger.getLogger(DepartmentService.class.getName());

    private DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> list() throws SQLException {
        return departmentRepository.list();
    }

    public Department get(Long id) throws SQLException {
        return departmentRepository.get(id);
    }

    public boolean create(Department department) throws SQLException {
        department.setName(department.getName().toUpperCase());
        return departmentRepository.create(department);
    }

    public boolean update(Department department) throws SQLException {
        department.setName(department.getName().toUpperCase());
        return departmentRepository.update(department);
    }

    public boolean delete(Long id) throws SQLException {
        return departmentRepository.delete(get(id));
    }
}
