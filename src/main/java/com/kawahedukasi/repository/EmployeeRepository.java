package com.kawahedukasi.repository;

import com.kawahedukasi.model.Employee;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<Employee> {
    public List<Employee> findSubordinates(long id) {
        return list("manager_id", id);
    }
}
