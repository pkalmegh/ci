package graphServices;

import org.neo4j.graphdb.Node;

public interface IndexService{
    void index( Node node, String key, Object value );
    Node getSingleNode( String key, Object value );
    Iterable<Node> getNodes( String key, Object value );
    void removeIndex( Node node, String key, Object value );
    //void setIsolation( Isolation level );
    void shutdown();
}