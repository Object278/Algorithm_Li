package graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadGraph<E extends Graph> {
	
	public ReadGraph(E graph, String filename) {
		try {
			String line;
			int vertex, edge, space;
			FileReader fr=new FileReader(filename);
			BufferedReader br=new BufferedReader(fr);
			line=br.readLine();
			space=indexOfSpace(line);
			vertex=Integer.parseInt(line.substring(0, space));
			edge=Integer.parseInt(line.substring(space+1));
			if(!(vertex==graph.vertex())) {
				br.close();
				fr.close();
				throw new IllegalArgumentException("File does not match the graph");
			}
			for(int i=0; i<edge; i++) {
				int a,b;
				line=br.readLine();
				space=indexOfSpace(line);
				a=Integer.parseInt(line.substring(0, space));
				b=Integer.parseInt(line.substring(space+1));
				if(a<0 || a>=vertex || b<0 || b>=vertex) {
					br.close();
					fr.close();
					throw new IllegalArgumentException("Error");
				}
				graph.addEdge(a, b);
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int indexOfSpace(String line) {
		for(int i=0; i<line.length(); i++) {
			if(line.charAt(i)==' ') {
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {

	}

}
