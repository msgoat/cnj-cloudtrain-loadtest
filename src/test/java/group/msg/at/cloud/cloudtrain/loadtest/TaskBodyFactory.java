package group.msg.at.cloud.cloudtrain.loadtest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import group.msg.at.cloud.cloudtrain.loadtest.model.Task;
import group.msg.at.cloud.cloudtrain.loadtest.model.TaskCategory;
import group.msg.at.cloud.cloudtrain.loadtest.model.TaskPriority;

public class TaskBodyFactory {

    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    public String extractTaskIdFromLocation(String location) {
        String result = null;
        int lastPathSep = location.lastIndexOf("/");
        if (lastPathSep != 0) {
            result = location.substring(lastPathSep + 1);
        }
        return result;
    }

    public String createTaskJsonString() {
        return serializeTask(createTask());
    }

    private Task createTask() {
        Task result = new Task();
        result.setSubject("test");
        result.setDescription("this is a (load) test instance");
        result.setCategory(TaskCategory.NEW_FEATURE);
        result.setPriority(TaskPriority.MEDIUM);
        result.setAffectedProjectId("CloudTrain");
        result.setAffectedApplicationId("cnj-any");
        return result;
    }

    private String serializeTask(Task task) {
        String result;
        try {
            result = objectMapper.writeValueAsString(task);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(String.format("Failed to serialize task [%s] to JSON: %s", task, e.getMessage()), e);
        }
        return result;
    }

}
