package org.neo4j.ogm.unit.metadata.info.validation;

import org.junit.Test;
import org.neo4j.ogm.metadata.AnnotationsException;
import org.neo4j.ogm.metadata.MetaData;

public class ClassFieldsAnnotationValidatorTest {
    @Test(expected = AnnotationsException.class)
    public void shouldRaiseExceptionForMissingEndNode() {
        new MetaData("org.neo4j.ogm.domain.incorrect.endnode");
    }

    @Test(expected = AnnotationsException.class)
    public void shoudlRaiseExceptionForMissingStartNode() {
        new MetaData("org.neo4j.ogm.domain.incorrect.startnode");
    }

    @Test(expected = AnnotationsException.class)
    public void shoudlRaiseExceptionForMissingRelationshipEntity() {
        new MetaData("org.neo4j.ogm.domain.incorrect.relationship");
    }

    @Test(expected = AnnotationsException.class)
    public void shouldRaiseExceptionForMissingStartNodeAndEndNode() {
        new MetaData("org.neo4j.ogm.domain.incorrect.startendnodes");
    }

}
