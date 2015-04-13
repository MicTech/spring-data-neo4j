package org.neo4j.ogm.metadata.info.validation;

import org.neo4j.ogm.metadata.info.ClassInfo;

import java.util.ArrayList;
import java.util.Collection;

public class AnnotationValidationService {
    Collection<AnnotationValidator> validators;

    public AnnotationValidationService() {
        this.validators = new ArrayList<>();
        this.validators.add(new ClassAnnotationValidator());
        this.validators.add(new FieldAnnotationValidator());
        this.validators.add(new ClassFieldsAnnotationValidator());

    }

    public void validate(ClassInfo classInfo) {
        for(AnnotationValidator validator : validators) {
            validator.validate(classInfo);
        }
    }
}
