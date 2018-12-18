import java.util.Random;
import java.math.BigInteger;
import java.util.Vector;
public class Main {

    public static void main(String[] args) {
       String tekst=new String("C:\\Users\\Michał\\Desktop\\chmielu.jpg");
       byte[] bajt=tekst.getBytes();
       Pliki pliki=new Pliki();

       Podpis podpis=new Podpis(pliki.readFile(bajt));
       podpis.genPodpis();
       System.out.println(podpis.getS1());
       System.out.println(podpis.getS2());
       //System.out.println(podpis.getH());
       //Weryfikacja weryfikacja=new Weryfikacja(podpis.getP(),podpis.getH(), podpis.getG(), podpis.hash(),podpis.getS1(),podpis.getS2());
       if(podpis.zweryfikuj())
       {
           System.out.println("dziala :)");
       }
       else System.out.println("nie dziala :(");

    }
}
