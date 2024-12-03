package org.yougrow.backend.entities.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "name"})
public interface IdAndName {
    Long getId();

    String getName();
}
