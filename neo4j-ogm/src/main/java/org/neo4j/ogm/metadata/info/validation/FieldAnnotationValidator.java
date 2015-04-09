package org.neo4j.ogm.metadata.info.validation;

import org.neo4j.ogm.metadata.AnnotationsException;
import org.neo4j.ogm.metadata.info.*;

import java.util.ArrayList;
import java.util.Collection;

public class FieldAnnotationValidator implements AnnotationValidator {

    private final Collection<ValidationRule> rules = new ArrayList<ValidationRule>() {{
        add(new ForbiddenCombinationRule(null,
                                         new String[]{"org.neo4j.ogm.annotation.StartNode", "org.neo4j.ogm.annotation.EndNode"},
                                         "%s - Is not possible to have StartNode and EndNode as one field on the object"));
    }};

    private ClassInfo classInfo;

    public FieldAnnotationValidator(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }

    public void validate() {
        FieldsInfo fieldsInfo = classInfo.fieldsInfo();

        for(FieldInfo fieldAnnotation : fieldsInfo.fields()) {
            areFieldAnnotationsValid(fieldAnnotation);

            boolean valid = true;
            StringBuilder exceptionMessage = new StringBuilder();

            for(ValidationRule rule : rules) {
                if(!rule.isValid()) {
                    valid = false;
                    exceptionMessage.append(rule.getErrorMessage(classInfo.name()));
                }
            }

            if (!valid) {
                throw new AnnotationsException(exceptionMessage.toString());
            }
        }
    }

    private void areFieldAnnotationsValid(FieldInfo fieldAnnotation) {
        ObjectAnnotations annotations = fieldAnnotation.getAnnotations();
        Collection<AnnotationInfo> all = annotations.getAll();

        if (all.size() < 2) {
            return;
        }

        AnnotationValidatorHelper helper = new AnnotationValidatorHelper();

        Collection<String> fieldsAnnotationsNames = helper.getAnnotationsFQN(all);

        for (ValidationRule rule : rules) {
            rule.validateFieldsAnnotations(fieldsAnnotationsNames);
        }
    }
}
