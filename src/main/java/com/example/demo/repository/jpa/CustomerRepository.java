package com.example.demo.repository.jpa;

import com.example.demo.definition.CustomerEntityDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository(value = CustomerRepository.NAME)
@RepositoryRestResource(itemResourceRel = CustomerEntityDefinition.NAME, path = CustomerEntityDefinition.NAME, collectionResourceRel = CustomerEntityDefinition.NAME)
public interface CustomerRepository extends JpaRepository<CustomerEntityDefinition, Long> {
    String NAME = "customerRepository";
}
