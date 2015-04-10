package org.neo4j.ogm.domain.incorrect.startendnodes;

import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.domain.incorrect.startendnode.User;

@RelationshipEntity
public class Relationship {
    private User user;
}
