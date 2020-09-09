import java.util.*;
import java.io.*;
public class Benchmarking {
    LinkedList dataset = new LinkedList();

    public static void main(String[] args) throws FileNotFoundException{
        File f;
        if ((args.length == 2)){
            f = new File(args[0]);
        }else{
            Scanner consoleScanner = new Scanner (System.in);
            System.out.println("What is the name of the file you wish to use? Press ENTER after input: ");
            f = new File(consoleScanner.next());
            consoleScanner.close();
        }
        Scanner fileScanner  = new Scanner(f);

        fileScanner.close();
    }
}
