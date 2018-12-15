import java.util.Random;
import java.math.BigInteger;
import java.util.Vector;

public class Podpis {
    private BigInteger p;
    private BigInteger g;
    private BigInteger a;
    private BigInteger h;
    private BigInteger S1;
    private BigInteger S2;
    private byte[] tekstJawny;

    public Podpis(byte[] tekstJawny){
        this.tekstJawny=tekstJawny;
        Random rnd=new Random();
        BigInteger q;
        BigInteger TWO=BigInteger.valueOf(2);
        do{
            p=BigInteger.probablePrime(1024,rnd);
            q=p.subtract(BigInteger.ONE).divide(TWO);
        }while(q.isProbablePrime(100));

        do{
            g=new BigInteger(1023,rnd);
        }while(!g.modPow(p.subtract(BigInteger.ONE).divide(TWO),p).equals(BigInteger.ONE) && !g.modPow(p.subtract(BigInteger.ONE).divide(q),p).equals(BigInteger.ONE));
        a=new BigInteger(1023,rnd);
    }

    public BigInteger genPodpis(){
        //H=Hashysh(tekstJawny) - hashujemy tekst jawny funkcja hashujaca
        BigInteger r;
        Random rnd=new Random();
        do{
            r=new BigInteger(1023,rnd);
        }while (r.gcd(p.subtract(BigInteger.ONE)).equals(BigInteger.ONE));
        S1=g.modPow(r,p);
        BigInteger rPrim=BigInteger.ONE.divide(r).mod(p.subtract(BigInteger.ONE));
        //


    }

}
