import java.util.*;
import java.io.*;
import java.lang.Math; 

public class Benchmarking {
    public static LinkedList <Integer> dataset = new LinkedList <Integer>();
    public static int min;
    public static int max;
    public static int median;

    //finds the correct location for the new value
    public static int locateSortedSpot (int middleIndex, int newNum){
        int startingIndex;

        if(newNum >= dataset.get(middleIndex)){
            startingIndex = middleIndex;
        }
        else{
            startingIndex = 0;
        }

        for (int i = startingIndex; i < dataset.size() - 1; i++){
            if (newNum <= dataset.get(i)){
                return i;
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
            int middleIndex = (int) Math.ceil(dataset.size()/2); //uses ceiling function in java Math in case there is an uneven size
            int newIndex = locateSortedSpot(middleIndex, newNum);
            dataset.add(newIndex, newNum);
        }
    }

    public static void reportStats(){
        //finds minimum value in dataset 
        double start_min = System.nanoTime();
        int min = dataset.getFirst();
        System.out.println("Min: " + min);
        double end_min = System.nanoTime();
        double time_min = (end_min - start_min)/1000000000.0;
        System.out.println("time_min: " + time_min + " seconds");

        //finds maximum value in dataset 
        double start_max = System.nanoTime();
        int max = dataset.getLast();
        System.out.println("Max: " + max);
        double end_max = System.nanoTime();
        double time_max = (end_max - start_max)/1000000000.0;
        System.out.println("time_max: " + time_max + " seconds");

        //finds median value in dataset
        double start_median = System.nanoTime();
        int median = dataset.get((int) Math.ceil(dataset.size()/2));
        System.out.println("Median: " + median);
        double end_median = System.nanoTime();
        double time_median = (end_median - start_median)/1000000000.0;
        System.out.println("time_median: " + time_median + " seconds");
    }

    public static void main(String[] args) throws FileNotFoundException{

        // either uses the command line file specified or asks the user what file they would like to use 
        File f;
        if ((args.length == 1)){
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

        double start = System.nanoTime();
        while (fileScanner.hasNextInt()){
            instertIntoList(fileScanner.nextInt());
            //System.out.println(dataset.toString());
        }
        fileScanner.close();
        double end = System.nanoTime();
        double time_insert = (end - start)/1000000000.0;

        //reports on time taken to insert numbers 
        //reports min,time_min,max,time_max,median,time_median
        System.out.println("time_insert: " + time_insert + " seconds");
        reportStats();

    }
}
