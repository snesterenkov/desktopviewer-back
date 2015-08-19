package com.eklib.desktopviewer.services.companystructure;

import com.eklib.desktopviewer.convertor.fromdto.companystructure.ProjectFromDTO;
import com.eklib.desktopviewer.convertor.fromdto.companystructure.ProjectFromDetailDTO;
import com.eklib.desktopviewer.convertor.todto.companystructure.ProjectToDTO;
import com.eklib.desktopviewer.convertor.todto.companystructure.ProjectToDetailDTO;
import com.eklib.desktopviewer.dto.companystructure.ProjectDTO;
import com.eklib.desktopviewer.dto.companystructure.ProjectDetailDTO;
import com.eklib.desktopviewer.dto.enums.StatusDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.ProjectEntity;
import com.eklib.desktopviewer.persistance.model.enums.StatusEnum;
import com.eklib.desktopviewer.persistance.model.security.RoleEntity;
import com.eklib.desktopviewer.persistance.model.security.UserEntity;
import com.eklib.desktopviewer.persistance.repository.companystructure.DepartmentRepository;
import com.eklib.desktopviewer.persistance.repository.companystructure.ProjectRepository;
import com.eklib.desktopviewer.persistance.repository.security.UserRepository;
import com.google.common.collect.FluentIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Set;

/**
 * Created by vadim on 01.12.2014.
 */
@Service
@Transactional
public class ProjectServicesImpl implements ProjectService {

    @Autowired
    private ProjectFromDTO projectFromDTO;
    @Autowired
    private ProjectFromDetailDTO projectFromDetailDTO;
    @Autowired
    private ProjectToDTO projectToDTO;
    @Autowired
    private ProjectToDetailDTO projectToDetailDTO;

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ProjectDTO insert(ProjectDTO projectDTO, String client) {
        Assert.hasLength(projectDTO.getName(), "Project name must not be null and not the empty String.");
        Assert.isNull(projectDTO.getId(), "Project id is not null");
        boolean hasDepartment = departmentRepository.hasOpenDepartmentForClient(projectDTO.getDepartmentId(), client);
        Assert.isTrue(hasDepartment, "Department id not exist for client");
        ProjectEntity newProject = projectFromDTO.apply(projectDTO);
        UserEntity userEntity = userRepository.getUserByName(client);
        if(userEntity == null){
            Assert.isTrue(false, "Client is null");
            return new ProjectDTO();
        }
        if(newProject != null) {
            newProject.setOwner(userEntity);
            ProjectEntity updatedProject = projectRepository.insert(newProject);
            Set<RoleEntity> roles = userEntity.readRoles();
            if(!roles.contains(RoleEntity.DESK_USER_PROJECT)){
                roles.add(RoleEntity.DESK_USER_PROJECT);
                userEntity.writeRoles(roles);
                userRepository.update(userEntity);
            }
            return projectToDTO.apply(updatedProject);
        }
        Assert.isTrue(false, "Cann`t create project");
        return new ProjectDTO();
    }

    @Override
    public ProjectDTO update(Long id, ProjectDTO dto, String client) {
        Assert.hasLength(dto.getName(), "Project name must not be null and not the empty String.");
        boolean hasDepartment = departmentRepository.hasOpenDepartmentForClient(dto.getDepartmentId(), client);
        Assert.isTrue(hasDepartment, "Department id not exist for client");
        dto.setId(id);
        ProjectEntity newProject = projectFromDTO.apply(dto);
        if(newProject != null && newProject.getOwner() != null ){
            if(newProject.getOwner().getLogin().equals(client)){
                ProjectEntity updatedProject = projectRepository.update(newProject);
                return projectToDTO.apply(updatedProject);
            }
            Assert.isTrue(false, "Cann`t update not your project");
        }
        Assert.isTrue(false, "Cann`t find project");
        return new ProjectDTO();
    }

    @Override
    public ProjectDetailDTO detailUpdate(Long id, ProjectDetailDTO dto, String client) {
        Assert.hasLength(dto.getName(), "Project name must not be null and not the empty String.");
        boolean hasDepartment = departmentRepository.hasOpenDepartmentForClient(dto.getDepartmentId(), client);
        Assert.isTrue(hasDepartment, "Department id not exist for client");
        dto.setId(id);
        ProjectEntity newProject = projectFromDetailDTO.apply(dto);
        if(newProject != null && newProject.getOwner() != null ){
            if(newProject.getOwner().getLogin().equals(client)){
                ProjectEntity updatedProject = projectRepository.update(newProject);
                return projectToDetailDTO.apply(updatedProject);
            }
            Assert.isTrue(false, "Cann`t update not your project");
        }
        Assert.isTrue(false, "Cann`t find project");
        return new ProjectDetailDTO();
    }

    @Override
    public ProjectDetailDTO findById(Long id, String client) {
        ProjectEntity projectEntity = projectRepository.findById(id);
        if(projectEntity != null && projectEntity.getOwner() != null) {
            if (projectEntity.getOwner().getLogin().equals(client)) {
                return projectToDetailDTO.apply(projectEntity);
            }
        }
        Assert.isTrue(false, "Can`t find project");
        return new ProjectDetailDTO();
    }

    @Override
    public Collection<ProjectDetailDTO> findAll(String client) {
        return FluentIterable.from(projectRepository.findByUser(client)).transform(projectToDetailDTO).toList();
    }

    @Override
    public ProjectDetailDTO changeStatus(Long id, StatusDTO statusDTO, String client) {
        ProjectEntity project = projectRepository.findById(id);
        StatusEnum newStatus = StatusEnum.valueOf(statusDTO.name());
        StatusEnum depStatusEnum = project.getDepartment().getStatus();
        if(depStatusEnum.equals(StatusEnum.OPEN)){
            return changeStatus(project, newStatus,client);
        }
        if(depStatusEnum.equals(StatusEnum.PAUSED) && newStatus.equals(StatusEnum.CLOSED)){
            return changeStatus(project, newStatus,client);
        }
        Assert.isTrue(false, "Cann`t change status in department becouse Company status is " + depStatusEnum);
        return new ProjectDetailDTO();
    }

    private ProjectDetailDTO changeStatus(ProjectEntity project,  StatusEnum newStatus, String client){
        if(project.getOwner().getLogin().equals(client)) {
            if (projectRepository.changeStatus(project, newStatus)) {
                project = projectRepository.findById(project.getId());
            }
            return projectToDetailDTO.apply(project);
        }
        Assert.isTrue(false, "Cann`t change status in company if you not owner");
        return new ProjectDetailDTO();
    }
}
