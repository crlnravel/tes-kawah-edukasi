package com.kawahedukasi.service;

import com.kawahedukasi.model.Employee;
import com.kawahedukasi.model.EmployeeScore;
import com.kawahedukasi.repository.EmployeeRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EmployeeService {

    @Inject
    EmployeeRepository repository;

    @Transactional
    public List<Employee> findManagersWithSubordinates(long id) {
        List<Employee> employees = new ArrayList<>();

        if (!repository.findSubordinates(id).isEmpty()) {
            List<Employee> subordinates = new ArrayList<>();

            repository.findSubordinates(id)
                    .forEach(subordinate -> subordinates.addAll(findManagersWithSubordinates(subordinate.getId())));

            employees.addAll(subordinates);
        }

        employees.add(repository.findById(id));

        return employees;
    }

    public double countAvgEmployeesScore(List<Employee> employees) {

        if (employees.isEmpty()) {
            return 0;
        }

        double totalScore = 0d;
        int totalEmployee = employees.size();

        for (Employee employee: employees) {
            EmployeeScore employeeScore = EmployeeScore.findById(employee.getId());

            totalScore += employeeScore.getScore();
        }

        return totalScore / totalEmployee;
    }
}
