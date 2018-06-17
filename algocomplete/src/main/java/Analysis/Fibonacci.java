package Analysis;

public class Fibonacci {
    // Big O = O(2^n)
    public int fibo1(int n){
        if(n == 1 || n == 2){
            return 1;
        }
        return fibo1(n-1)+fibo1(n-2);
    }
    // Big O = O(n)
    public int fibo2(int n){
        int[] f = new int[n];
        f[0] = 1;
        f[1] = 1;
        for(int i=2; i<n; i++){
            f[i] = f[i-1] + f[i-2];
        }
        return f[n-1];
    }
    // Big O = O(n)
    public int fibo3(int n){
        int f = -1;
        int dad = 1;
        int granddad = 1;
        for(int i=2; i<n; i++){
            f = dad + granddad;
            granddad = dad;
            dad = f;
        }
        return f;
    }

    public static void main(String[] args) {
        Fibonacci fibo = new Fibonacci();
        int number = 10;
        System.out.println("Fibo nr. 1: = "+fibo.fibo1(number));
        System.out.println("Fibo nr. 2: = "+fibo.fibo2(number));
        System.out.println("Fibo nr. 3: = "+fibo.fibo3(number));
    }
}
