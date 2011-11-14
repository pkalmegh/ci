package graphServices;

import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.kernel.EmbeddedGraphDatabase;

public class GraphDBServiceImpl implements GraphDBService {
	private static final String DB_PATH = "neo4j-store";
    private static GraphDatabaseService graphDb;
    
    @Override
    public void startGraphDb(){
    	graphDb = new EmbeddedGraphDatabase( DB_PATH );
    }
     
    @Override
	public void startGraphDb( Map<String, String> settings ){
		graphDb = new EmbeddedGraphDatabase( DB_PATH, settings );
	}
	
	@Override
	public void shutdownGraphDb(){
		System.out.println( "Shutting down database ..." );
		graphDb.shutdown();
		graphDb = null;
    }
	 
	@Override
	public void shutdown(){
	    if ( graphDb != null ){
	        //deleteExampleNodeSpace();
	        shutdownGraphDb();
	    }
	}
	 
	@Override
	public void registerShutdownHook(){
	// Registers a shutdown hook for the Neo4j instance so that it
	// shuts down nicely when the VM exits (even if you "Ctrl-C" the
	// running example before it's completed)
	    Runtime.getRuntime().addShutdownHook( new Thread(){
	        @Override
	        public void run(){
	            shutdown();
	        }
	    });
	}

	@Override
	public Node createNode() {
		return graphDb.createNode();
	}
}