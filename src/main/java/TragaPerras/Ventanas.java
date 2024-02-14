package TragaPerras;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Ventanas {
    public static void abrirVentana(String mensaje, Stage escenarioPrincipal){
        Stage escenario = new Stage();
        Text texto = new Text(mensaje);
        Button btn = new Button("Cerrar");
        BorderPane raiz = new BorderPane();
        raiz.setCenter(texto);
        raiz.setBottom(btn);

        Scene escena = new Scene(raiz, 250, 100);
        escenario.setTitle("ERROR");
        escenario.initModality(Modality.WINDOW_MODAL);
        escenario.initOwner(escenarioPrincipal);
        escenario.setScene(escena);
        escenario.show();
    }
}
