package com.alipour.learn.repositories;

import com.alipour.learn.models.Employee;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, Long> {
    @Query("{ 'name': {$regex : ?0, $options: 'i'} }")
    Flux<Employee> findByName(final String name);
}
