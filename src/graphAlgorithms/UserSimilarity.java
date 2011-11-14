package graphAlgorithms;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class UserSimilarity implements GraphRepresentation{
	
	public Map<String, Set<String>> createTaglist(String ratings, String tags, 
			double threshold, boolean like){
		Map<String, Set<String>> userTagMap = 
				new LinkedHashMap<String, Set<String>>();
		FileInputStream fstream;
		String strLine = null;
		try {
			fstream = new FileInputStream(ratings);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			//Read File Line By Line
			String vote = null;
			while ((strLine = br.readLine()) != null)  {
				//Print the content on the console
				//System.out.println (" Line = "+ strLine);
				String[] columns = strLine.split("::");
				String userMovie = columns[0] + "::" + columns[1] + "::";
				double rating = Double.parseDouble(columns[2]); 
				
				if(!userTagMap.containsKey(columns[0])){
					if((like == true && rating > threshold) 
							|| (like == false && rating <= threshold)){
						userTagMap.put(columns[0], getTagsforUserMovie(tags, userMovie));
					}
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userTagMap;
	}

	private Set<String> getTagsforUserMovie(String tags, String userMovie){
		Set<String> tagList = new LinkedHashSet<String>();
		FileInputStream fstream;
		String strLine = null;
		try {
			fstream = new FileInputStream(tags);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)  {
				//Print the content on the console
				//System.out.println (" Line = "+ strLine);
				if(strLine.startsWith(userMovie)){
					String[] columns = strLine.split("::");
					if(!tagList.contains(columns[2])){
						tagList.add(columns[2]);
					}
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tagList;
	}
}
