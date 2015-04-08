package org.neo4j.ogm.unit.metadata.info.validation;

import org.junit.Test;
import org.neo4j.ogm.metadata.info.validation.UniqueRequiredCombinationRule;

import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class UniqueRequiredCombinationRuleTest {
    @Test
    public void shouldFailForTwoEndNodes() {
        Collection<String> classAnnotations = new ArrayList<String>(){{
            add("org.neo4j.ogm.annotation.RelationshipEntity");
        }};
        Collection<String> fieldsAnnotations = new ArrayList<String>() {{
            add("org.neo4j.ogm.annotation.StartNode");
            add("org.neo4j.ogm.annotation.EndNode");
            add("org.neo4j.ogm.annotation.EndNode");
        }};

        String[] requiredClassAnnotations = new String[]{"org.neo4j.ogm.annotation.RelationshipEntity"};
        String[] requiredFieldsAnnotations = new String[]{"org.neo4j.ogm.annotation.StartNode", "org.neo4j.ogm.annotation.EndNode"};

        String errorMessage = "Error message";

        UniqueRequiredCombinationRule rule = new UniqueRequiredCombinationRule(requiredClassAnnotations,
                requiredFieldsAnnotations,
                errorMessage);

        rule.validateClassAnnotations(classAnnotations);

        rule.validateFieldsAnnotations(fieldsAnnotations);

        assertFalse(rule.isValid());
    }
}
