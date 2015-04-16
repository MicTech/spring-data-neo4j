package org.neo4j.ogm.metadata.info.validation;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.metadata.info.AnnotationInfo;
import org.neo4j.ogm.metadata.info.ClassInfo;

import java.util.HashMap;
import java.util.Map;

public class ValidatorResolver {
    private Map<String, Validator> validators = new HashMap<>();

    public ValidatorResolver() {
        validators.put(NodeEntity.CLASS, new NodeValidator());
        validators.put(RelationshipEntity.CLASS, new RelationshipValidator());
    }

    public Validator getValidator(String classTypeName) {
        return validators.get(classTypeName);
    }
}