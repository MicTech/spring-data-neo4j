package org.neo4j.ogm.unit.metadata.info.validation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.neo4j.ogm.metadata.AnnotationsException;
import org.neo4j.ogm.metadata.MetaData;
import org.neo4j.ogm.metadata.info.ClassInfo;

public class FieldAnnotationsValidatorTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldRaiseExceptionForInvalidFieldAnnotations() {
        expectedEx.expect(AnnotationsException.class);
        expectedEx.expectMessage("org.neo4j.ogm.domain.incorrect.startendnode.Relationship - Is not possible to have StartNode and EndNode as one field on the object");

        new MetaData("org.neo4j.ogm.domain.incorrect.startendnode");
    }
}
