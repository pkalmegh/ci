package graphServiceImplementations;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;

import graphServices.GraphDBIndexService;

public class GraphDBIndexServiceImpl implements GraphDBIndexService{
	
	Index<Node> index; 
	
	public GraphDBIndexServiceImpl(GraphDatabaseService graphDB) {
		this.index = graphDB.index().forNodes("movielensindex");
	}

	@Override
	public void addNode(Node node, String key, Object value) {
		index.add(node, key, value);
	}

	@Override
    public Node getSingleNode(String key, Object value){
        IndexHits<Node> hits = index.get( key, value );
        if(hits != null){
            for ( Node node : hits ){
                return node;
            }
        }
        return null;
    }

}
