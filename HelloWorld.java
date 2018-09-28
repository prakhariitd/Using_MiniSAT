import java.io.*;

public class HelloWorld{

     public static void main(String []args){
     	try {
     		FileOutputStream fstreamo = new FileOutputStream("a.txt",false);
	        PrintStream printStream = new PrintStream(fstreamo);

	        printStream.println(true);
	        printStream.println("hello "+(123+1));
	        printStream.println(123.456);
	        
	        printStream.close();
	    }
	    catch (FileNotFoundException e){
            System.out.println("File not Found");
        }
    }
}