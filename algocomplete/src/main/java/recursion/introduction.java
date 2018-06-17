package recursion;

public class introduction {
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

    public double sqrt(int numb){
        double x = numb/2;
        for(int i = 0;i<10;i++){
            x=(0.5)*(x+numb/x);
        }
        return x;
    }
    public int mystery(int a, int b){
        if (b == 0) return 0;
        if (b % 2 == 0) return mystery(a+a, b/2);
        return mystery(a+a, b/2) + a;
    }
    public int mystery2(int a, int b){
        if (b == 0) return 1;
        if (b % 2 == 0) return mystery2(a*a, b/2);
        return mystery2(a*a, b/2) * a;
    }

    public int gcd(int p, int q){
        System.out.println("dcd step: p: "+p+", q: "+q);
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }

    public static void main(String[] args) {
        introduction intro = new introduction();
        int number = 10;
        long startTime = System.nanoTime();
        System.out.println("Fibo nr. 1: = "+intro.fibo1(number)+", exec time: "+((double)(System.nanoTime()-startTime)/1000000)+" ms");
        startTime = System.nanoTime();
        System.out.println("Fibo nr. 1: = "+intro.fibo2(number)+", exec time: "+((double)(System.nanoTime()-startTime)/1000000)+" ms");
        int number2 = 1000;
        System.out.println("Math.sqrt(numb): "+Math.sqrt(number2)+", intro.sqrt(numb): "+intro.sqrt(number2));
        System.out.println("task8: "+(1+intro.sqrt(5))/2);
        System.out.println("task9: "+(1+2*Math.sin(Math.PI/10)));
        System.out.println("goldR: 1.61803398875");
        //golden ration relationship :: a+b/a = a/b

        System.out.println("mystery1: "+intro.mystery(2,25));
        System.out.println("mystery(2,25) -> m(2+2,12)+2 -> m(4+4,6)+2 -> m(8+8,3)+2 -> m(16+16,1)+2+16 -> m(32+32,0)+2+16+32 -> 2*25 = 50");
        System.out.println("mystery1: "+intro.mystery(3,11));
        System.out.println("mystery(3,11) -> m(3+3,5)+3 -> m(6+6,2)+9 -> m(12+12,1)+9 -> 24+9 = 33 ");

        System.out.println("mystery2: "+intro.mystery2(2,25));
        System.out.println("mystery2(2,25) -> m2(2^2,12)*2 -> m2(2^4,6)*2 -> m2(2^8,3)*2 -> m2(2^16,1)*2*2^8 -> m2(2^32,0)*2*2^8*2^16 -> 2^25");
        System.out.println("mystery2: "+intro.mystery2(3,11));
        System.out.println("mystery2(3,11) -> m2(3^2,5)*3 -> m2(3^4,2)*3^3 -> m2(3^8,1)*3^3 -> 3^8*3^3 = 3^11");

        System.out.println(intro.gcd( 1111111,1234567));

    }
}
