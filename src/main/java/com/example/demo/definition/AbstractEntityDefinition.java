package com.example.demo.definition;

import javax.persistence.Id;

public interface AbstractEntityDefinition {

    @Id
    Long getId();

    void setId(Long id);
}
