package org.neo4j.ogm.unit.metadata.validation;

import org.junit.Test;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.Transient;
import org.neo4j.ogm.metadata.info.validation.NodeValidator;
import org.neo4j.ogm.metadata.info.validation.RelationshipValidator;
import org.neo4j.ogm.metadata.info.validation.Validator;
import org.neo4j.ogm.metadata.info.validation.ValidatorResolver;

import static org.junit.Assert.assertEquals;

public class ValidatorResolverTest {

    @Test
    public void shouldReturnValidatorForRelationshipEntity() {
        ValidatorResolver resolver = new ValidatorResolver();

        Validator validator = resolver.getValidator(RelationshipEntity.CLASS);

        assertEquals(RelationshipValidator.class, validator.getClass());
    }

    @Test
    public void shouldReturnValidatorForNodeEntity() {
        ValidatorResolver resolver = new ValidatorResolver();

        Validator validator = resolver.getValidator(NodeEntity.CLASS);

        assertEquals(NodeValidator.class, validator.getClass());
    }

    @Test
    public void shouldReturnNullForNotExistingValidatorForTransient() {
        ValidatorResolver resolver = new ValidatorResolver();

        Validator validator = resolver.getValidator(Transient.CLASS);

        assertEquals(null, validator);
    }
}