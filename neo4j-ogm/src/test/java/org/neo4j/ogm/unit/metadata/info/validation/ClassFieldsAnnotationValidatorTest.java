package org.neo4j.ogm.unit.metadata.info.validation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.neo4j.ogm.metadata.AnnotationsException;
import org.neo4j.ogm.metadata.MetaData;
import org.neo4j.ogm.metadata.info.ClassInfo;
import org.neo4j.ogm.metadata.info.DomainInfo;
import org.neo4j.ogm.metadata.info.validation.ClassAnnotationValidator;

public class ClassFieldsAnnotationValidatorTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldRaiseExceptionForMissingEndNode() {
        expectedEx.expect(AnnotationsException.class);
        expectedEx.expectMessage("Class with RelationShipEntity must contain Fields with StartNode and EndNode");

        DomainInfo domainInfo = new DomainInfo("org.neo4j.ogm.domain.incorrect.endnode");
        ClassInfo classInfo = domainInfo.getClass(org.neo4j.ogm.domain.incorrect.endnode.Relationship.class.getName());
        ClassAnnotationValidator validator = new ClassAnnotationValidator(classInfo);

        validator.validate();
    }

    @Test
    public void shoudlRaiseExceptionForMissingStartNode() {
        expectedEx.expect(AnnotationsException.class);
        expectedEx.expectMessage("Class with RelationShipEntity must contain Fields with StartNode and EndNode");

        DomainInfo domainInfo = new DomainInfo("org.neo4j.ogm.domain.incorrect.startnode");
        ClassInfo classInfo = domainInfo.getClass(org.neo4j.ogm.domain.incorrect.startendnode.Relationship.class.getName());
        ClassAnnotationValidator validator = new ClassAnnotationValidator(classInfo);

        validator.validate();
    }

    @Test
    public void shoudlRaiseExceptionForMissingRelationshipEntity() {
        expectedEx.expect(AnnotationsException.class);
        expectedEx.expectMessage("Class with RelationShipEntity must contain Fields with StartNode and EndNode");

        DomainInfo domainInfo = new DomainInfo("org.neo4j.ogm.domain.incorrect.relationship");
        ClassInfo classInfo = domainInfo.getClass(org.neo4j.ogm.domain.incorrect.relationship.Relationship.class.getName());
        ClassAnnotationValidator validator = new ClassAnnotationValidator(classInfo);

        validator.validate();
    }

    @Test
    public void shouldRaiseExceptionForMissingStartNodeAndEndNode() {
        expectedEx.expect(AnnotationsException.class);
        expectedEx.expectMessage("Class with RelationShipEntity must contain Fields with StartNode and EndNode");

        DomainInfo domainInfo = new DomainInfo("org.neo4j.ogm.domain.incorrect.startendnodes");
        ClassInfo classInfo = domainInfo.getClass(org.neo4j.ogm.domain.incorrect.startendnodes.Relationship.class.getName());
        ClassAnnotationValidator validator = new ClassAnnotationValidator(classInfo);

        validator.validate();
    }
}