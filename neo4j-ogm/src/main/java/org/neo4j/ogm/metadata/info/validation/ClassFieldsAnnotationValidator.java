package org.neo4j.ogm.metadata.info.validation;

import org.neo4j.ogm.metadata.AnnotationsException;
import org.neo4j.ogm.metadata.info.*;

import java.util.ArrayList;
import java.util.Collection;

public class ClassFieldsAnnotationValidator implements AnnotationValidator {

    private Collection<ValidationRule> rules = new ArrayList<ValidationRule>() {{
        add(new RequiredCombinationRule(new String[]{"org.neo4j.ogm.annotation.RelationshipEntity"},
                                        new String[]{"org.neo4j.ogm.annotation.StartNode", "org.neo4j.ogm.annotation.EndNode"},
                                        "Class with RelationShipEntity must contain Fields with StartNode and EndNode"));
    }};

    public void validate(ClassInfo classInfo) {
        Collection<AnnotationInfo> classAnnotations = classInfo.annotations();
        Collection<AnnotationInfo> fieldsAnnotations = getFieldsAnnotations(classInfo);

        boolean valid = areValid(classAnnotations, fieldsAnnotations);

        if (!valid) {
            StringBuilder exceptionMessage = new StringBuilder();

            for(ValidationRule rule : rules) {
                exceptionMessage.append(rule.getErrorMessage());
            }

            throw new AnnotationsException(exceptionMessage.toString());
        }
    }

    private boolean areValid(Collection<AnnotationInfo> classAnnotations, Collection<AnnotationInfo> fieldsAnnotations) {
        AnnotationValidatorHelper helper = new AnnotationValidatorHelper();

        Collection<String> classAnnotationsNames = helper.getAnnotationsFQN(classAnnotations);
        Collection<String> fieldsAnnotationsNames = helper.getAnnotationsFQN(fieldsAnnotations);

        boolean result = true;

        for(ValidationRule rule : rules) {
            rule.validateClassAnnotations(classAnnotationsNames);
            rule.validateFieldsAnnotations(fieldsAnnotationsNames);

            if(!rule.isValid()) {
                result = false;
            }
        }

        return result;
    }

    private Collection<AnnotationInfo> getFieldsAnnotations(ClassInfo classInfo) {
        Collection<AnnotationInfo> annotationInfos = new ArrayList<>();

        FieldsInfo fieldsInfo = classInfo.fieldsInfo();

        Collection<FieldInfo> fields = fieldsInfo.fields();

        for (FieldInfo fieldInfo : fields) {
            ObjectAnnotations annotations = fieldInfo.getAnnotations();
            annotationInfos.addAll(annotations.getAll());
        }

        return annotationInfos;
    }
}