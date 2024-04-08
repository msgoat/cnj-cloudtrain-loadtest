package group.msg.at.cloud.cloudtrain.loadtest.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class AbstractAuditableEntity {

    private String createdBy;

    private LocalDateTime createdAt;

    private String lastModifiedBy;

    private LocalDateTime lastModifiedAt;

    /**
     * Returns the user ID of the user that created this entity.
     */
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Returns the creation date and time of this entity.
     */
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Tells this entity to retain the specified user ID and point in time as the
     * createdBy and the createdAt of this entity.
     * <p>
     * Usually this method is called by {@code Repository} implementations that
     * support auditable entities.
     * </p>
     */
    void trackCreation(String creatorId, LocalDateTime creationDate) {
        if (this.createdBy == null) {
            this.createdBy = creatorId;
        }
        if (this.createdAt == null) {
            this.createdAt = creationDate;
        }
        if (this.lastModifiedBy == null) {
            this.lastModifiedBy = creatorId;
        }
        if (this.lastModifiedAt == null) {
            this.lastModifiedAt = creationDate;
        }
    }

    /**
     * Returns the user ID of the user that modified this entity the last time.
     */
    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    /**
     * Returns the date and time of the last modification of this entity.
     */
    public LocalDateTime getLastModifiedAt() {
        return this.lastModifiedAt;
    }

    /**
     * Tells this entity to retain the specified user ID and point in time as the
     * lastModifiedBy and the lastModifiedAt of this entity.
     * <p>
     * Usually this method is called by {@code Repository} implementations that
     * support auditable entities.
     * </p>
     */
    void trackModification(String lastModifierId, LocalDateTime lastModificationDate) {
        this.lastModifiedBy = lastModifierId;
        this.lastModifiedAt = lastModificationDate;
    }

    /**
     * Supports tracking of custom audit information which is supposed to override
     * the automatic audit information.
     * <p>
     * The given creation information overrides the current creation information, if
     * there is no creation information available or if the given creation date is
     * before the current creation date.
     * </p>
     * <p>
     * The given modification information overrides the current modification
     * information, if there is no modification information available or if the
     * given modification date is after the current modification date.
     * </p>
     */
    protected final void trackCustomAuditInformation(String creatorId, LocalDateTime creationDate,
                                                     String lastModifierId, LocalDateTime lastModificationDate) {
        Objects.requireNonNull(creatorId, "Missing required parameter creatorId!");
        Objects.requireNonNull(creationDate, "Missing required parameter creationDate!");
        Objects.requireNonNull(lastModifierId, "Missing required parameter lastModifierId!");
        Objects.requireNonNull(lastModificationDate, "Missing required parameter lastModificationDate!");
        if (this.createdAt == null || creationDate.isBefore(this.createdAt)) {
            this.createdBy = creatorId;
            this.createdAt = creationDate;
        }
        if (this.lastModifiedAt == null || lastModificationDate.isAfter(this.lastModifiedAt)) {
            this.lastModifiedBy = lastModifierId;
            this.lastModifiedAt = lastModificationDate;
        }
    }
}
