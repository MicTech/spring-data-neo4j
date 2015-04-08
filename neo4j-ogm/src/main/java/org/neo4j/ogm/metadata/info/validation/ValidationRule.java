package org.neo4j.ogm.metadata.info.validation;

import java.util.Collection;

public abstract class ValidationRule {

    private String[] classAnnotations;
    private String[] fieldsAnnotations;
    private String errorMessageTemplate;
    private boolean areClassAnnotationsValid;
    private boolean classAnnotationsValidated;
    private boolean areFieldsAnnotationsValid;
    private boolean fieldsAnnotationsValidated;

    public ValidationRule(String[] classAnnotations, String[] fieldsAnnotations, String errorMessage) {
        this.classAnnotations = classAnnotations;
        this.fieldsAnnotations = fieldsAnnotations;
        this.errorMessageTemplate = errorMessage;
    }

    protected void setAreClassAnnotationsValid(boolean areClassAnnotationsValid) {
        this.classAnnotationsValidated = true;
        this.areClassAnnotationsValid = areClassAnnotationsValid;
    }

    protected void setAreFieldsAnnotationsValid(boolean areFieldsAnnotationsValid) {
        this.fieldsAnnotationsValidated = true;
        this.areFieldsAnnotationsValid = areFieldsAnnotationsValid;
    }

    public String[] getClassAnnotations() {
        return classAnnotations;
    }

    public String[] getFieldsAnnotations() {
        return fieldsAnnotations;
    }

    public String getErrorMessage() {
        return getErrorMessage(null);
    }

    public String getErrorMessage(Object... args) {
        return String.format(errorMessageTemplate, args);
    }

    public abstract void validateClassAnnotations(Collection<String> annotationsNames);

    public abstract void validateFieldsAnnotations(Collection<String> annotationsNames);

    public boolean isValid() {
        if (classAnnotationsValidated == false && fieldsAnnotationsValidated == false) {
            return true;
        }

        if (classAnnotationsValidated == false && fieldsAnnotationsValidated == true) {
            return areFieldsAnnotationsValid;
        }

        if (fieldsAnnotationsValidated == false && classAnnotationsValidated == true) {
            return areClassAnnotationsValid;
        }

        if (classAnnotationsValidated && fieldsAnnotationsValidated) {
            if(areClassAnnotationsValid == false && areFieldsAnnotationsValid == false) {
                return true;
            }

            return areClassAnnotationsValid && areFieldsAnnotationsValid;
        }

        return false;
    }
}
