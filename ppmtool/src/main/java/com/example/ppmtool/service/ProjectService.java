package com.example.ppmtool.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ppmtool.domain.Project;
import com.example.ppmtool.exception.ProjectIdException;
import com.example.ppmtool.repository.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

	public Project saveOrUpdate(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		}catch(Exception ex) {
			throw new ProjectIdException("Project Id :'"+project.getProjectIdentifier()+"' already exists");
		}
	}
	
	
	public Project findProjectByIdentifier(String projectIdentifier) {
		 Project project =  projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
		if(project==null) {
			throw new ProjectIdException("Project id :'"+projectIdentifier.toUpperCase()+"'does not  exist");
		}
		 
		 return project;
	}
	 	
	public Iterable<Project> findAllProjects() {
		return projectRepository.findAll();
	}

	
	public void deleteProjectByIdentifier(String projectIdentifier) {
		Project project =projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
		if(project==null) {
			throw new ProjectIdException("Project id :'"+projectIdentifier.toUpperCase()+"'does not  exist");
		}
		 
		 projectRepository.delete(project);
	}
	

	public Project saveOrUpdate(Long id, Project project) {
		Project temp = projectRepository.findById(id).get();
		temp = project;
		return projectRepository.save(temp);
	}

	
//	public List<Project> getAllProjects() {
//		List<Project> list = new ArrayList<>();
//		projectRepository.findAll().forEach(e -> list.add(e));
//		return list;
//	}
}