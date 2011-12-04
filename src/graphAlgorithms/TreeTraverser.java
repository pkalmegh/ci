package graphAlgorithms;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.EmbeddedGraphDatabase;

public class TreeTraverser
{
    private static final String INODE = "inode";
    private static final String NAME = "name";
    private static GraphDatabaseService graphDb;

    public enum FileTreeTypes implements RelationshipType
    {
        CHILD,
        SYMLINK
    }

    public static void main( final String[] args )
    {
        graphDb = new EmbeddedGraphDatabase( "target/neo4j-db" );
        Transaction tx = graphDb.beginTx();
        try
        {
            Node rootNode = graphDb.getReferenceNode();

            // create a path like this:
            // /dir1/dir12/dir123/dir1231

            Node dir1 = createSubdir( rootNode, "dir1", 100001 );
            Node dir12 = createSubdir( dir1, "dir12", 100002 );
            Node dir123 = createSubdir( dir12, "dir123", 100003 );
            Node dir1231 = createSubdir( dir123, "dir1231", 111111111 );

            // create a symlink like this:
            // /dir1/dir12/shortcut
            // which points to dir1231
            createSymlink( dir12, dir1231, "shortcut" );

            tx.success();
        }
        finally
        {
            tx.finish();
        }
        tx = graphDb.beginTx();
        try
        {
            Node rootNode = graphDb.getReferenceNode();

            // traverse down from a path
            String path = "dir1/dir12/dir123/dir1231";
            Node target = getNodeFromPath( rootNode, path );
            System.out.println( target.getProperty( INODE ) );

            // now look for the symlinked path
            path = "dir1/dir12/shortcut";
            target = getNodeFromPath( rootNode, path, true );
            System.out.println( target.getProperty( INODE ) );

            // finally, generate an exception ...
            path = "dir1/dir12/shortcut";
            try
            {
                // ... don't follow symlinks!
                target = getNodeFromPath( rootNode, path );
                System.out.println( target.getProperty( INODE ) );
            }
            catch ( IllegalArgumentException iae )
            {
                System.out.println( iae.getMessage() );
            }
        }
        finally
        {
            tx.finish();
        }
        graphDb.shutdown();
    }

    /**
     * Create a subdirectory.
     * 
     * @param parent parent directory
     * @param name name of the new directory
     * @param inode the file inode
     * @return
     */
    private static Node createSubdir( final Node parent, final String name,
            final long inode )
    {
        Node dirNode = graphDb.createNode();
        dirNode.setProperty( INODE, inode );
        Relationship rel = parent.createRelationshipTo( dirNode,
                FileTreeTypes.CHILD );
        rel.setProperty( NAME, name );
        return dirNode;
    }

    /**
     * Create a symlink.
     * 
     * @param parent parent directory
     * @param target directory the symlink points to
     * @param name name of the symlink
     * @return the symlink itself
     */
    private static Relationship createSymlink( final Node parent,
            final Node target, final String name )
    {
        Relationship rel = parent.createRelationshipTo( target,
                FileTreeTypes.SYMLINK );
        rel.setProperty( NAME, name );
        return rel;
    }

    /**
     * Follow CHILD relationships along the path and return the target node.
     * 
     * @param startNode the root node to start from
     * @param path the pat to the target node
     * @return the target node
     */
    private static Node getNodeFromPath( final Node startNode, final String path )
    {
        Node currentNode = startNode;
        for ( String name : path.split( "/" ) )
        {
            boolean foundName = false;
            for ( Relationship rel : currentNode.getRelationships(
                    FileTreeTypes.CHILD, Direction.OUTGOING ) )
            {
                if ( name.equals( rel.getProperty( NAME, null ) ) )
                {
                    currentNode = rel.getEndNode();
                    foundName = true;
                    break;
                }
            }
            if ( !foundName )
            {
                throw new IllegalArgumentException( "No such path" );
            }
        }
        return currentNode;
    }

    /**
     * Follow CHILD relationships along the path and return the target node.
     * 
     * @param startNode the root node to start from
     * @param path the pat to the target node
     * @param includeSymlinks follows symlinks when true
     * @return the target node
     */
    private static Node getNodeFromPath( final Node startNode,
            final String path, final boolean includeSymlinks )
    {
        Node currentNode = startNode;
        for ( String name : path.split( "/" ) )
        {
            boolean foundName = false;
            Iterable<Relationship> rels;
            if ( includeSymlinks )
            {
                // simply follow any outgoing relationships
                rels = currentNode.getRelationships( Direction.OUTGOING );
            }
            else
            {
                // only follow outgoing CHILD relationships
                rels = currentNode.getRelationships( FileTreeTypes.CHILD,
                        Direction.OUTGOING );
            }
            for ( Relationship rel : rels )
            {
                if ( name.equals( rel.getProperty( NAME, null ) ) )
                {
                    currentNode = rel.getEndNode();
                    foundName = true;
                    break;
                }
            }
            if ( !foundName )
            {
                throw new IllegalArgumentException( "No such path" );
            }
        }
        return currentNode;
    }
}