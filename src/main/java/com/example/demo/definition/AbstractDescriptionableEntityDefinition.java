package com.example.demo.definition;

import javax.persistence.Column;

public interface AbstractDescriptionableEntityDefinition {

    @Column(name = "description")
    String getDescription();

    void setDescription(String description);
}
