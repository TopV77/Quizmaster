package org.yougrow.backend.entities.projection.Category;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.yougrow.backend.entities.projection.IdAndName;

import java.util.List;

@JsonPropertyOrder({"id", "name", "description", "color", "quizzes"})
public interface CategoryDetail {

    Long getId();

    String getName();

    String getDescription();

    String getColor();

    List<IdAndName> getQuizzes();
}
