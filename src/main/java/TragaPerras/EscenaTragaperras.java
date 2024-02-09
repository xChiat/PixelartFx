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
    private HBox panelCasillas;
    private Casillas izquierda,centro,derecha;
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

        panelCasillas = new HBox(15);
        izquierda = new Casillas();
        derecha = new Casillas();
        centro = new Casillas();

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
        panelCasillas.setStyle("-fx-background-color: #E6E6FA;");
        panelCasillas.getChildren().addAll(izquierda,centro,derecha);
        panelCasillas.setAlignment(Pos.CENTER);
        raiz.setCenter(panelCasillas);
        // preparar la zona inferior
        btnApostar.setOnAction(e-> ejecutarTiradas()); // agregamos una funcionalidad a btnApostar
        panelInferior.setAlignment(Pos.CENTER);
        panelInferior.setPadding(new Insets(10));
        panelInferior.getChildren().addAll(infoDinero,dineroActual,btnApostar,infoApuesta,dineroApostado);
        raiz.setBottom(panelInferior);
    }

    private void ejecutarTiradas() {
        btnApostar.setDisable(true); // desabilita el btn para que no se precione repetidamente hasta acabar la animación
        // accionamos la funcion tirar en las tres casillas
        izquierda.tirar(null);
        centro.tirar(null);
        derecha.tirar(btnApostar); // agregamos el btnApostar para rehabilitarlo al finalisar con la tirada
    }
}
