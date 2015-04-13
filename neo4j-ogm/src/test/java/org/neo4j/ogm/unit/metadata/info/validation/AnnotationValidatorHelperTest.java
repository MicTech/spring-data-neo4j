package org.neo4j.ogm.unit.metadata.info.validation;

import org.junit.Test;
import org.neo4j.ogm.metadata.info.validation.AnnotationValidatorHelper;

import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnnotationValidatorHelperTest {

    @Test
    public void shouldReturnTrueWhenCombinationAreInAnnotations() {
        Collection<String> annotationNames = new ArrayList<>();
        annotationNames.add("EndNode");
        annotationNames.add("StartNode");

        String[] combinations = new String[] {"StartNode", "EndNode"};

        AnnotationValidatorHelper helper = new AnnotationValidatorHelper();

        boolean result = helper.areAnnotationsContains(annotationNames, combinations);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenOneElementIsMissing() {
        Collection<String> annotationNames = new ArrayList<>();
        annotationNames.add("EndNode");

        String[] combinations = new String[] {"StartNode", "EndNode"};

        AnnotationValidatorHelper helper = new AnnotationValidatorHelper();

        boolean result = helper.areAnnotationsContains(annotationNames, combinations);

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseWhenNothingFromCombinationIsPresent() {
        Collection<String> annotationNames = new ArrayList<>();
        annotationNames.add("MiddleNode");

        String[] combination = new String[] {"StartNode", "EndNode"};

        AnnotationValidatorHelper helper = new AnnotationValidatorHelper();

        boolean result = helper.areAnnotationsContains(annotationNames, combination);

        assertFalse(result);
    }
}
