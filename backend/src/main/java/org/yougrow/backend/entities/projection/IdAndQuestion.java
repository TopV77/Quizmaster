package org.yougrow.backend.entities.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "question"})
public interface IdAndQuestion {
    Long getId();

    String getQuestion();
}
