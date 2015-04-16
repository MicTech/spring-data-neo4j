package org.neo4j.ogm.unit.metadata.validation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.neo4j.ogm.metadata.info.ClassInfo;
import org.neo4j.ogm.metadata.info.DomainInfo;
import org.neo4j.ogm.metadata.info.validation.RelationshipValidator;
import org.neo4j.ogm.metadata.info.validation.ValidationException;

import static org.junit.Assert.assertEquals;

public class RelationshipValidatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldReturnFalseForUniqueAnnotationsForFields() {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("org.neo4j.ogm.domain.validations.Relationship.incorrect.RelationshipSameStartAndEndNode.Relationship - StartNode and EndNode must be on separate fields.");

        new DomainInfo("org.neo4j.ogm.domain.validations.Relationship.incorrect.RelationshipSameStartAndEndNode");
    }

    @Test
    public void shouldBeValidRelationshipEntity() {
        DomainInfo domainInfo = new DomainInfo("org.neo4j.ogm.domain.validations.Relationship.correct");

        ClassInfo classInfo = domainInfo.getClass(org.neo4j.ogm.domain.validations.Relationship.correct.Relationship.class.getName());

        RelationshipValidator validator = new RelationshipValidator();

        validator.validate(classInfo);

        assertEquals("", validator.getErrorMessage());
    }

    @Test
    public void shouldBeValidForStartNodeAndEndNodeAreOnDifferentFields() {
        DomainInfo domainInfo = new DomainInfo("org.neo4j.ogm.domain.validations.Relationship.correct");

        ClassInfo classInfo = domainInfo.getClass(org.neo4j.ogm.domain.validations.Relationship.correct.Relationship.class.getName());

        RelationshipValidator validator = new RelationshipValidator();

        validator.validate(classInfo);

        assertEquals("", validator.getErrorMessage());
    }

    @Test
    public void shouldBeInvalidBecauseClassCouldNotBePersistedAndNot() {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("org.neo4j.ogm.domain.validations.Relationship.incorrect.PersistedNot.Relationship - Transient and RelationshipEntity are in conflict");

        new DomainInfo("org.neo4j.ogm.domain.validations.Relationship.incorrect.PersistedNot");
    }

    @Test
    public void shouldBeInvalidForMissingStartNode() {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("org.neo4j.ogm.domain.validations.Relationship.incorrect.MissingStartNode.Relationship - Only one StartNode or EndNode is Allowed at RelationshipEntity.");

        new DomainInfo("org.neo4j.ogm.domain.validations.Relationship.incorrect.MissingStartNode");
    }

    @Test
    public void shouldBeInvalidForMissingEndNode() {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("org.neo4j.ogm.domain.validations.Relationship.incorrect.MissingEndNode.Relationship - Only one StartNode or EndNode is Allowed at RelationshipEntity.");

        new DomainInfo("org.neo4j.ogm.domain.validations.Relationship.incorrect.MissingEndNode");
    }

    @Test
    public void shouldBeInvalidForMultipleStartNodes() {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("org.neo4j.ogm.domain.validations.Relationship.incorrect.MultipleStartNode.Relationship - StartNode and EndNode must be on separate fields.");

        new DomainInfo("org.neo4j.ogm.domain.validations.Relationship.incorrect.MultipleStartNode");
    }

    @Test
    public void shouldBeInvalidForMultipleEndNodes() {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("org.neo4j.ogm.domain.validations.Relationship.incorrect.MultipleEndNode.Relationship - StartNode and EndNode must be on separate fields.");

        new DomainInfo("org.neo4j.ogm.domain.validations.Relationship.incorrect.MultipleEndNode");
    }

    @Test
    public void shouldBeInvalidForStartNodeAndEndNodeAreOnSameField() {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("org.neo4j.ogm.domain.validations.Relationship.incorrect.RelationshipSameStartAndEndNode.Relationship - StartNode and EndNode must be on separate fields.");

        new DomainInfo("org.neo4j.ogm.domain.validations.Relationship.incorrect.RelationshipSameStartAndEndNode");
    }
}
