package org.yougrow.backend.entities.projection;

import org.yougrow.backend.entities.Quiz;
import org.yougrow.backend.entities.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Keep it for now but it is not used
// call it in Projection claas this way
/* @JsonProperty("tags")
    @Value("#{T(org.yougrow.backend.entities.projection.ProjectionHelper).extractTagNames(target.tags)}")
    List<String> getTagNames();
*/
public class ProjectionHelper {

    public static List<String> extractTagNames(List<Tag> tags) {
        if (tags == null) {
            return List.of();
        }
        List<String> tagNames = new ArrayList<>();
        for (Tag tag : tags) {
            tagNames.add(tag.getName()); // Zugriff auf die `name`-Eigenschaft jedes Tags
        }
        return tagNames;
    }

    public static List<Map<String, Object>> extractQuizItem(List<Quiz> quizzes) {
        if (quizzes == null) {
            return List.of();
        }
        List<Map<String, Object>> quizItems = new ArrayList<>();
        for (Quiz quiz : quizzes) {
            Map<String, Object> quizData = new HashMap<>();
            quizData.put("id", quiz.getId());
            quizData.put("name", quiz.getName());

            quizItems.add(quizData);
        }
        return quizItems;
    }

}
