package com.alipour.learn.services;

import com.alipour.learn.models.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    void create(Employee employee);

    Mono<Employee> findById(Long id);

    Flux<Employee> findByName(String name);

    Flux<Employee> findAll();

    Mono<Employee> update(Employee employee);

    Mono<Void> delete(Long id);
}
