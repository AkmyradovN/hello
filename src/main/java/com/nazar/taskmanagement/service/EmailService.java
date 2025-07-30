package com.nazar.taskmanagement.service;

import com.nazar.taskmanagement.entity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendTaskAssignmentNotification(Task task) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(task.getAssignee().getEmail());
            message.setSubject("New Task Assigned: " + task.getTitle());
            
            String body = String.format(
                "Hello %s,\n\n" +
                "You have been assigned a new task:\n\n" +
                "Title: %s\n" +
                "Description: %s\n" +
                "Priority: %s\n" +
                "Due Date: %s\n" +
                "Assigned by: %s\n\n" +
                "Please log in to the Task Management System to view more details.\n\n" +
                "Best regards,\n" +
                "Task Management Team",
                task.getAssignee().getFullName(),
                task.getTitle(),
                task.getDescription(),
                task.getPriority(),
                task.getDueDate() != null ? task.getDueDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : "Not set",
                task.getCreator().getFullName()
            );
            
            message.setText(body);
            mailSender.send(message);
            logger.info("Task assignment notification sent to: {}", task.getAssignee().getEmail());
        } catch (Exception e) {
            logger.error("Failed to send task assignment notification", e);
        }
    }

    @Async
    public void sendTaskCompletionNotification(Task task) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(task.getCreator().getEmail());
            message.setSubject("Task Completed: " + task.getTitle());
            
            String body = String.format(
                "Hello %s,\n\n" +
                "The following task has been completed:\n\n" +
                "Title: %s\n" +
                "Completed by: %s\n" +
                "Completion Date: %s\n\n" +
                "Best regards,\n" +
                "Task Management Team",
                task.getCreator().getFullName(),
                task.getTitle(),
                task.getAssignee() != null ? task.getAssignee().getFullName() : "Unknown",
                task.getCompletedAt() != null ? task.getCompletedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : "Just now"
            );
            
            message.setText(body);
            mailSender.send(message);
            logger.info("Task completion notification sent to: {}", task.getCreator().getEmail());
        } catch (Exception e) {
            logger.error("Failed to send task completion notification", e);
        }
    }

    @Async
    public void sendDeadlineReminders(List<Task> tasks) {
        tasks.forEach(task -> {
            if (task.getAssignee() != null) {
                try {
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setTo(task.getAssignee().getEmail());
                    message.setSubject("Task Deadline Reminder: " + task.getTitle());
                    
                    String body = String.format(
                        "Hello %s,\n\n" +
                        "This is a reminder that the following task is due soon:\n\n" +
                        "Title: %s\n" +
                        "Due Date: %s\n" +
                        "Priority: %s\n\n" +
                        "Please ensure to complete it on time.\n\n" +
                        "Best regards,\n" +
                        "Task Management Team",
                        task.getAssignee().getFullName(),
                        task.getTitle(),
                        task.getDueDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                        task.getPriority()
                    );
                    
                    message.setText(body);
                    mailSender.send(message);
                    logger.info("Deadline reminder sent to: {}", task.getAssignee().getEmail());
                } catch (Exception e) {
                    logger.error("Failed to send deadline reminder for task: {}", task.getId(), e);
                }
            }
        });
    }
}