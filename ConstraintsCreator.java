import java.util.*;
import java.io.*;

public class ConstraintsCreator {

	public static void main(String[] args) {
		try {
			String name = args[0];
			String filename1 = name + ".graph";
			String filename2 = name + ".satinput";
			FileInputStream fstreami = new FileInputStream(filename1);
			FileOutputStream fstreamo = new FileOutputStream(filename2,false);
            Scanner s = new Scanner(fstreami);
            PrintStream p = new PrintStream (fstreamo);
			int v,g,e;
			v = s.nextInt();
			e = s.nextInt();
			g = s.nextInt();
			int[][] edges = new int[v][v]; // v*v variables
			int count = 1;
			for (int i=0; i<v; i++) {
				for (int j=0; j<v; j++) {
					if (j>i) {
						edges[i][j] = v*g+count;
						count++;
					}
					else {
						edges[i][j] = 0;
					}
				}
			}

			int a,b;
			while (s.hasNextInt()) {
				a = s.nextInt();
				b = s.nextInt();
				if (a>b) {
					edges[a-1][b-1] = 1;
				}
				else {
					edges[b-1][a-1] = 1;
				}
			}

			int num_claus = 0;
			num_claus = 3*g*count + count + g; // + ek aur cheez

			p.println("p cnf "+(count+v*g)+" "+num_claus);
			// basic constraints
			for (int k=0; k<g; k++) {
				for (int i=0; i<v; i++) {
					for (int j=i+1; j<v; j++) {
						// add clause
						p.println(((i+1)*(k+1)*-1)+" "+((j+1)*(k+1)*-1)+" "+edges[i][j]+" 0");
						p.println(((i+1)*(k+1))+" "+((j+1)*(k+1)*-1)+" "+(edges[i][j]*-1)+" 0");
						p.println(((i+1)*(k+1)*-1)+" "+((j+1)*(k+1))+" "+(edges[i][j]*-1)+" 0");
					}
				}
			}

			//non empty subgraph
			String c="";
			for (int k=1; k<=g; k++) {
				c="";
				for (int i=1; i<=v; i++) {
					c += (i*k) + " ";
		 		}
		 		c+="0";
		 		p.println(c);
			}

			// subsidiary consntraint

			//edges present in graph
			for (int i=0; i<v; i++) {
				for (int j=i+1; j<v; j++) {
					if (edges[j][i]==1) {
						p.println(edges[i][j]+" 0");;
					}
					else {
						p.println((edges[i][j]*-1)+" 0");
					}
				}
			}
			p.close();
		}
		catch (FileNotFoundException e){
            System.err.println("File not Found");
        }
	}
}