import java.util.*;
import java.io.*;
public class Benchmarking {
    LinkedList <Integer> dataset = new LinkedList <Integer>();
    Node tempNode;

    public static void sortIntoList (int newNum){
        //this needs to be able to do things 
    }

    public static void main(String[] args) throws FileNotFoundException{

        // either uses the command line file specified or asks the user what file they would like to use 
        File f;
        if ((args.length == 2)){
            f = new File(args[0]);
        }else{
            Scanner consoleScanner = new Scanner (System.in);
            System.out.println("What is the name of the file you wish to use? Press ENTER after input: ");
            f = new File(consoleScanner.next());
            consoleScanner.close();
        }

        //scanning file for integers and sorts into LinkedList dataset
        Scanner fileScanner  = new Scanner(f);
        while (fileScanner.hasNextInt()){
            sortIntoList(fileScanner.nextInt());
        }
        fileScanner.close();

        //
    }
}
