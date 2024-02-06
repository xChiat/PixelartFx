package ConversorMorse;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class EscenaConversor extends Scene {
    private VBox principal;
    private Label conversor;
    private HBox panel;
    private Button btnConvertir;
    private TextField texto;

    public EscenaConversor(Parent contenedor, double ancho, double alto) {
        super(contenedor, ancho, alto);
        principal = (VBox) contenedor;
        conversor = new Label("Conversor morse/texto");
        conversor.setAlignment(Pos.TOP_CENTER);
        panel = new HBox(10);
        btnConvertir = new HBox(4);
        texto = new TextField("Escriba El texto que desea convertir");

        montarEscena();
    }

    private void montarEscena() {
        panel.getChildren().addAll(btnConvertir,texto);
        btnConvertir.setOnAction(e->convertirTexto());
    }

    private void convertirTexto() {
        HashMap<String, String> txtToMorse = new HashMap<String, String>();
        txtToMorse.put("a",".-");
        txtToMorse.put("b","-...");
        txtToMorse.put("c","-.-.");
        txtToMorse.put("d","-..");
        txtToMorse.put("e",".");
        txtToMorse.put("f","..-.");
    }
}
