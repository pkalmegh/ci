package graphServices;

import java.util.Map;

import org.neo4j.graphdb.Node;

public interface GraphDBService {
	public void startGraphDb();
	public void startGraphDb(Map<String, String> settings);
	public void shutdownGraphDb();
	public void shutdown();
	public void registerShutdownHook();
	public Node createNode();

}
