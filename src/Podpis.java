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

    public Podpis(byte[] tekstJawny) {
        this.tekstJawny = tekstJawny;
        Random rnd = new Random();
        BigInteger q;
        BigInteger TWO = BigInteger.valueOf(2);
        do {
            p = BigInteger.probablePrime(1024, rnd);
            q = p.subtract(BigInteger.ONE).divide(TWO);
        } while (q.isProbablePrime(100));

        do {
            g = new BigInteger(1023, rnd);
        }
        while (!g.modPow(p.subtract(BigInteger.ONE).divide(TWO), p).equals(BigInteger.ONE) && !g.modPow(p.subtract(BigInteger.ONE).divide(q), p).equals(BigInteger.ONE));
        a = new BigInteger(1023, rnd);
    }

    public void genPodpis(){
        BigInteger H=Hashysh(tekstJawny); //- hashujemy tekst jawny funkcja hashujaca
        BigInteger r;
        Random rnd=new Random();
        do{
            r=new BigInteger(1023,rnd);
        }while (r.gcd(p.subtract(BigInteger.ONE)).equals(BigInteger.ONE));
        S1=g.modPow(r,p);
        BigInteger rPrim=chinol(p.subtract(BigInteger.ONE),r).mod(p.subtract(BigInteger.ONE));
        S2=(H.subtract(a.multiply(S1))).multiply(rPrim).mod(p.subtract(BigInteger.ONE));
    }
    BigInteger getS1()
    {
        return S1;
    }
    BigInteger getS2()
    {
        return S2;
    }


    public BigInteger chinol(BigInteger pMin1, BigInteger r) {

        BigInteger d = new BigInteger(String.valueOf(pMin1));
        BigInteger e = new BigInteger(String.valueOf(r));
        BigInteger u, v;

        /*if(d.compareTo(e) == -1)
        {
            BigInteger pom;
            pom = d;
            d = e;
            e = pom;
        }*/

        Vector<BigInteger> P = new Vector<>();
        Vector<BigInteger> Q = new Vector<>();
        Vector<BigInteger> q = new Vector<>();

        P.addElement(BigInteger.valueOf(0));
        P.addElement(BigInteger.valueOf(1));
        Q.addElement(BigInteger.valueOf(1));
        Q.addElement(BigInteger.valueOf(0));

        while (e.compareTo(BigInteger.valueOf(0)) != 0) {
            q.addElement(d.divide(e));

            BigInteger pom = new BigInteger(String.valueOf(d));
            d = e;
            e = pom.mod(e);
        }

        for (int i = 0; i < q.size() - 1; i++) {
            P.addElement(P.elementAt(i + 1).multiply(q.elementAt(i)).add(P.elementAt(i)));
            Q.addElement(Q.elementAt(i + 1).multiply(q.elementAt(i)).add(Q.elementAt(i)));
        }

        u = Q.lastElement();
        v = P.lastElement();

        if (q.size() % 2 == 0) {
            u = u.multiply(BigInteger.valueOf(-1));
            v = v.multiply(BigInteger.valueOf(-1));
        }

        while(v.compareTo(BigInteger.valueOf(0))==-1 || v.compareTo(BigInteger.valueOf(0))==0)
        {
            v=v.add(pMin1);
        }

        BigInteger[] result = new BigInteger[2];
        //result = p.multiply(u).multiply(b).subtract(this.q.multiply(v).multiply(a)).mod(n);
        /*result[0]=u;
        result[1]=v;*/
        return v;

    }

}
