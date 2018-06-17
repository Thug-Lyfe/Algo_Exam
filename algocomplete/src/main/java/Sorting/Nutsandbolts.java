package Sorting;

public class Nutsandbolts
{
    // se qsort.gif for visuelt ex.
    // hver værdi har kun en tilsvarende værdi i modsat array
    private int[] nuts = {2, 6, 1, 7, 10, 9, 5, 8, 3, 4};
    private int[] bolts = {9, 8, 10, 3, 1, 4, 7, 5, 6, 2};
    private int pivotindex;
    

    public Nutsandbolts()
    {
        System.out.println("Nuts and bolts");
        System.out.println("Nuts :");
        printarray(nuts);
        System.out.println("Bolts :");
        printarray(bolts);
        System.out.println();
        findpairs(nuts, bolts, 0, nuts.length -1);
        System.out.println("Sorted");
        System.out.println("Nuts :");
        printarray(nuts);
        System.out.println("Bolts :");
        printarray(bolts);
    }

    public void findpairs(int nuts[], int bolts[], int firstindex, int lastindex)
    {
        if(firstindex < lastindex)
        {
            // første bolt bliver brugt til at splitte møtriks i 3 bunker (større, mindre og 1 enkelt som har samme størrelse)
            // de resterende bolts kan se opdeles i større og mindre udfra den møtrik som havde samme størrelse
            // dette vælges som vores midtpunkt eller omdrejningspunkt
            // de to dele bliver splittet og sorteret hver for sig
            // se qsort.gif for visuelt ex.
            pivotindex = split(nuts, firstindex, lastindex, bolts[firstindex]);
            split(bolts, firstindex, lastindex, nuts[pivotindex]);
            findpairs(nuts, bolts, firstindex, pivotindex -1);
            findpairs(nuts, bolts, pivotindex + 1, lastindex);
        }
    }

    public int split(int a[], int firstindex, int lastindex, int pivotvalue)
    {
        int location = firstindex;
        int swap;
        for(int i = firstindex; i < lastindex; i++)
        {

            if(a[i] < pivotvalue)
            {
                // hvis vores bolt eller møtrik har en værdi mindre end omdrejningspunktet bytter vi om
                swap = a[i];
                a[i] = a[location];
                a[location] = swap;
                location++;
            }
            else
            {
                if(a[i] == pivotvalue)
                {
                    // hvis vi finder en bolt/møtrik der har samme værdi som omdrejningspunktet,
                    // betyder det at dette er det tilsvarende omdrejningspunkt for den anden array
                    // denne værdi sendes derfor til slutning af loopet da det er den rigtige position
                    // i-- for at køre igen for den nye værdi der er blevet swappet ind.
                    swap = a[i];
                    a[i] = a[lastindex];
                    a[lastindex] = swap;
                    i--;
                }
            }
        }
        swap = a[location];
        a[location] = a[lastindex];
        a[lastindex] = swap;
        return location;
    }

    public void printarray(int[] x)
    {
        for(int i = 0; i < x.length; i++)
        {
            System.out.print(x[i] + " ");
        }
        System.out.println();
       
    }

    public static void main(String[] args){
        new Nutsandbolts();
    }
}
