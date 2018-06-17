package Analysis;

public class Squareroot
{
    // instance variables 
    String Not_DONE;
    // constructor
    public Squareroot(double x, int decimals, int selection)
    {
        if(decimals > 10) decimals = 10;
        double precision = 0.1;
        for(int i = 1; i < decimals; i++)
        {
            precision *= 0.1;
        }
        if(selection == 1) System.out.println("Squareroot of " + x + " = " + String.format("%."+decimals+"f",root1(x,precision)));
        if(selection == 2) System.out.println("Squareroot of " + x + " = " + String.format("%."+decimals+"f",root2(x,precision)));
        if(selection == 3) System.out.println("Squareroot of " + x + " = " + String.format("%."+decimals+"f",sqrt(x)));
    }

    public double sqrt(double numb){
        double x = numb/2;
        for(int i = 0;i<10;i++){
            x=(0.5)*(x+numb/x);
        }
        return x;
    }

    // step search
    public double root1(double x, double precision)
    {
        boolean running = true;
        double min = 0.;
        while(running)
        {
            if(min*min >= x)
            {
                running = false;
            }
            else
            {
                min += precision;
            }
        }
        return min;
    }
    
    // binary search
    public double root2(double x, double precision)
    {
        boolean running = true;
        double min = (x<=1) ? 0 : 1;
        double max = (x<=1) ? 1 : x;
        double result = (min + max)/2.;
        while(running)
        {
            if(max - min < precision)
            {
                running = false;
            }
            else
            {
                if(result * result > x)
                {
                    max = result;
                }
                else
                {
                    min = result;
                }
            }
            result = (min + max)/2.;
        }
        return result;
    }
    
    // main
    public static void main(String[] args)
    {
        new Squareroot(10,9,1);
        new Squareroot(10,9,2);
        new Squareroot(10,9,3);
    }
}
