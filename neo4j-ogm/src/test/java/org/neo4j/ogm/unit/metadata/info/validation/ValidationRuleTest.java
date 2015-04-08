package org.neo4j.ogm.unit.metadata.info.validation;

import org.junit.Test;
import org.neo4j.ogm.metadata.info.validation.RequiredCombinationRule;
import org.neo4j.ogm.metadata.info.validation.ValidationRule;

import static org.junit.Assert.assertEquals;

public class ValidationRuleTest {
    @Test
    public void shouldReturnErrorMessage() {
        String exceptionMessage = "This is exception message";
        ValidationRule rule = new RequiredCombinationRule(null, null, exceptionMessage);

        assertEquals(exceptionMessage, rule.getErrorMessage());
    }

    @Test
    public void shouldReturnFormatedErrorMessageWithOneParameter() {
        String className = "ValidationClass";
        String exceptionMessage = "This is exception message for class %s";
        ValidationRule rule = new RequiredCombinationRule(null, null, exceptionMessage);

        String expectedMessage = "This is exception message for class ValidationClass";

        assertEquals(expectedMessage, rule.getErrorMessage(className));
    }

    @Test
    public void shouldReturnFormatedErrorMessageWithThreeParameters() {
        String className = "ValidationClass";
        String methodName = "validate";
        String fieldName = "isValid";

        String exceptionMessage = "This is exception message for class: %s, method: %s, field: %s";
        ValidationRule rule = new RequiredCombinationRule(null, null, exceptionMessage);

        String expectedMessage = "This is exception message for class: ValidationClass, method: validate, field: isValid";

        assertEquals(expectedMessage, rule.getErrorMessage(className, methodName, fieldName));
    }

    @Test
    public void shouldReturnFormatedErrorMessageWithThreeParametersWithOrder() {
        String className = "ValidationClass";
        String methodName = "validate";
        String fieldName = "isValid";

        String exceptionMessage = "This is exception message for field: %3$s, method: %2$s, class: %1$s";
        ValidationRule rule = new RequiredCombinationRule(null, null, exceptionMessage);

        String expectedMessage = "This is exception message for field: isValid, method: validate, class: ValidationClass";

        assertEquals(expectedMessage, rule.getErrorMessage(className, methodName, fieldName));
    }
}
