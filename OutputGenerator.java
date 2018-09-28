import java.util.*;
import java.io.*;

public class OutputGenerator {

	public static void main(String[] args) {
		try{
			String name = args[0];
			String filename1 = name + ".graph";
			String filename3 = name + ".satoutput";
			String filename2 = name + ".subgraphs";
			FileInputStream fstreami = new FileInputStream(filename3);
			FileInputStream fstreami2 = new FileInputStream(filename1);
			FileOutputStream fstreamo = new FileOutputStream(filename2,false);
            Scanner s = new Scanner(fstreami);
            Scanner s2 = new Scanner(fstreami2); //for original input file
            PrintStream p = new PrintStream (fstreamo);
			int v,g,e;
			v = s2.nextInt();
			e = s2.nextInt();
			g = s2.nextInt();
			int[][] vinsg = new int[g][v]; // g*v variables

			if ((s.next()).equals("UNSAT")) {
				p.println(0);
				return;
			}

			for (int k=0; k<g; k++) {
				for (int i=0; i<v; i++) {
					vinsg[k][i] = s.nextInt();
				}
			}

			ArrayList<Integer> temp = new ArrayList<Integer>();
			String out = "";
			int count;

			for (int k=0; k<g; k++) {
				temp.clear();
				count = 0;	
				out = "";
				for (int i=0; i<v; i++) {
					if (vinsg[k][i]>0) {
						count++;
						temp.add(i+1);
					}
				}
				p.println("#"+(k+1)+" "+count);
				for (int j=0; j<count; j++) {
					out+=temp.get(j)+" ";
				}
				p.println(out.substring(0,out.length()-1));
			}

			p.close();

		}
		catch (FileNotFoundException e){
            System.err.println("File not Found");
        }
	}
}