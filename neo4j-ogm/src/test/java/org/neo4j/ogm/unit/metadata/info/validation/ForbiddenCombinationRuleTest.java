package org.neo4j.ogm.unit.metadata.info.validation;

import org.junit.Test;
import org.neo4j.ogm.metadata.info.validation.ForbiddenCombinationRule;

import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class ForbiddenCombinationRuleTest {
    @Test
    public void shouldFailForForbiddenCombinationOfClassAnnotations() {
        Collection<String> classAnnotations = new ArrayList<String>(){{
            add("org.neo4j.ogm.annotation.NodeEntity");
            add("org.neo4j.ogm.annotation.Transient");
        }};

        String[] forbiddenClassAnnotations = new String[]{"org.neo4j.ogm.annotation.NodeEntity",
                "org.neo4j.ogm.annotation.Transient"};

        String errorMessage = "Error message";

        ForbiddenCombinationRule rule = new ForbiddenCombinationRule(forbiddenClassAnnotations,
                null,
                errorMessage);

        rule.validateClassAnnotations(classAnnotations);
        assertFalse(rule.isValid());
        assertEquals(errorMessage, rule.getErrorMessage());
    }

    @Test
    public void shouldFailForForbiddenCombinationOfFieldAnnotations() {
        Collection<String> fieldAnnotations = new ArrayList<String>(){{
            add("org.neo4j.ogm.annotation.StartNode");
            add("org.neo4j.ogm.annotation.EndNode");
        }};

        String[] forbiddenFieldAnnotations = new String[]{"org.neo4j.ogm.annotation.StartNode",
                "org.neo4j.ogm.annotation.EndNode"};

        String errorMessage = "Error message";

        ForbiddenCombinationRule rule = new ForbiddenCombinationRule(null,
                forbiddenFieldAnnotations,
                errorMessage);

        rule.validateFieldsAnnotations(fieldAnnotations);
        assertFalse(rule.isValid());
        assertEquals(errorMessage, rule.getErrorMessage());
    }
}
