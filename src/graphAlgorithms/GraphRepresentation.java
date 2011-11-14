package graphAlgorithms;

import java.util.Map;
import java.util.Set;

public interface GraphRepresentation {
	public Map<String, Set<String>> createTaglist(String ratings, String tags, 
			double threshold, boolean like);

}
