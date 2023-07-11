package com.example.demo.entity;

import com.example.demo.definition.AbstractDescriptionableEntityDefinition;
import com.example.demo.definition.AbstractEntityDefinition;
import com.example.demo.definition.CustomerEntityDefinition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = CustomerEntityDefinition.NAME)
public class CustomerEntity implements AbstractEntityDefinition, AbstractDescriptionableEntityDefinition {

    @Id
    private Long id;

    @Column(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
