package org.neo4j.ogm.metadata.info.validation;

import org.neo4j.ogm.metadata.info.ClassInfo;

public interface AnnotationValidator {
    void validate(ClassInfo classInfo);
}
