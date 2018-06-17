package sorting_and_searching;

import java.util.Random;

public class heap_sort {


    public void constructHeap(Comparable[] array) {
        for(int i = array.length / 2; i >= 1; i--) {
            put(array, i, array.length - 1);
        }
    }

    public void sortdown(Comparable[] array) {
        int endIndex = array.length - 1;

        for(int i = 1; i < array.length; i++) {
            exchange(array, 1, endIndex);
            endIndex--;
            put(array, 1, endIndex);
        }
    }

    public void put(Comparable[] array, int index, int endIndex) {
        while (index * 2 <= endIndex) {
            int biggestChildIndex = index * 2;

            if (index * 2 + 1 <= endIndex
                    && more(array[index * 2 + 1], array[index * 2])) {
                biggestChildIndex = index * 2 + 1;
            }

            if (less(array[index], array[biggestChildIndex])) {
                exchange(array, index, biggestChildIndex);
            } else {
                break;
            }

            index = biggestChildIndex;
        }
    }
    private void exchange(Comparable[] array, int position1, int position2) {
        Comparable temp = array[position1];
        array[position1] = array[position2];
        array[position2] = temp;
    }

    private boolean less(Comparable value1, Comparable value2) {
        if (value1.compareTo(value2) < 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean more(Comparable value1, Comparable value2) {
        if (value1.compareTo(value2) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        //brug jeap_sort_results_timeInMs.png for at se resultater da 100.000.000 heap, tager omkring 15min at kører
        //grunden er højstsandsynligt at det ikke er compilet
        //bruger 10^8 istedet for 10^9 for at sparer tid.
        Random ran = new Random();
        heap_sort heap = new heap_sort();
        int[] sizes = {1000,1000000,100000000};
        Comparable[] small  = new Comparable[sizes[0]];
        Comparable[] medium = new Comparable[sizes[1]];
        Comparable[] large  = new Comparable[sizes[2]];
        for(int i = 0;i<3;i++){
            for(int k = 0;k<sizes[i];k++){
                if(i == 2){
                    large[k] = ran.nextInt();
                }
                else if(i == 1){
                    medium[k] = ran.nextInt();
                }
                else{
                    small[k] = ran.nextInt();
                }
            }
        }
        System.out.printf("%10s %15s %10s %10s\n","size", "PercTime","sorttime","conctime");
        /// small
        long startTimeConc = System.nanoTime();
        heap.constructHeap(small);
        double concTime =(System.nanoTime()-startTimeConc);

        long startTimeSort = System.nanoTime();
        heap.sortdown(small);
        double sortTime =(System.nanoTime()-startTimeSort);

        double percTime = concTime/(concTime+sortTime);
        System.out.printf("%10d %15f %10f %10f\n", sizes[0], percTime, sortTime/1000000,concTime/1000000);

        /// medium
        startTimeConc = System.nanoTime();
        heap.constructHeap(medium);
        concTime =(System.nanoTime()-startTimeConc);

        startTimeSort = System.nanoTime();
        heap.sortdown(medium);
        sortTime =(System.nanoTime()-startTimeSort);

        percTime = concTime/(concTime+sortTime);
        System.out.printf("%10d %15f %10f %10f\n", sizes[1], percTime, sortTime/1000000,concTime/1000000);

        /// large
        startTimeConc = System.nanoTime();
        heap.constructHeap(large);
        concTime =(System.nanoTime()-startTimeConc);

        startTimeSort = System.nanoTime();
        heap.sortdown(large);
        sortTime =(System.nanoTime()-startTimeSort);

        percTime = concTime/(concTime+sortTime);
        System.out.printf("%10d %15f %10f %10f\n", sizes[2], percTime, sortTime/1000000,concTime/1000000);
    }
}
