package TragaPerras;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Ventanas {
    public static void abrirVentana(String mensaje, Stage escenarioPrincipal){
        Stage escenario = new Stage();
        Text texto = new Text(mensaje);
        texto.setFont(new Font("Constantia", 14));
        Button btn = new Button("Cerrar");
        btn.setOnAction(e -> escenario.close());
        BorderPane raiz = new BorderPane();
        raiz.setCenter(texto);
        raiz.setBottom(btn);
        BorderPane.setAlignment(btn, Pos.CENTER);
        BorderPane.setMargin(btn, new Insets(7,0,7,0));

        Scene escena = new Scene(raiz, 380, 100);
        escenario.setTitle("ERROR");
        escenario.initModality(Modality.WINDOW_MODAL);
        escenario.initOwner(escenarioPrincipal);
        escenario.setScene(escena);
        escenario.show();
    }
}
