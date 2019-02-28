package com.example.ppmtool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ppmtool.domain.Project;
import java.lang.String;
import java.util.List;
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
	
	Project findByProjectIdentifier(String projectidentifier);
	Iterable<Project> findAll();
	
	
}
