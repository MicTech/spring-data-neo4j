package org.neo4j.ogm.unit.metadata.info.validation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.neo4j.ogm.metadata.AnnotationsException;
import org.neo4j.ogm.metadata.info.ClassInfo;
import org.neo4j.ogm.metadata.info.DomainInfo;
import org.neo4j.ogm.metadata.info.validation.ClassAnnotationValidator;

public class ClassAnnotationValidatorTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldRaiseExceptionForWholeObjectTree() {
        expectedEx.expect(AnnotationsException.class);
        expectedEx.expectMessage("Class could not be NodeEntity and Transient at the same time");

        DomainInfo domainInfo = new DomainInfo("org.neo4j.ogm.domain.incorrect.inheritance");
        ClassInfo classInfo = domainInfo.getClass(org.neo4j.ogm.domain.incorrect.inheritance.User.class.getName());
        ClassAnnotationValidator validator = new ClassAnnotationValidator(classInfo);

        validator.validate();
    }

    @Test
    public void shouldRaiseExceptionForNodeClassAndTransient() {
        expectedEx.expect(AnnotationsException.class);
        expectedEx.expectMessage("Class could not be NodeEntity and Transient at the same time");

        DomainInfo domainInfo = new DomainInfo("org.neo4j.ogm.domain.incorrect.persistence");
        ClassInfo classInfo = domainInfo.getClass(org.neo4j.ogm.domain.incorrect.persistence.User.class.getName());
        ClassAnnotationValidator validator = new ClassAnnotationValidator(classInfo);

        validator.validate();
    }
}