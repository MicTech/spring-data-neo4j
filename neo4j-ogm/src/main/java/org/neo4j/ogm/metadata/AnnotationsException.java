package org.neo4j.ogm.metadata;

/**
 * Specialised {@link RuntimeException} thrown when annotations are in conflict.
 **/
public class AnnotationsException extends RuntimeException {

    /**
     * Constructs a new {@link AnnotationsException} with the given message.
     *
     * @param message A message describing the reason for this exception
     */
    public AnnotationsException(String message) {
        super(message);
    }
}
