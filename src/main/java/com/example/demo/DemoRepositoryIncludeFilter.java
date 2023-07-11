package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.data.util.ClassTypeInformation;

import java.io.IOException;

/**
 * A component-scan filter to include only repositories for which we can correctly resolve the entity arguments.
 * In a situation where we have multiple modules:
 * - moduleA defines entityA
 * - moduleB defines entityB extends entityA, repository for entityB and also moduleA is an optional module.
 * If the client opts-in to include only moduleB, spring data will throw an exception when trying to resolve repository
 * for entityB, so we need to exclude it from the component scan.
 * See: https://jira.spring.io/browse/DATACMNS-1617
 *
 * @author Petar Tahchiev
 * @since 2.2
 */
public class DemoRepositoryIncludeFilter implements TypeFilter {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {

        try {
            ClassTypeInformation.from(org.springframework.util.ClassUtils
                .forName(metadataReader.getClassMetadata().getClassName(), this.getClass().getClassLoader()));
        } catch (ClassNotFoundException | NoClassDefFoundError cnfex) {
            LOG.debug("Skipping repository due to no class found: " + cnfex.getMessage());
            return false;
        }

        return true;
    }
}