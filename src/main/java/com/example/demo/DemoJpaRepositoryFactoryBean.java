package com.example.demo;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * {@link org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean} implementation that overrides the
 * {@link org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean#createRepositoryFactory} method to allow us to return the custom
 * {@link DemoJpaRepositoryFactory}.
 *
 * @author Petar Tahchiev
 * @since 0.6
 */
public class DemoJpaRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable> extends JpaRepositoryFactoryBean<T, S, ID> {

    /**
     * Creates a new {@link JpaRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public DemoJpaRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        DemoJpaRepositoryFactory result = new DemoJpaRepositoryFactory(entityManager);
        //result.setEntityPathResolver(entityPathResolver);
        return result;
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
    }
}

