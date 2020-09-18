import java.util.*;
import java.io.*;
import java.lang.Math; 

public class BenchmarkingNotWorking {
    public static LinkedList <Integer> dataset = new LinkedList <Integer>();
    public static int min;
    public static int max;
    public static int median;

    //if size is zero, new node is added, if size is 1, the newNum is compared
    //directly to the existing node data, and if the size is more than one the
    //middle index is located and then the appropriate location for the new number
    //is found 
    public static void instertIntoList (int newNum) {
        //System.out.println("inside insert");
        if(dataset.size()==0){
            dataset.add(0, newNum);
        } else if(dataset.size() < 11 && dataset.size() != 1){
            //System.out.println("less than 11");
            ListIterator<Integer> listIt = dataset.listIterator();
            int index = 0;
            //System.out.println(""+listIt.next());
            while(listIt.hasNext()){
                //System.out.println("inner while loop");
                if (newNum <= listIt.nextIndex()){
                    dataset.add(index, newNum);
                    //System.out.println(dataset.get(index));
                }
                listIt.next();
                index ++;
            }
        } else {
        int front = 0;
        int end = dataset.size()-1;
        insertIntoListRec(newNum, front, end);
        }    
    }

    public static void insertIntoListRec(int newNum, int front, int end){
        int middle = (int) Math.ceil((end - front)/2);
        if(newNum < dataset.get(middle)){
            insertIntoListRec(newNum,front,middle);
        } else if (newNum > dataset.get(middle)){
            insertIntoListRec(newNum, middle, end);
        } else if (end-front <= 11){
            ListIterator<Integer> listIt = dataset.listIterator();
            int index = front;
            while(index < end){
                if (newNum <= listIt.next()){
                    dataset.add(index, newNum);
                }
                index ++;
            }
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
