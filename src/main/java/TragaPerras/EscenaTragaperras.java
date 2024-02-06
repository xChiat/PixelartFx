package TragaPerras;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class EscenaTragaperras extends Scene {
    private BorderPane raiz;
    //atributos de la barra superior.
    private MenuBar barra;
    private Menu mJuego;
    private MenuItem iSalir;

    // Atributos Zona Central.
    // Atributos zona inferior.
    private HBox panelInferior;
    private Label infoDinero, dineroActual,infoApuesta;
    private Button btnApostar;
    private TextField dineroApostado;

    public EscenaTragaperras(Parent raiz, double ancho, double alto) {
        super(raiz, ancho, alto);
        this.raiz = (BorderPane) raiz;
        barra = new MenuBar();
        mJuego = new Menu("Juego");
        iSalir = new MenuItem("Salir");

        panelInferior = new HBox(10);
        infoDinero = new Label("Dinero actual: ");
        dineroActual = new Label("50");
        infoApuesta = new Label("Cantidad apostada: ");
        btnApostar = new Button("Tirar");
        dineroApostado = new TextField("0");
        dineroApostado.setMinSize(60,25);
        dineroApostado.setMaxSize(60,25);

        montarEscena();

    }

    private void montarEscena() {
        // preparamos la barra
        iSalir.setOnAction(e->System.exit(0));
        mJuego.getItems().add(iSalir);
        barra.getMenus().add(mJuego);
        raiz.setTop(barra);
        // Aqui devemos crear la zona central

        // preparar la zona inferior
        panelInferior.setAlignment(Pos.CENTER);
        panelInferior.setPadding(new Insets(10));
        panelInferior.getChildren().addAll(infoDinero,dineroActual,btnApostar,infoApuesta,dineroApostado);
        raiz.setBottom(panelInferior);
    }
}
