package com.example.demo.definition;

import javax.persistence.Entity;

@Entity(name = CustomerEntityDefinition.NAME)
public interface CustomerEntityDefinition extends AbstractEntityDefinition, AbstractDescriptionableEntityDefinition {
    String NAME = "customer";
}
