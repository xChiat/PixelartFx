package TragaPerras;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.*;


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
    private int nDineroActual;
    private int nApuesta;
    private Stage escenario;

    public EscenaTragaperras(Parent raiz, double ancho, double alto, Stage escenario) {
        super(raiz, ancho, alto);
        this.raiz = (BorderPane) raiz;
        this.escenario = escenario;
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
        nDineroActual = Integer.parseInt(dineroActual.getText());

        try{
            nApuesta = Integer.parseInt(dineroApostado.getText());
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Valor no valido, intente nuevamente");
            btnApostar.setDisable(false);
            return;
        }
        if(nApuesta<=0){
//            JOptionPane.showMessageDialog(null,"El dinero apostado no puede ser menor o igual a 0");
            Ventanas.abrirVentana("El dinero apostado no puede ser menor o igual a 0", escenario);
            btnApostar.setDisable(false);
            return;
        }
        if(nApuesta>nDineroActual){
            JOptionPane.showMessageDialog(null,"El dinero apostado no puede ser mayor que el dinero actual");
            btnApostar.setDisable(false);
            return;
        }

        nDineroActual -= nApuesta;
        dineroActual.setText(String.valueOf(nDineroActual));

        // accionamos la funcion tirar en las tres casillas
        izquierda.tirar(null);
        centro.tirar(null);
        derecha.tirar(btnApostar); // agregamos el btnApostar para rehabilitarlo al finalisar con la tirada
    }
}
