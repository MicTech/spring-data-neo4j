package org.neo4j.ogm.metadata.info.validation;

import org.neo4j.ogm.metadata.AnnotationsException;
import org.neo4j.ogm.metadata.info.AnnotationInfo;
import org.neo4j.ogm.metadata.info.ClassInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class ClassAnnotationValidator implements AnnotationValidator {
    private final Collection<ValidationRule> validationRules = new ArrayList<ValidationRule>() {{
        add(new ForbiddenCombinationRule(new String[]{"org.neo4j.ogm.annotation.Transient", "org.neo4j.ogm.annotation.NodeEntity"},
                                         null,
                                         "Class could not be NodeEntity and Transient at the same time"));
    }};

    public void validate(ClassInfo classInfo) {
        Collection<AnnotationInfo> classesAnnotations = getAnnotationsForClassAndAncestors(classInfo);

        AnnotationValidatorHelper helper = new AnnotationValidatorHelper();

        Collection<String> classesAnnotationsNames = helper.getAnnotationsFQN(classesAnnotations);

        boolean valid = areClassAnnotationsValid(classesAnnotationsNames);

        if (!valid) {
            StringBuilder exceptionMessage = new StringBuilder();

            for(ValidationRule rule : validationRules) {
                exceptionMessage.append(rule.getErrorMessage());
            }

            throw new AnnotationsException(exceptionMessage.toString());
        }
    }

    private boolean areClassAnnotationsValid(Collection<String> classAnnotations) {
        if (classAnnotations.size() < 2) {
            return true;
        }

        boolean result = true;

        for(ValidationRule rule : validationRules) {
            rule.validateClassAnnotations(classAnnotations);

            if(!rule.isValid()) {
                result = false;
            }
        }

        return result;
    }

    private Collection<AnnotationInfo> getAnnotationsForClassAndAncestors(ClassInfo classInfo) {
        Collection<AnnotationInfo> annotationInfos = new ArrayList<>();

        annotationInfos.addAll(classInfo.annotations());

        Collection<AnnotationInfo> superClassesAnnotations = getAnnotationsForAncestors(classInfo.directSuperclass());
        annotationInfos.addAll(superClassesAnnotations);

        return annotationInfos;
    }

    private Collection<AnnotationInfo> getAnnotationsForAncestors(ClassInfo classInfo) {
        Collection<AnnotationInfo> annotationInfos = new ArrayList<>();

        if (classInfo == null) {
            return annotationInfos;
        }

        annotationInfos.addAll(classInfo.annotationsInfo().list());

        if (classInfo.directSuperclass() == null) {
            return annotationInfos;
        } else {
            annotationInfos.addAll(getAnnotationsForAncestors(classInfo.directSuperclass()));
        }

        return annotationInfos;
    }
}