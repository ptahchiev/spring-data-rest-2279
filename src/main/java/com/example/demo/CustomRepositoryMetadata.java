package com.example.demo;

import com.example.demo.entity.CustomerEntity;
import org.springframework.data.repository.core.CrudMethods;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.AbstractRepositoryMetadata;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.data.util.TypeInformation;
import org.springframework.lang.NonNull;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Set;

/**
 * An implementation of {@link org.springframework.data.repository.core.RepositoryMetadata} that we customize because
 * our JPA repositories deal with interfaces instead of entity classes.
 *
 * @author Petar Tahchiev
 * @since 0.6
 */
public class CustomRepositoryMetadata implements RepositoryMetadata {

    private final RepositoryMetadata delegate;

    private final Class<?> domainClass;

    private final Class<?> domainInterface;

    public CustomRepositoryMetadata(Class<?> repositoryInterface) {

        this.delegate = AbstractRepositoryMetadata.getMetadata(repositoryInterface);

        // Translate interface into domain class

        this.domainClass = CustomRepositoryMetadata.findDomainClass(delegate.getDomainType());
        this.domainInterface = this.delegate.getDomainType();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.core.RepositoryMetadata#getIdType()
     */
    @Override
    public Class<?> getIdType() {
        return this.delegate.getIdType();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.core.RepositoryMetadata#getDomainType()
     */
    @Override
    public Class<?> getDomainType() {
        return this.domainClass;
    }

//    @Override
//    public TypeInformation<?> getIdTypeInformation() {
//        return this.delegate.getIdTypeInformation();
//    }
//
//    @Override
//    public TypeInformation<?> getDomainTypeInformation() {
//        return ClassTypeInformation.from(this.domainClass);
//    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.core.RepositoryMetadata#getRepositoryInterface()
     */
    @Override
    public Class<?> getRepositoryInterface() {
        return this.delegate.getRepositoryInterface();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.core.RepositoryMetadata#getReturnedDomainClass(java.lang.reflect.Method)
     */
    @Override
    public Class<?> getReturnedDomainClass(Method method) {
        Class<?> c = delegate.getReturnedDomainClass(method);
        return domainInterface.equals(c) ? domainClass : c;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.core.RepositoryMetadata#getReturnType(java.lang.reflect.Method)
     */
    @Override
    public TypeInformation<?> getReturnType(Method method) {
        return delegate.getReturnType(method);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.core.RepositoryMetadata#getCrudMethods()
     */
    @Override
    public CrudMethods getCrudMethods() {
        return this.delegate.getCrudMethods();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.core.RepositoryMetadata#isPagingRepository()
     */
    @Override
    public boolean isPagingRepository() {
        return this.delegate.isPagingRepository();
    }

    @Override
    public Set<Class<?>> getAlternativeDomainTypes() {
        return Collections.<Class<?>>singleton(domainInterface);
    }

    @Override
    public boolean isReactiveRepository() {
        return false;
    }

    private static Class<?> findDomainClass(@NonNull Class<?> domainClass) {
        if (domainClass.isInterface()) {
            try {
                return CustomerEntity.class;
            } catch (Exception e) {
                throw new IllegalStateException("Failed to resolve target for " + domainClass, e);
            }
        }
        return domainClass;
    }

}
