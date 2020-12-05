package com.alipour.learn.controllers;

import com.alipour.learn.models.Employee;
import com.alipour.learn.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = { "/create", "/" }, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Employee e) {
        employeeService.create(e);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Mono<Employee>> findById(@PathVariable("id") Long id) {
        Mono<Employee> e = employeeService.findById(id);
        HttpStatus status = e != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(e, status);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public Flux<Employee> findByName(@PathVariable("name") String name) {
        return employeeService.findByName(name);
    }
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> findAll() {
        return employeeService.findAll();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Mono<Employee> update(@RequestBody Employee e) {
        return employeeService.update(e);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        employeeService.delete(id).subscribe();
    }
}
