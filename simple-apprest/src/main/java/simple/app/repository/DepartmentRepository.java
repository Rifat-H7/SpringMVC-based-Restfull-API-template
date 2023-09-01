package simple.app.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import simple.app.domain.Department;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.List;

@Repository
public class DepartmentRepository {
    private SessionFactory sessionFactory;

    public DepartmentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Department> list() throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<Department> departmentQuery = session.createQuery("from Department", Department.class);
        return departmentQuery.getResultList();
    }

    public boolean create(Department department) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.save(department);
        return true;
    }

    public Department get(Long id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Department.class, id);
    }

    public boolean update(Department department) {
        Session session = sessionFactory.getCurrentSession();
        session.update(department);
        return true;
    }

    public boolean delete(Department department) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(department);
        return true;
    }
}
