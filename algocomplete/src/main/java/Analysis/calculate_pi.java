package Analysis;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class calculate_pi {
    public void getPi(int deci){
        BigDecimal pi = new BigDecimal(4);
        boolean plus = false;
        MathContext mc = new MathContext(deci+1, RoundingMode.DOWN);
        for (int i = 3; i < 1000000; i += 2) {
            if (plus) {
                pi = pi.add(new BigDecimal(4).divide(new BigDecimal(i),mc));
            } else {
                pi = pi.subtract(new BigDecimal(4).divide(new BigDecimal(i), mc));
            }
            plus = !plus;
        }
        System.out.println(pi);
    }

    public static void main(String[] args) {
        calculate_pi uf = new calculate_pi();
        uf.getPi(100);
    }
}
