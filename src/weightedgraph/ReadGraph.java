package weightedgraph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadGraph<E extends Graph, T> {
	
	public ReadGraph(E graph, String filename) {
		try {
			String line;
			int vertex, edge, space, point;
			FileReader fr=new FileReader(filename);
			BufferedReader br=new BufferedReader(fr);
			line=br.readLine();
			space=indexOfFirstSpace(line);
			vertex=Integer.parseInt(line.substring(0, space));
			edge=Integer.parseInt(line.substring(space+1));
			if(!(vertex==graph.vertex())) {
				br.close();
				fr.close();
				throw new IllegalArgumentException("File does not match the graph");
			}
			for(int i=0; i<edge; i++) {
				int a,b;
				double weight;
				line=br.readLine();
				space=indexOfFirstSpace(line);
				point=indexOfFirstPoint(line);
				a=Integer.parseInt(line.substring(0, space));
				b=Integer.parseInt(line.substring(space+1, point-1));
				//这里不知道怎么用泛型了
				//这里假定weight是一个浮点型，对于其他的类型需要修改下面一行代码
				weight=Integer.parseInt(line.substring(point+1));
				if(a<0 || a>=vertex || b<0 || b>=vertex) {
					br.close();
					fr.close();
					throw new IllegalArgumentException("Error");
				}
				graph.addEdge(a, b, weight);
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int indexOfFirstSpace(String line) {
		for(int i=0; i<line.length(); i++) {
			if(line.charAt(i)==' ') {
				return i;
			}
		}
		return -1;
	}
	
	public int indexOfFirstPoint(String line) {
		for(int i=0; i<line.length(); i++) {
			if(line.charAt(i)=='.') {
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {

	}

}
