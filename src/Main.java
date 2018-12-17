import java.util.Random;
import java.math.BigInteger;
import java.util.Vector;
public class Main {

    public static void main(String[] args) {
        BigInteger p=BigInteger.valueOf(19);
        BigInteger r=BigInteger.valueOf(5);
        byte[] tekscik=new byte[20];
        Podpis podpis=new Podpis(tekscik);
        BigInteger dzialaj=podpis.chinol(p.subtract(BigInteger.ONE),r);
        System.out.println(dzialaj.mod(p.subtract(BigInteger.ONE)));

    }
}
