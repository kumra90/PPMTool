package com.learnfullstack.ppmtool.repository;

import com.learnfullstack.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    default Iterable<Project> findAllById(Iterable<Long> iterable) {
        return null;
    }
}
