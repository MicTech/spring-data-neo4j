package org.neo4j.ogm.unit.metadata.info.validation;

import org.junit.Test;
import org.neo4j.ogm.metadata.info.validation.RequiredCombinationRule;

import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class RequiredCombinationRuleTest {
    @Test
    public void shouldFailForMissingOneFieldAnnotations() {
        Collection<String> classAnnotations = new ArrayList<String>(){{
            add("org.neo4j.ogm.annotation.RelationshipEntity");
        }};
        Collection<String> fieldsAnnotations = new ArrayList<String>() {{
            add("org.neo4j.ogm.annotation.StartNode");
        }};

        String[] requiredClassAnnotations = new String[]{"org.neo4j.ogm.annotation.RelationshipEntity"};
        String[] requiredFieldsAnnotations = new String[]{"org.neo4j.ogm.annotation.StartNode", "org.neo4j.ogm.annotation.EndNode"};

        String errorMessage = "Error message";

        RequiredCombinationRule rule = new RequiredCombinationRule(requiredClassAnnotations,
                requiredFieldsAnnotations,
                errorMessage);

        rule.validateClassAnnotations(classAnnotations);

        rule.validateFieldsAnnotations(fieldsAnnotations);

        assertFalse(rule.isValid());

        assertEquals(errorMessage, rule.getErrorMessage());
    }

    @Test
    public void shouldPassNothingIsMissing() {
        Collection<String> classAnnotations = new ArrayList<String>(){{
            add("org.neo4j.ogm.annotation.RelationshipEntity");
        }};
        Collection<String> fieldsAnnotations = new ArrayList<String>() {{
            add("org.neo4j.ogm.annotation.StartNode");
            add("org.neo4j.ogm.annotation.EndNode");
        }};

        String[] requiredClassAnnotations = new String[]{"org.neo4j.ogm.annotation.RelationshipEntity"};
        String[] requiredFieldsAnnotations = new String[]{"org.neo4j.ogm.annotation.StartNode", "org.neo4j.ogm.annotation.EndNode"};

        String errorMessage = "Error message";

        RequiredCombinationRule rule = new RequiredCombinationRule(requiredClassAnnotations,
                requiredFieldsAnnotations,
                errorMessage);

        rule.validateClassAnnotations(classAnnotations);

        rule.validateFieldsAnnotations(fieldsAnnotations);

        assertTrue(rule.isValid());
    }

    @Test
    public void shouldFailForMissingClassAnnotations() {
        Collection<String> fieldsAnnotations = new ArrayList<String>() {{
            add("org.neo4j.ogm.annotation.StartNode");
            add("org.neo4j.ogm.annotation.EndNode");
        }};

        String[] requiredClassAnnotations = new String[]{"org.neo4j.ogm.annotation.RelationshipEntity"};
        String[] requiredFieldsAnnotations = new String[]{"org.neo4j.ogm.annotation.StartNode", "org.neo4j.ogm.annotation.EndNode"};

        String errorMessage = "Error message";

        RequiredCombinationRule rule = new RequiredCombinationRule(requiredClassAnnotations,
                requiredFieldsAnnotations,
                errorMessage);

        rule.validateClassAnnotations(null);

        rule.validateFieldsAnnotations(fieldsAnnotations);

        assertFalse(rule.isValid());
    }

    @Test
    public void shouldPassBecauseClassHaveDifferentAnnotations() {
        Collection<String> classAnnotations = new ArrayList<String>(){{
            add("org.neo4j.ogm.annotation.NodeEntity");
        }};

        String[] requiredClassAnnotations = new String[]{"org.neo4j.ogm.annotation.RelationshipEntity"};
        String[] requiredFieldsAnnotations = new String[]{"org.neo4j.ogm.annotation.StartNode", "org.neo4j.ogm.annotation.EndNode"};

        String errorMessage = "Error message";

        RequiredCombinationRule rule = new RequiredCombinationRule(requiredClassAnnotations,
                requiredFieldsAnnotations,
                errorMessage);

        rule.validateClassAnnotations(classAnnotations);

        rule.validateFieldsAnnotations(null);

        //assertFalse(rule.isValid());
    }

    @Test
    public void shouldFailBecauseFieldsAnnotationsMissing() {
        Collection<String> classAnnotations = new ArrayList<String>(){{
            add("org.neo4j.ogm.annotation.RelationshipEntity");
        }};

        String[] requiredClassAnnotations = new String[]{"org.neo4j.ogm.annotation.RelationshipEntity"};
        String[] requiredFieldsAnnotations = new String[]{"org.neo4j.ogm.annotation.StartNode", "org.neo4j.ogm.annotation.EndNode"};

        String errorMessage = "Error message";

        RequiredCombinationRule rule = new RequiredCombinationRule(requiredClassAnnotations,
                requiredFieldsAnnotations,
                errorMessage);

        rule.validateClassAnnotations(classAnnotations);

        rule.validateFieldsAnnotations(null);

        assertFalse(rule.isValid());

        assertEquals(errorMessage, rule.getErrorMessage());
    }
}