package org.neo4j.ogm.metadata.info.validation;

import java.util.Collection;

public class ForbiddenCombinationRule extends ValidationRule {

    public ForbiddenCombinationRule(String[] classAnnotations, String[] fieldsAnnotations, String errorMessage) {
        super(classAnnotations, fieldsAnnotations, errorMessage);
    }

    @Override
    public void validateClassAnnotations(Collection<String> annotationsNames) {
        if(this.getClassAnnotations() == null) {
            this.setAreClassAnnotationsValid(true);
        }

        AnnotationValidatorHelper helper = new AnnotationValidatorHelper();

        boolean inList = !helper.areAnnotationsContains(annotationsNames, this.getClassAnnotations());

        this.setAreClassAnnotationsValid(inList);
    }

    @Override
    public void validateFieldsAnnotations(Collection<String> annotationsNames) {
        if(this.getFieldsAnnotations() == null) {
            this.setAreFieldsAnnotationsValid(true);
            return;
        }

        AnnotationValidatorHelper helper = new AnnotationValidatorHelper();

        boolean inList = !helper.areAnnotationsContains(annotationsNames, this.getFieldsAnnotations());

        this.setAreFieldsAnnotationsValid(inList);
    }
}