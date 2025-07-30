package com.nazar.taskmanagement.dto.response;

import com.nazar.taskmanagement.entity.Task;

import java.time.LocalDateTime;

public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private Task.TaskStatus status;
    private Task.TaskPriority priority;
    private LocalDateTime dueDate;
    private UserResponse assignee;
    private UserResponse creator;
    private ProjectResponse project;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TaskResponse(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.status = task.getStatus();
        this.priority = task.getPriority();
        this.dueDate = task.getDueDate();
        this.assignee = task.getAssignee() != null ? new UserResponse(task.getAssignee()) : null;
        this.creator = new UserResponse(task.getCreator());
        this.project = task.getProject() != null ? new ProjectResponse(task.getProject()) : null;
        this.createdAt = task.getCreatedAt();
        this.updatedAt = task.getUpdatedAt();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Task.TaskStatus getStatus() { return status; }
    public Task.TaskPriority getPriority() { return priority; }
    public LocalDateTime getDueDate() { return dueDate; }
    public UserResponse getAssignee() { return assignee; }
    public UserResponse getCreator() { return creator; }
    public ProjectResponse getProject() { return project; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
