package graphServiceImplementations;

import org.neo4j.graphdb.RelationshipType;

public enum GraphDBSearchRelTypes implements RelationshipType
{
    USER_NAME, MOVIE_TITLE, USER_ID, MOVIE_ID, GENRE_TITLE, TAG_NAME
}
