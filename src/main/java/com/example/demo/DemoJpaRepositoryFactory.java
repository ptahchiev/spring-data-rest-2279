package com.example.demo;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.jpa.repository.support.QuerydslJpaPredicateExecutor;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;

import javax.persistence.EntityManager;

/**
 * {@link org.springframework.data.jpa.repository.support.JpaRepositoryFactory} implementation that overrides the {@code JpaRepositoryFactory#getRepositoryMetadata} method to allow us to return the
 * custom {@link CustomRepositoryMetadata}.
 *
 * @since 0.6
 */
public class DemoJpaRepositoryFactory extends JpaRepositoryFactory {

    public DemoJpaRepositoryFactory(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected RepositoryMetadata getRepositoryMetadata(Class<?> repositoryInterface) {
        return new CustomRepositoryMetadata(repositoryInterface);
    }

    @Override
    protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
        if (QuerydslJpaPredicateExecutor.class.isAssignableFrom(information.getRepositoryBaseClass())) {
            JpaEntityInformation<?, ?> entityInformation = getEntityInformation(information.getDomainType());
            return getTargetRepositoryViaReflection(information, entityInformation, entityManager);
        }
        return super.getTargetRepository(information, entityManager);
    }
}
