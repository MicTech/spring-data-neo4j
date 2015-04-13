package org.neo4j.ogm.metadata.info.validation;

import org.neo4j.ogm.metadata.info.AnnotationInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class AnnotationValidatorHelper {
    public Collection<String> getAnnotationsFQN(Collection<AnnotationInfo> fieldsAnnotations) {
        Collection<String> fqns = new ArrayList<>();

        for (AnnotationInfo objectAnnotations : fieldsAnnotations) {
            fqns.add(objectAnnotations.getName());
        }

        return fqns;
    }

    public boolean areAnnotationsContains(Collection<String> annotationNames, String[] combination) {
        boolean containsOne = containsOne(annotationNames, combination);

        if (containsOne) {
            return annotationNames.containsAll(Arrays.asList(combination));
        }

        return false;
    }

    private boolean containsOne(Collection<String> fieldAnnotationsNames, String[] combination) {
        for (String annotation : combination) {
            boolean contains = fieldAnnotationsNames.contains(annotation);

            if (contains) {
                return true;
            }
        }

        return false;
    }
}