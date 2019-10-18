package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.plans.ProjectDTO;
import ua.prozorro.prozorro.model.plans.Project;

public class ProjectMapper extends AbstractMapper<Project, ProjectDTO> {
    @Override
    public ProjectDTO convertToEntity(Project project) {

        if (project == null || project.getId() == null || project.getId().equals("")) {
            return null;
        }

        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());

        return projectDTO;
    }

    @Override
    public Project convertToObject(ProjectDTO entity) {
        return null;
    }
}
