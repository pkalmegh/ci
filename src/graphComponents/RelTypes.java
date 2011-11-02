package graphComponents;

import org.neo4j.graphdb.RelationshipType;
  
public enum RelTypes implements RelationshipType{
      RATED,
      BELONGS_TO,
      SIMILAR_TO,
      CREATES_TAG,
      IS_TAGGED
}