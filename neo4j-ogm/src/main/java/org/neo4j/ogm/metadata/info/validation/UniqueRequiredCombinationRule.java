package org.neo4j.ogm.metadata.info.validation;

import java.util.Collection;
import java.util.Collections;

public class UniqueRequiredCombinationRule extends RequiredCombinationRule {
    public UniqueRequiredCombinationRule(String[] classAnnotations, String[] fieldsAnnotations, String errorMessage) {
        super(classAnnotations, fieldsAnnotations, errorMessage);
    }

    @Override
    public void validateFieldsAnnotations(Collection<String> annotationsNames) {
        for(String fieldAnnotation : this.getFieldsAnnotations()) {
            int frequency = Collections.frequency(annotationsNames, fieldAnnotation);

            if (frequency > 1) {
                this.setAreFieldsAnnotationsValid(false);
                return;
            }
        }

        this.setAreFieldsAnnotationsValid(true);
    }
}
