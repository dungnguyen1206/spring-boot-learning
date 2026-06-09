package edu.fu.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;
@MappedSuperclass
public class BaseEntity {
    @Column(name = "Created_At")
    private LocalDateTime createdAt;

    @Column(name = "Update_At")
    private  LocalDateTime updatedAt;

    @Column(name ="Created_By")
    private String createBy;

    @Column(name = "Is_Deleted")
    private Boolean idDeleted;
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Column(name = "Update_By")
    private String updateBy;

    @Column(name= "Deleted_At")
    private LocalDateTime deletedAt;

    //this function
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.createBy = "Dung";
        idDeleted = false;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.updateBy = "Dung";
    }

    @PreRemove
    public void preRemove() {
        this.deletedAt = LocalDateTime.now();
        idDeleted = true;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Boolean getIdDeleted() {
        return idDeleted;
    }

    public void setIdDeleted(Boolean idDeleted) {
        this.idDeleted = idDeleted;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", createBy='" + createBy + '\'' +
                ", idDeleted=" + idDeleted +
                ", updateBy='" + updateBy + '\'' +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
