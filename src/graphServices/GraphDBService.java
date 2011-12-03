package graphServices;

import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;

public interface GraphDBService {
	public GraphDatabaseService startGraphDb();
	public GraphDatabaseService startGraphDb(Map<String, String> settings);
	public void shutdownGraphDb();
	public void shutdown();
	public void registerShutdownHook();
	public Node createNode();

}
