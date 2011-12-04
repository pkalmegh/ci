package graphServiceImplementations;

import graphServices.GraphDBService;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.neo4j.kernel.impl.util.FileUtils;

public class GraphDBServiceImpl implements GraphDBService {
	private static final String DB_PATH = "neo4j-store";
    private static GraphDatabaseService graphDb;
    
    @Override
    public GraphDatabaseService startGraphDb(){
    	clearDb();
    	graphDb = new EmbeddedGraphDatabase( DB_PATH );
    	removeData();
    	registerShutdownHook();
    	return graphDb;
    }
     
    @Override
	public GraphDatabaseService startGraphDb( Map<String, String> settings ){
    	clearDb();
		graphDb = new EmbeddedGraphDatabase( DB_PATH, settings );
		registerShutdownHook();
    	return graphDb;
	}
	
	@Override
	public void shutdownGraphDb(){
		System.out.println( "Shutting down database ..." );
		removeData();
		graphDb.shutdown();
		graphDb = null;
		clearDb();
		
    }
	 
	@Override
	public void shutdown(){
	    if ( graphDb != null ){
	        //deleteExampleNodeSpace();
	        shutdownGraphDb();
	    }
	}
	
	void removeData()
    {
        Transaction tx = graphDb.beginTx();
        try
        {
            Iterable<Node> nodes = graphDb.getAllNodes();
            for (Node node : nodes) {
				if(node.hasRelationship()){
					Iterable<Relationship> rels = node.getRelationships();
					for (Relationship relationship : rels) {
						relationship.delete();
					}
				}
				node.delete();
			}

            tx.success();
        }
        finally
        {
            tx.finish();
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
	
	
    private void clearDb()
    {
        try
        {
            FileUtils.deleteRecursively( new File( DB_PATH ) );
        }
        catch ( IOException e )
        {
            throw new RuntimeException( e );
        }
    }

}