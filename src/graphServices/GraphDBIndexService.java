package graphServices;

import org.neo4j.graphdb.Node;

public interface GraphDBIndexService {
	public void addNode(Node node, String key, Object value);
	Node getSingleNode(String key, Object value);
	

}
