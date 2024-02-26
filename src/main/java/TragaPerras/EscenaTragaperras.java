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
    private static Casillas izquierda,centro,derecha;
    // Atributos zona inferior.
    private HBox panelInferior;
    private static Label infoDinero, dineroActual,infoApuesta;
    private Button btnApostar;
    private TextField dineroApostado;
    private static int nDineroActual;
    private static int nApuesta;
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
//            JOptionPane.showMessageDialog(null,"Valor no valido, intente nuevamente");
            Ventanas.abrirVentana("Valor no valido, intente nuevamente", escenario);
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
//            JOptionPane.showMessageDialog(null,"El dinero apostado no puede ser mayor que el dinero actual");
            Ventanas.abrirVentana("El dinero apostado no puede ser mayor que el dinero actual", escenario);
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
    public static void comprobarTiradas(){
        int nIzq = izquierda.getIdImagen();
        int nCentro = centro.getIdImagen();
        int nDer = derecha.getIdImagen();

        System.out.println(nIzq + " " + nCentro + " " + nDer);

        int nPremio = 0;

        if(nIzq == nCentro && nIzq == nDer) {
            // tres espadas de diamantes
            if(nIzq == 0) nPremio += nApuesta*5;
            // tres creepers
            if(nIzq == 1) nPremio -= nApuesta*3; // te quitan vida
            // tres Manzana de notch
            if(nIzq == 2) nPremio += nApuesta*7; // recuperas vida
            // tres sopas misteriosas
            if(nIzq == 3) {
                int f = (int) Math.round(Math.random() *10);
                switch (f) {
                    case 0:
                        nPremio += nApuesta*4; // resistencia al fuego (concede un escudo contra el fuego)
                        break;
                    case 1:
                        nPremio -= nApuesta*8; // blindness
                        break;
                    case 2:
                        nPremio += nApuesta*2; // saturation recupera 2 de vida
                        break;
                    case 3:
                        nPremio += nApuesta*3; // saturation
                        break;
                    case 4:
                        nPremio += nApuesta*6; // jump boost (permite una tirada gratis)
                        break;
                    case 5:
                        nPremio -= nApuesta*12; // poison (quita 4 de vida)
                        break;
                    case 6:
                        nPremio += nApuesta*8; // regeneration (restaura 3 de vida)
                        break;
                    case 7:
                        nPremio += nApuesta*5; // night vision
                        break;
                    case 8:
                        nPremio += nApuesta*4; // night vision
                        break;
                    case 9:
                        nPremio -= nApuesta*9; // weakness
                        break;
                    case 10:
                        nPremio -= nApuesta*10; // whiter (quita 4 de vida + efecto withered)
                        break;
                }
            }
            // tres netherite upgrade
            if(nIzq == 4) nPremio += nApuesta*2;
            // tres espadas de netherita
            if(nIzq == 5) nPremio += nApuesta*7;
            // tres ojos de ender
            if(nIzq == 6) nPremio += nApuesta*3; // al juntar 11 abre la battalla contra la dragona
            // tres lingotes de netherite
            if(nIzq == 7) nPremio += nApuesta*9;
            // tres pigglins
            if(nIzq == 8) nPremio -= nApuesta*5; // evitable si tiene reserva de oro
            // tres lingotes de oro
            if(nIzq == 9) nPremio += nApuesta*9;
            // tres cristales del end
            if(nIzq == 10) nPremio -= nApuesta*7; // a menos que estes en el combate contra la dragona
            // tres totems
            if(nIzq == 11) nPremio += nApuesta*10;  // una vida extra
            // tres cabesas de wither
            if(nIzq == 12) nPremio -= nApuesta*9; // invoca al wither + efecto withered
            // tres lavas
            if(nIzq == 13) nPremio -= nApuesta*8; // te quemas
            }

        if(nIzq == nDer && nIzq != nCentro &&nIzq!= 12 && nCentro!= 8 && nCentro!= 9) {
            // dos espadas de diamantes
            if(nCentro == 0) nPremio += nApuesta*3;
            // dos creepers
            if(nCentro == 1) nPremio -= nApuesta*2; // te quitan vida
            // dos Manzana de notch
            if(nCentro == 2) nPremio += nApuesta*5; // recuperas vida
            // dos sopas misteriosas
            if(nCentro == 3) {
                int f = (int) Math.round(Math.random() *10);
                switch (f) {
                    case 0:
                        nPremio += nApuesta*2; // resistencia al fuego (concede un escudo contra el fuego)
                        break;
                    case 1:
                        nPremio -= nApuesta*4; // blindness
                        break;
                    case 2:
                        nPremio += nApuesta; // saturation
                        break;
                    case 3:
                        nPremio += nApuesta*2; // saturation recupera 1 de vida
                        break;
                    case 4:
                        nPremio += nApuesta*3; // jump boost (permite una tirada gratis)
                        break;
                    case 5:
                        nPremio -= nApuesta*6; // poison (quita 2 de vida)
                        break;
                    case 6:
                        nPremio += nApuesta*4; // regeneration (restaura 2 de vida)
                        break;
                    case 7:
                        nPremio += nApuesta*3; // night vision
                        break;
                    case 8:
                        nPremio += nApuesta*2; // night vision
                        break;
                    case 9:
                        nPremio -= nApuesta*4; // weakness
                        break;
                    case 10:
                        nPremio -= nApuesta*5; // whiter (quita 2 de vida + efecto withered)
                        break;
                }
            }
            // dos netherite upgrade
            if(nCentro == 4) nPremio += nApuesta;
            // dos espadas de netherita
            if(nCentro == 5) nPremio += nApuesta*5;
            // dos ojos de ender
            if(nCentro == 6) nPremio += nApuesta; // al juntar 11 abre la battalla contra la dragona
            // dos lingotes de netherite
            if(nCentro == 7) nPremio += nApuesta*7;
            // dos cristales del end
            if(nCentro == 10) nPremio -= nApuesta*5; // a menos que estes en el combate contra la dragona
            // dos totems
            if(nCentro == 11) nPremio += nApuesta*8;  // una vida extra
            // dos lavas
            if(nCentro == 13) nPremio -= nApuesta*6; // te quemas y pierdes vida
        }
        if(nCentro == nDer && nCentro!= nIzq && nCentro!= 12 && nCentro!= 8 && nCentro!= 9) {
            // dos espadas de diamantes
            if(nCentro == 0) nPremio += nApuesta*3;
            // dos creepers
            if(nCentro == 1) nPremio -= nApuesta*2; // te quitan vida
            // dos Manzana de notch
            if(nCentro == 2) nPremio += nApuesta*5; // recuperas vida
            // dos sopas misteriosas
            if(nCentro == 3) {
                int f = (int) Math.round(Math.random() *10);
                switch (f) {
                    case 0:
                        nPremio += nApuesta*2; // resistencia al fuego (concede un escudo contra el fuego)
                        break;
                    case 1:
                        nPremio -= nApuesta*4; // blindness
                        break;
                    case 2:
                        nPremio += nApuesta; // saturation
                        break;
                    case 3:
                        nPremio += nApuesta*2; // saturation recupera 1 de vida
                        break;
                    case 4:
                        nPremio += nApuesta*3; // jump boost (permite una tirada gratis)
                        break;
                    case 5:
                        nPremio -= nApuesta*6; // poison (quita 2 de vida)
                        break;
                    case 6:
                        nPremio += nApuesta*4; // regeneration (restaura 2 de vida)
                        break;
                    case 7:
                        nPremio += nApuesta*3; // night vision
                        break;
                    case 8:
                        nPremio += nApuesta*2; // night vision
                        break;
                    case 9:
                        nPremio -= nApuesta*4; // weakness
                        break;
                    case 10:
                        nPremio -= nApuesta*5; // whiter (quita 2 de vida + efecto withered)
                        break;
                }
            }
            // dos netherite upgrade
            if(nCentro == 4) nPremio += nApuesta;
            // dos espadas de netherita
            if(nCentro == 5) nPremio += nApuesta*5;
            // dos ojos de ender
            if(nCentro == 6) nPremio += nApuesta; // al juntar 11 abre la battalla contra la dragona
            // dos lingotes de netherite
            if(nCentro == 7) nPremio += nApuesta*7;
            // dos cristales del end
            if(nCentro == 10) nPremio -= nApuesta*5; // a menos que estes en el combate contra la dragona
            // dos totems
            if(nCentro == 11) nPremio += nApuesta*8;  // una vida extra
            // dos lavas
            if(nCentro == 13) nPremio -= nApuesta*6; // te quemas y pierdes vida
        }
        if(nCentro == nIzq && nCentro != nDer && nCentro != 12 && nCentro!= 8 && nCentro!= 9) {
            // dos espadas de diamantes
            if(nCentro == 0) nPremio += nApuesta*3;
            // dos creepers
            if(nCentro == 1) nPremio -= nApuesta*2; // te quitan vida
            // dos Manzana de notch
            if(nCentro == 2) nPremio += nApuesta*5; // recuperas vida
            // dos sopas misteriosas
            if(nCentro == 3) {
                int f = (int) Math.round(Math.random() *10);
                switch (f) {
                    case 0:
                        nPremio += nApuesta*2; // resistencia al fuego (concede un escudo contra el fuego)
                        break;
                    case 1:
                        nPremio -= nApuesta*4; // blindness
                        break;
                    case 2:
                        nPremio += nApuesta; // saturation
                        break;
                    case 3:
                        nPremio += nApuesta*2; // saturation recupera 1 de vida
                        break;
                    case 4:
                        nPremio += nApuesta*3; // jump boost (permite una tirada gratis)
                        break;
                    case 5:
                        nPremio -= nApuesta*6; // poison (quita 2 de vida)
                        break;
                    case 6:
                        nPremio += nApuesta*4; // regeneration (restaura 2 de vida)
                        break;
                    case 7:
                        nPremio += nApuesta*3; // night vision
                        break;
                    case 8:
                        nPremio += nApuesta*2; // night vision
                        break;
                    case 9:
                        nPremio -= nApuesta*4; // weakness
                        break;
                    case 10:
                        nPremio -= nApuesta*5; // whiter (quita 2 de vida + efecto withered)
                        break;
                }
            }
            // dos netherite upgrade
            if(nCentro == 4) nPremio += nApuesta;
            // dos espadas de netherita
            if(nCentro == 5) nPremio += nApuesta*5;
            // dos ojos de ender
            if(nCentro == 6) nPremio += nApuesta; // al juntar 11 abre la battalla contra la dragona
            // dos lingotes de netherite
            if(nCentro == 7) nPremio += nApuesta*7;
            // dos cristales del end
            if(nCentro == 10) nPremio -= nApuesta*5; // a menos que estes en el combate contra la dragona
            // dos totems
            if(nCentro == 11) nPremio += nApuesta*8;  // una vida extra
            // dos lavas
            if(nCentro == 13) nPremio -= nApuesta*6; // te quemas y pierdes vida
        }
        // Upgrade
        if(nIzq == 4 && nCentro == 0 && nDer == 7) nPremio += nApuesta*8;
        //un pigglin y dos lingotes de oro
        if(nIzq == 8 && nCentro == 9 && nDer == 9
                || nIzq == 9 && nCentro == 8 && nDer == 9
                || nIzq == 9 && nCentro == 9 && nDer == 8)nPremio += nApuesta*7;
        // dos pigglins y un lingote de oro
        if(nIzq == nCentro && nIzq == 8|| nCentro == nDer && nDer == 8
                || nIzq == nDer && nIzq== 8)nPremio -= nApuesta*5;
        // un pigglin y un lingote de oro
        if(nIzq == 8 && nCentro == 9 || nIzq == 9 && nCentro == 8
                || nCentro == 9 && nDer == 8|| nCentro == 8 && nDer == 9
                ||nDer == 9 && nIzq == 8|| nDer ==8 && nIzq== 9)nPremio += nApuesta*5;
        // un bloque de lava
        if(nIzq == 13 || nCentro == 13|| nDer == 13)nPremio -= nApuesta*5;
        dineroActual.setText(String.valueOf(nDineroActual+nPremio));
        }

    }
