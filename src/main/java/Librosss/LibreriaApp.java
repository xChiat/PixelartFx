package Librosss;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LibreriaApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage escenario) throws Exception {
        BorderPane contenedorPrincipal = new BorderPane();

        CompradorView compradorView = new CompradorView(new BorderPane(), 450, 160, escenario);
        VendedorView vendedorView = new VendedorView(new BorderPane(), 450, 300, escenario);

        compradorView.setVendedorView(vendedorView);

        contenedorPrincipal.setTop(compradorView.getRoot());
        contenedorPrincipal.setCenter(vendedorView.getRoot());

        Scene scene = new Scene(contenedorPrincipal, 580, 430);

        escenario.setTitle("Librería App");
        escenario.setScene(scene);
        escenario.show();
    }
}
