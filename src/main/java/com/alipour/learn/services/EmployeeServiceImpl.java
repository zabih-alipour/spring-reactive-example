package com.alipour.learn.services;

import com.alipour.learn.models.Employee;
import com.alipour.learn.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Employee employee) {
        repository.save(employee).subscribe();
    }

    @Override
    public Mono<Employee> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Employee> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Flux<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Employee> update(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return repository.deleteById(id);
    }
}
