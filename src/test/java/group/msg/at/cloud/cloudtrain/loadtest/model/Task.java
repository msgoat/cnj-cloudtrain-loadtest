package group.msg.at.cloud.cloudtrain.loadtest.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Task extends AbstractAuditableEntity {

    private UUID id;

    private String subject;

    private String description;

    private TaskCategory category = TaskCategory.UNDEFINED;

    private TaskPriority priority = TaskPriority.UNDEFINED;

    private TaskLifeCycleState lifeCycleState = TaskLifeCycleState.UNDEFINED;

    private LocalDateTime submittedAt;

    private String submitterUserId;

    private LocalDateTime dueDate;

    private int completionRate;

    private LocalDateTime completionDate;

    private String completedByUserId;

    private String responsibleUserId;

    private String affectedProjectId;

    private String affectedApplicationId;

    private String affectedModule;

    private String affectedResource;

    private int estimatedEffort;

    private int actualEffort;

    private int version;

    public Task() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        if (this.id != null) {
            throw new IllegalStateException("Task ID already set!");
        }
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskLifeCycleState getLifeCycleState() {
        return lifeCycleState;
    }

    public void setLifeCycleState(TaskLifeCycleState state) {
        this.lifeCycleState = state;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime startDate) {
        this.submittedAt = startDate;
    }

    public String getSubmitterUserId() {
        return submitterUserId;
    }

    public void setSubmitterUserId(String requesterUserId) {
        this.submitterUserId = requesterUserId;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public int getCompletionRate() {
        return completionRate;
    }

    public void setCompletionRate(int completionRate) {
        this.completionRate = completionRate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public String getCompletedByUserId() {
        return completedByUserId;
    }

    public void setCompletedByUserId(String completedById) {
        this.completedByUserId = completedById;
    }

    public String getResponsibleUserId() {
        return responsibleUserId;
    }

    public void setResponsibleUserId(String responsibleId) {
        this.responsibleUserId = responsibleId;
    }

    public String getAffectedProjectId() {
        return affectedProjectId;
    }

    public void setAffectedProjectId(String affectedProjectId) {
        this.affectedProjectId = affectedProjectId;
    }

    public String getAffectedApplicationId() {
        return affectedApplicationId;
    }

    public void setAffectedApplicationId(String affectedApplicationId) {
        this.affectedApplicationId = affectedApplicationId;
    }

    public String getAffectedModule() {
        return affectedModule;
    }

    public void setAffectedModule(String affectedModule) {
        this.affectedModule = affectedModule;
    }

    public String getAffectedResource() {
        return affectedResource;
    }

    public void setAffectedResource(String affectedResource) {
        this.affectedResource = affectedResource;
    }

    public int getEstimatedEffort() {
        return estimatedEffort;
    }

    public void setEstimatedEffort(int estimatedEffort) {
        this.estimatedEffort = estimatedEffort;
    }

    public int getActualEffort() {
        return actualEffort;
    }

    public void setActualEffort(int actualEffort) {
        this.actualEffort = actualEffort;
    }

    public int getVersion() {
        return version;
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 7;
        result = prime * result + this.id.hashCode();
        return result;
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        return id == other.id;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " { id : " + this.id + ", version : " + this.version + " }";
    }
}
