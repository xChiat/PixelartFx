package VistaLib;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import Controller.CLib;

public class Cliente extends Scene {
    private BorderPane raiz;
    private Stage escenario;
    private VBox principal, agregar, valor;
    private HBox libros;
    private Button btnAgregar;
    private Label infoLibros ,cantidad, total;
    public Cliente(Parent raiz, double ancho, double alto, Stage escenario) {
        super(raiz, ancho, alto);
        this.raiz = (BorderPane) raiz;
        this.escenario = escenario;

        principal = new VBox(10);
        agregar = new VBox(10);
        valor = new VBox(10);
        libros = new HBox(10);

        btnAgregar = new Button("+");
        btnAgregar.setShape(new Rectangle(15,15));

        montarEscena();
    }

    private void montarEscena() {

    }
}
