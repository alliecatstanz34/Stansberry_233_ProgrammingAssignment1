import java.util.*;
import java.io.*;
public class Benchmarking {
    public static LinkedList <Integer> dataset = new LinkedList <Integer>();


    //finds the middle of the linked list whether size is divisible by 2 or not 
    //then determines if the newNum is greater than or less than the middle value
    //returns the middleindex or zero 
    public static int findStartingIndex(int newNum) {
        if ((dataset.size())%2 == 0){
            if (dataset.get((dataset.size() / 2) - 1) <= newNum){
                return (dataset.size() / 2) - 1;
            }
            return 0;
        }
        else{
            if (dataset.get(((dataset.size() - 1) / 2) + 1) <= newNum){
                return ((dataset.size() - 1) / 2) + 1;
            }
            return 0;
        }
    }

    //finds the correct location for the new value
    public static int locateSortedSpot (int startingIndex, int newNum){
        for (int i = startingIndex; i < dataset.size() - 1; i++){
            if (newNum <= dataset.get(i)){
                return i-1;
            } 
        }
        return dataset.size();
    }

    //if size is zero, new node is added, if size is 1, the newNum is compared
    //directly to the existing node data, and if the size is more than one the
    //middle index is located and then the appropriate location for the new number
    //is found 
    public static void instertIntoList (int newNum){
        if (dataset.size() == 0){
            dataset.add(0, newNum);
        }
        else if(dataset.size() == 1){
            if(newNum >= dataset.get(0)){
                dataset.add(1, newNum);
            }else{
                dataset.add(0,newNum);
            }
        }
        else{
            int middleIndex = findStartingIndex(newNum);
            int newIndex = locateSortedSpot(middleIndex, newNum);
            dataset.add(newIndex, newNum);
        }
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
        //includes Systme.nanoTime() for algorithm timing 
        Scanner fileScanner  = new Scanner(f);

        long start = System.nanoTime();
        while (fileScanner.hasNextInt()){
            instertIntoList(fileScanner.nextInt());
        }
        fileScanner.close();
        long end = System.nanoTime();

        long totalTime = (end - start);

        //testing 
        System.out.println(dataset.toString());
        System.out.println(totalTime);
    }
}
