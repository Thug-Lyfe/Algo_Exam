package Sorting;



import java.util.List;
import java.util.Random;

public class quicksort_poisson_gaussian_etc {
    private static Random ran = new Random();
    private static StdRandom Stdran = new StdRandom();




    public Comparable[] halfZeroHalfOne(int length){
        Comparable[] array = new Comparable[length];
        for(int i = 0;i<length;i++){
            // hver anden value er 1 de andre er 0
                array[i] = i%2 == 0? 0:1;
        }
        return array;
    }

    public Comparable[] halfIncArray(int arrayLength) {
        Comparable[] array = new Comparable[arrayLength];
        // kald en recursive funktion til at generere
        halfIncValues(array, 0, 0);
        return array;
    }

    private void halfIncValues(Comparable[] array, int startIndex, int value) {

        if (startIndex == array.length) {
            return;
        }
        // endindex skal være halvdelen af den resterende array længde ie. en array af længden 10
        // først print value 0 for halvdelen, sæt index her som startvalue, og increment value, kør igen indtil startindex == array.length
        int endIndex = startIndex + (array.length - startIndex-1) / 2;
        for(int i = startIndex; i <= endIndex; i++) {
            array[i] = value;
        }
        halfIncValues(array, endIndex+1, value + 1);
    }

    public Comparable[] halfZeroHalfRandom(int arrayLength) {
        Comparable[] array = new Comparable[arrayLength];
        // half zero values
        for(int i = 0; i < arrayLength / 2; i++) {
            array[i] = 0;
        }
        //random numbers for the rest
        for(int i = arrayLength / 2; i < arrayLength; i++) {
            array[i] = ran.nextInt();
        }
        return array;
    }

    public Comparable[] gaussianArray(int arrayLength) {

        Comparable[] array = new Comparable[arrayLength];
        // the gaussian dirtribution is a list of numbers that "clusters" around a number, it can also be called a normal distribution
        // see NormalDistribution.png for ex.
        for(int i = 0; i < arrayLength; i++) {
            array[i] = ran.nextGaussian();
        }
        return array;
    }

    public Comparable[] PoissonArray(int arrayLength) {
        Comparable[] array = new Comparable[arrayLength];
        // is a discrete probability distribution that expresses the probability of a given number of
        // events occurring in a fixed interval of time or space if these events occur with a known
        // constant rate and independently of the time since the last event
        // see poisson_distribution.png for ex.
        for(int i = 0; i < arrayLength; i++) {
            array[i] = Stdran.poisson(2);
        }

        return array;
    }

    public Comparable[] GeometricArray(int arrayLength) {

        Comparable[] array = new Comparable[arrayLength];
        // The geometric distribution is a discrete distribution for n=0, 1, 2, ... having probability density function
        // aka. the next element has half as many entries as the last one. see geometric destribution_800.gif for ex.
        for(int i = 0; i < arrayLength; i++) {
            array[i] = Stdran.geometric(0.3);
        }

        return array;
    }

    public Comparable[] DiscreteArray(int arrayLength) {

        Comparable[] array = new Comparable[arrayLength];
        // A discrete distribution is a function that gives the probabilities of observable (either finite or
        // countably infinite) pre-defined values. Unlike a continuous distribution, which probabilistically
        // describes observable outcomes that can take any value in a continuous interval (uncountably
        // infinite) range, a discrete distribution defines probabilities for distinct potential outcomes that do
        // not cover any continuous interval range. Discrete distributions are frequently used in statistical
        // modeling and computer programming.
        for(int i = 0; i < arrayLength; i++) {
            array[i] = i % 2 == 0? 0 : 1;
        }

        return array;
    }

    public void print(String flavorText,Comparable[] comp){
        System.out.print(flavorText+":   ");
        for(int i = 0;i<comp.length;i++){
            System.out.print(comp[i]+", ");
        }
        System.out.println(" ");
    }

    public void quickSort(Comparable[] array, int low, int high) {

        if (low >= high) {
            return;
        }

        int partition = partition(array, low, high);
        quickSort(array, low, partition - 1);
        quickSort(array, partition + 1, high);
    }

