package com.nazar.taskmanagement.service;

import com.nazar.taskmanagement.entity.Project;
import com.nazar.taskmanagement.entity.Role;
import com.nazar.taskmanagement.entity.User;
import com.nazar.taskmanagement.exception.ResourceNotFoundException;
import com.nazar.taskmanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserService userService;

    public Project createProject(Project project, String ownerUsername) {
        User owner = userService.getUserByUsername(ownerUsername);
        project.setOwner(owner);
        return projectRepository.save(project);
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
    }

    public Page<Project> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    public Page<Project> getUserProjects(String username, Pageable pageable) {
        User user = userService.getUserByUsername(username);
        return projectRepository.findUserProjects(user, pageable);
    }
}