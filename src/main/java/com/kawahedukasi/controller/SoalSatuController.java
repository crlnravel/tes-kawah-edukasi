package com.kawahedukasi.controller;

import com.kawahedukasi.model.Employee;
import com.kawahedukasi.service.EmployeeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/api/soalsatu")
@Produces(MediaType.APPLICATION_JSON)
public class SoalSatuController {

    @Inject
    EmployeeService employeeService;

    @GET
    public Response soalSatuEndpoint(@QueryParam("id") long id) {
        Map<String, Object> jawabanSoalSatu = new HashMap<>();

        List<Employee> employees = employeeService
                .findManagersWithSubordinates(id)
                .stream()
                .sorted(Comparator.comparingLong(Employee::getId))
                .collect(Collectors.toList());

        jawabanSoalSatu.put("employees", employees);

        Double avgScore = employeeService.countAvgEmployeesScore(employees);

        jawabanSoalSatu.put("avg_score", avgScore);

        return Response.ok(jawabanSoalSatu).build();
    }

}
