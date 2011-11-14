package graphAlgorithms;

import java.util.Map;
import java.util.Set;

public interface CommunityDetection {
	public Map<String, Set<String>> createCommunityList(String movies, String tags);
}
