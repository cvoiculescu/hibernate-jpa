package org.voiculescu.advancedjpa.repository;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.voiculescu.advancedjpa.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Repository
@Transactional
public class EmployeeRepository {

    private final EntityManager entityManager;

    @Autowired
    public EmployeeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Employee save(Employee employee) {
        entityManager.persist(employee);
        return employee;
    }

    public List<Employee> retrieveAllEmployees() {
        return Stream.concat(retrieveAllFullTimeEmployees().stream(), retrieveAllPartTimeEmployees().stream()).collect(Collectors.toList());
    }

    public List<Employee> retrieveAllFullTimeEmployees() {
        return entityManager.createQuery("select e from FullTimeEmployee e", Employee.class).getResultList();
    }

    public List<Employee> retrieveAllPartTimeEmployees() {
        return entityManager.createQuery("select e from PartTimeEmployee e", Employee.class).getResultList();
    }

}