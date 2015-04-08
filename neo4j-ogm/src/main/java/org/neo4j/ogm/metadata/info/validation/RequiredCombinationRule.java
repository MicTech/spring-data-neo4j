package org.neo4j.ogm.metadata.info.validation;

import java.util.Collection;

public class RequiredCombinationRule extends ValidationRule {

    public RequiredCombinationRule(String[] classAnnotations, String[] fieldsAnnotations, String errorMessage) {
        super(classAnnotations, fieldsAnnotations, errorMessage);
    }

    @Override
    public void validateClassAnnotations(Collection<String> annotationsNames) {
        if(this.getClassAnnotations() == null) {
            this.setAreClassAnnotationsValid(true);
            return;
        }

        if(this.getClassAnnotations().length > 0 && annotationsNames == null) {
            this.setAreClassAnnotationsValid(false);
            return;
        }

        if(this.getClassAnnotations().length > 0 && annotationsNames.isEmpty()) {
            this.setAreClassAnnotationsValid(false);
            return;
        }

        AnnotationValidatorHelper helper = new AnnotationValidatorHelper();

        boolean inList = helper.areAnnotationsContains(annotationsNames, this.getClassAnnotations());

        this.setAreClassAnnotationsValid(inList);
    }

    @Override
    public void validateFieldsAnnotations(Collection<String> annotationsNames) {
        if(this.getFieldsAnnotations() == null) {
            this.setAreFieldsAnnotationsValid(true);
            return;
        }

        if(this.getFieldsAnnotations().length > 0 && annotationsNames == null) {
            this.setAreFieldsAnnotationsValid(false);
            return;
        }
        
        AnnotationValidatorHelper helper = new AnnotationValidatorHelper();

        boolean inList = helper.areAnnotationsContains(annotationsNames, this.getFieldsAnnotations());

        this.setAreFieldsAnnotationsValid(inList);
    }
}
