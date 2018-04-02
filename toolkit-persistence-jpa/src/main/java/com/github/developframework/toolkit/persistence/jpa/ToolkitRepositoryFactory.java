package com.github.developframework.toolkit.persistence.jpa;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author qiuzhenhao
 */
public class ToolkitRepositoryFactory extends JpaRepositoryFactory {

    public ToolkitRepositoryFactory(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
        return new ToolkitRepositoryImpl<T, ID>((Class<T>) information.getDomainType(), entityManager);
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return ToolkitRepositoryImpl.class;
    }
}
