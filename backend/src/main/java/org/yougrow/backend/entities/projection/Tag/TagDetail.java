package org.yougrow.backend.entities.projection.Tag;

import org.yougrow.backend.entities.projection.IdAndQuestion;

import java.util.List;

public interface TagDetail {

    Long getId();

    String getName();

    List<IdAndQuestion> getQuestions();

}
