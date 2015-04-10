package org.neo4j.ogm.domain.incorrect.endnode;

import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity
public class Relationship {
    @StartNode
    private User user;
}
