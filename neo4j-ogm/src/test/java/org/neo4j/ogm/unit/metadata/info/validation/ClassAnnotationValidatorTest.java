package org.neo4j.ogm.unit.metadata.info.validation;

import org.junit.Test;
import org.neo4j.ogm.metadata.AnnotationsException;
import org.neo4j.ogm.metadata.MetaData;

public class ClassAnnotationValidatorTest {

    @Test(expected = AnnotationsException.class)
    public void shouldRaiseExceptionForWholeObjectTree() {
        new MetaData("org.neo4j.ogm.domain.incorrect.inheritance");
    }

    @Test(expected = AnnotationsException.class)
    public void shouldRaiseExceptionForNodeClassAndTransient() {
        new MetaData("org.neo4j.ogm.domain.incorrect.persistence");
    }
}