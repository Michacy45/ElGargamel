import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Pliki {

    public byte[] readFile(byte[] pathFile) {
        byte[] evr;
        String error;
        //Path sciezka = Paths.get(pathFile);
        // byte[] trescByte = new byte[ ]
        // FileInputStream inStream

        String data="";
        byte[] byteData;


        try {
            //byte[] tab = Files.readAllBytes(sciezka);
            //evr = new String(tab, "UTF-8");
            //data=new String(Files.readAllBytes(Paths.get(pathFile)));
            byteData = Files.readAllBytes(Paths.get(String.valueOf(pathFile)));
        } catch (FileNotFoundException var5) {
            error = "Błąd";
            evr = error.getBytes();
            return evr;
        } catch (IOException var6) {
            error = "zła ścieżka";
            evr = error.getBytes();
            return evr;
        }

        return byteData;
    }
    public void writeFile(String pathFile, byte[] cipher) throws IOException {

        Path path = Paths.get(pathFile);
        Files.write(path, cipher);
    }
}
