package org.neo4j.ogm.unit.metadata.info.validation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.metadata.AnnotationsException;
import org.neo4j.ogm.metadata.MetaData;
import org.neo4j.ogm.metadata.info.ClassInfo;
import org.neo4j.ogm.metadata.info.DomainInfo;
import org.neo4j.ogm.metadata.info.validation.ClassAnnotationValidator;

public class FieldAnnotationsValidatorTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldRaiseExceptionForInvalidFieldAnnotations() {
        expectedEx.expect(AnnotationsException.class);
        expectedEx.expectMessage("org.neo4j.ogm.domain.incorrect.startendnode.Relationship - Is not possible to have StartNode and EndNode as one field on the object");

        DomainInfo domainInfo = new DomainInfo("org.neo4j.ogm.domain.incorrect.startendnode");
        ClassInfo classInfo = domainInfo.getClass(org.neo4j.ogm.domain.incorrect.startendnode.Relationship.class.getName());
        ClassAnnotationValidator validator = new ClassAnnotationValidator();

        validator.validate(classInfo);
    }
}