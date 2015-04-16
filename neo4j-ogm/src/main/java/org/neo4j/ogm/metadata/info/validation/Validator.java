package org.neo4j.ogm.metadata.info.validation;

import org.neo4j.ogm.metadata.info.ClassInfo;

public abstract class Validator {
    protected StringBuilder errorMessage;
    protected ClassValidator clazz;
    protected boolean areValid;

    public Validator() {
        this.errorMessage = new StringBuilder();
    }

    public abstract void validate(ClassInfo classInfo);

    public String getErrorMessage() {
        return errorMessage.toString();
    }
}
