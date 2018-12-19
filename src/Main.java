import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.math.BigInteger;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("One Time Pad");

        //Ustawianie parametrów Grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(10);
        grid.setVgap(5);

        Label nazwaPlik = new Label("Podaj ścieżkę do pliku do podpisania:");
        GridPane.setConstraints(nazwaPlik, 0, 0);

        TextField tekstPlik = new TextField();
        GridPane.setConstraints(tekstPlik, 1, 0);

        Label nazwaZaszyfr = new Label("Podaj sciezke do pliku do sprawdzenia:");
        GridPane.setConstraints(nazwaZaszyfr, 0, 1);

        TextField tekstZaszyfr = new TextField();
        GridPane.setConstraints(tekstZaszyfr, 1, 1);

        //Przyciski
        Button button_szyfr = new Button("Podpisz");
        GridPane.setConstraints(button_szyfr, 3, 0);

        button_szyfr.setOnAction(e -> {
            String filePath = tekstPlik.getText();
            Pliki pliki=new Pliki();
            Podpis podpis=new Podpis(pliki.readFile(filePath));
            podpis.genPodpis();
            try {
                pliki.podpisDoPliku(Pliki.pathBox("Sciezka do zapisu podpisu"), podpis.getS1(), podpis.getS2());
                pliki.kluczykDoPliku(Pliki.pathBox("Sciezka do zapisu klucza"), podpis.getP(), podpis.getG(), podpis.getH());
            }   catch (IOException el) {
                el.printStackTrace();
            }
        });

        Button button_szyfrplik = new Button("Sprawdz plik");
        GridPane.setConstraints(button_szyfrplik, 3, 1);

        button_szyfrplik.setOnAction(e -> {
            Pliki pliki = new Pliki();
            String filePodpis = tekstZaszyfr.getText();
            Podpis podpis = new Podpis(filePodpis.getBytes());
            String fileKlucz = Pliki.pathBox("Sciezka do odczytu pliku z kluczem");
            BigInteger klucz[] = pliki.odczytaj(fileKlucz,3);
            BigInteger podpisWartosc[] = pliki.odczytaj(filePodpis,2);
            BigInteger p = klucz[0];
            BigInteger g = klucz[1];
            BigInteger h = klucz[2];
            BigInteger s1= podpisWartosc[0];
            BigInteger s2 = podpisWartosc[1];
            BigInteger H = podpis.hash();
            Weryfikacja weryfikacja = new Weryfikacja(p,h,g,H,s1,s2);
            //weryfikacja.zweryfikuj();
            if (weryfikacja.zweryfikuj())Pliki.checkBox("Podpis sie zgadza");
            else Pliki.checkBox("Podpis błędny");
        });

        //Dodawanie do Grid
        grid.getChildren().addAll(nazwaPlik, tekstPlik, nazwaZaszyfr, tekstZaszyfr, button_szyfr, button_szyfrplik);


        Scene scene = new Scene(grid, 500, 90);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
