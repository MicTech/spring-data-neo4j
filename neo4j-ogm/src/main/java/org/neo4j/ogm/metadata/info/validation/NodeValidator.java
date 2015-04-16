package org.neo4j.ogm.metadata.info.validation;

import org.neo4j.ogm.metadata.info.ClassInfo;

import java.util.Collection;

/**
 *
 * The Validator should be integration point between
 * DomainInfo and validation rules.
 *
 */

public class NodeValidator extends Validator {

    public void validate(ClassInfo classInfo) {
        clazz = new ClassValidator(new ClassValidatorInfo(classInfo));
    }
}
