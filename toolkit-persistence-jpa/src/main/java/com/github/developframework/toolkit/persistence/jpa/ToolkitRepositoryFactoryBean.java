package com.github.developframework.toolkit.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author qiuzhenhao
 */
public class ToolkitRepositoryFactoryBean<T extends JpaRepository<S, ID> , S, ID extends Serializable> extends JpaRepositoryFactoryBean<T, S, ID> {


    /**
     * Creates a new {@link JpaRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public ToolkitRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new ToolkitRepositoryFactory(entityManager);
    }
}