    private int partition(Comparable[] array, int low, int high) {
        Comparable pivot = array[low];

        int i = low;
        int j = high + 1;

        while(true) {
            while (less(array[++i], pivot)) {
                if (i == high) {
                    break;
                }
            }

            while(less(pivot, array[--j])) {
                if (j == low) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }

            exchange(array, i, j);
        }

        //Place pivot in the right place
        exchange(array, low, j);
        return j;
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



    public static void main(String[] args) {
    quicksort_poisson_gaussian_etc quick = new quicksort_poisson_gaussian_etc();

        System.out.println("--------discrete array---------");
        Comparable[] discrete = quick.DiscreteArray(10);
        StdRandom.shuffle(discrete);
        quick.print("discrete array",discrete);
        long startTime = System.nanoTime();
        quick.quickSort(discrete,0,discrete.length-1);
        System.out.println("unsorted to sorted: = "+((double)(System.nanoTime()-startTime)/1000000)+" ms");
        startTime = System.nanoTime();
        quick.quickSort(discrete,0,discrete.length-1);
        System.out.println("sorted to sorted: = "+((double)(System.nanoTime()-startTime)/1000000)+" ms");

        System.out.println("--------gaussian array---------");
        Comparable[] gaussian = quick.gaussianArray(10);
        StdRandom.shuffle(gaussian);
        quick.print("gaussian array",gaussian);
        startTime = System.nanoTime();
        quick.quickSort(gaussian,0,gaussian.length-1);
        System.out.println("unsorted to sorted: = "+((double)(System.nanoTime()-startTime)/1000000)+" ms");
        startTime = System.nanoTime();
        quick.quickSort(gaussian,0,gaussian.length-1);
        System.out.println("sorted to sorted: = "+((double)(System.nanoTime()-startTime)/1000000)+" ms");

        System.out.println("--------geometric array---------");
        Comparable[] geometric = quick.GeometricArray(10);
        StdRandom.shuffle(geometric);
        quick.print("geometric array",geometric);
        startTime = System.nanoTime();
        quick.quickSort(geometric,0,geometric.length-1);
        System.out.println("unsorted to sorted: = "+((double)(System.nanoTime()-startTime)/1000000)+" ms");
        startTime = System.nanoTime();
        quick.quickSort(geometric,0,geometric.length-1);
        System.out.println("sorted to sorted: = "+((double)(System.nanoTime()-startTime)/1000000)+" ms");

        System.out.println("--------half inc array---------");
        Comparable[] halfinc = quick.halfIncArray(10);
        StdRandom.shuffle(halfinc);
        quick.print("half inc array",halfinc);
        startTime = System.nanoTime();
        quick.quickSort(halfinc,0,halfinc.length-1);
        System.out.println("unsorted to sorted: = "+((double)(System.nanoTime()-startTime)/1000000)+" ms");
        startTime = System.nanoTime();
        quick.quickSort(halfinc,0,halfinc.length-1);
        System.out.println("sorted to sorted: = "+((double)(System.nanoTime()-startTime)/1000000)+" ms");

        System.out.println("--------half zero half one array---------");
        Comparable[] halfzerohalfone =  quick.halfZeroHalfOne(10);
        StdRandom.shuffle(halfzerohalfone);
        quick.print("half zero half one array",halfzerohalfone);
        startTime = System.nanoTime();
        quick.quickSort(halfzerohalfone,0,halfzerohalfone.length-1);
        System.out.println("unsorted to sorted: = "+((double)(System.nanoTime()-startTime)/1000000)+" ms");
        startTime = System.nanoTime();
        quick.quickSort(halfzerohalfone,0,halfzerohalfone.length-1);
        System.out.println("sorted to sorted: = "+((double)(System.nanoTime()-startTime)/1000000)+" ms");

        System.out.println("--------half zero half random array---------");
        Comparable[] halfzerohalfrandom = quick.halfZeroHalfRandom(10);
        StdRandom.shuffle(halfzerohalfrandom);
        quick.print("half zero half random array",halfzerohalfrandom);
        startTime = System.nanoTime();
        quick.quickSort(halfzerohalfrandom,0,halfzerohalfrandom.length-1);
        System.out.println("unsorted to sorted: = "+((double)(System.nanoTime()-startTime)/1000000)+" ms");
        startTime = System.nanoTime();
        quick.quickSort(halfzerohalfrandom,0,halfzerohalfrandom.length-1);
        System.out.println("sorted to sorted: = "+((double)(System.nanoTime()-startTime)/1000000)+" ms");

        System.out.println("--------poisson array---------");
        Comparable[] poisson = quick.PoissonArray(10);
        StdRandom.shuffle(poisson);
        quick.print("poisson array",poisson);
        startTime = System.nanoTime();
        quick.quickSort(poisson,0,poisson.length-1);
        System.out.println("unsorted to sorted: = "+((double)(System.nanoTime()-startTime)/1000000)+" ms");
        startTime = System.nanoTime();
        quick.quickSort(poisson,0,poisson.length-1);
        System.out.println("sorted to sorted: = "+((double)(System.nanoTime()-startTime)/1000000)+" ms");

    }
}
