package TragaPerras;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    // barra vida
    private HBox barraVida;
    private static BarraDeVida c1,c2,c3,c4,c5,c6,c7,c8,c9,c10;

    // Atributos zona inferior.
    private HBox pnlInf;
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

        pnlInf = new HBox();
        pnlInf.setAlignment(Pos.BOTTOM_CENTER);

        barraVida = new HBox();
        c1 = new BarraDeVida(); c2 = new BarraDeVida();
        c3 = new BarraDeVida(); c4 = new BarraDeVida();
        c5 = new BarraDeVida(); c6 = new BarraDeVida();
        c7 = new BarraDeVida(); c8 = new BarraDeVida();
        c9 = new BarraDeVida(); c10 = new BarraDeVida();

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


        // preparamos la barra de vida
        barraVida.getChildren().addAll(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10);
        barraVida.setPadding(new Insets(15));
        barraVida.setAlignment(Pos.BOTTOM_CENTER);
        pnlInf.getChildren().addAll(barraVida,panelInferior);
        raiz.setBottom(pnlInf);
    }

    private void ejecutarTiradas() {
        btnApostar.setDisable(true); // desabilita el btn para que no se precione repetidamente hasta acabar la animación
        int n1 = c1.getIdHeart();
        if(n1 != 3){
            nDineroActual = Integer.parseInt(dineroActual.getText());
        }else{
            nDineroActual = 0;
            dineroActual.setText(String.valueOf(nDineroActual));
        }

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

        int nPremio = 0;

        if(nIzq == nCentro && nIzq == nDer) {
            // tres espadas de diamantes
            if(nIzq == 0) nPremio += nApuesta*5;

            // tres creepers
            if(nIzq == 1) {
                if((nDineroActual - nApuesta * 4) > 0) {
                    nPremio -= nApuesta * 4;
                    int d = 4;
                    for (int i = 0; i < d; i++) {
                        Damage(d);
                    }
                }else{
                    nDineroActual = 0;
                    dineroActual.setText(String.valueOf(nDineroActual));
                }

            } // te quitan vida

            // tres Manzana de notch
            if(nIzq == 2) {
                nPremio += nApuesta * 7;// recuperas vida
                int d = 4;
                for(int i = 0; i < d; i++) {Curar(d);}
            }
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
                        int a = 2;
                        for(int i = 0; i < a; i++) {Curar(a);}
                        break;
                    case 3:
                        nPremio += nApuesta*3; // saturation
                        int q = 2;
                        for(int i = 0; i < q; i++) {Curar(q);}
                        break;
                    case 4:
                        nPremio += nApuesta*6; // jump boost (permite una tirada gratis)
                        break;
                    case 5:
                        nPremio -= nApuesta*12; // poison (quita 4 de vida)
                        int d = 4;
                        for(int i = 0; i < d; i++) {Damage(d);}
                        break;
                    case 6:
                        nPremio += nApuesta*8; // regeneration (restaura 3 de vida)
                        int r = 2;
                        for(int i = 0; i < r; i++) {Curar(r);}
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
                        int e = 4;
                        for(int i = 0; i < e; i++) {Damage(e);}
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
            if(nIzq == 8){
                if((nDineroActual - nApuesta * 5) > 0) {
                    nPremio -= nApuesta*5; // evitable si tiene reserva de oro
                    int d = 4;
                    for(int i = 0; i < d; i++) {Damage(d);}
                }else{
                    nDineroActual = 0;
                    dineroActual.setText(String.valueOf(nDineroActual));
                }
            }
            // tres lingotes de oro
            if(nIzq == 9) nPremio += nApuesta*9;
            // tres cristales del end
            if(nIzq == 10) {
                if((nDineroActual - nApuesta * 7) != 0) {
                    nPremio -= nApuesta * 7; // a menos que estes en el combate contra la dragona
                    int d = 6;
                    for (int i = 0; i < d; i++) {
                        Damage(d);
                    }
                }else{
                    nDineroActual = 0;
                    dineroActual.setText(String.valueOf(nDineroActual));
                }
            }
            // tres totems
            if(nIzq == 11){
                nPremio += nApuesta*10;  // una vida extra
                int d = 10;
                for(int i = 0; i < d; i++) {Curar(d);}
            }
            // tres cabesas de wither
            if(nIzq == 12) {
                if((nDineroActual - nApuesta * 9) != 0) {
                    nPremio -= nApuesta * 9; // invoca al wither + efecto withered
                    int d = 7;
                    for (int i = 0; i < d; i++) {
                        Damage(d);
                    }
                }else{
                    nDineroActual = 0;
                    dineroActual.setText(String.valueOf(nDineroActual));
                }
            }
            // tres lavas
            if(nIzq == 13) {
                if((nDineroActual - nApuesta * 8) > 0) {
                    nPremio -= nApuesta * 8; // te quemas
                    int d = 5;
                    for (int i = 0; i < d; i++) {
                        Damage(d);
                    }
                }else{
                    nDineroActual = 0;
                    dineroActual.setText(String.valueOf(nDineroActual));
                }
            }
        }

        // DOS

        if(nIzq == nDer && nIzq != nCentro &&nIzq!= 12 && nIzq!= 8 && nIzq!= 9) {
            // dos espadas de diamantes
            if(nIzq == 0) nPremio += nApuesta*3;

            // dos creepers
            if(nIzq == 1) {
                if((nDineroActual - nApuesta * 2) > 0) {
                    nPremio -= nApuesta * 2; // te quitan vida
                    int d = 2;
                    for (int i = 0; i < d; i++) {
                        Damage(d);
                    }
                    if(nCentro == 11){
                        nPremio += nApuesta*4;
                        int e = 4;
                        for(int i = 0; i < e; i++) {Curar(e);}
                    }
                    if(nCentro == 2){
                        nPremio += nApuesta * 4;
                        int r = 2;
                        for(int i = 0; i < r; i++) {Curar(r);}
                    }
                }else{
                    nDineroActual = 0;
                    dineroActual.setText(String.valueOf(nDineroActual));
                }
            }

            // dos Manzana de notch
            if(nIzq == 2){
                nPremio += nApuesta*5; // recuperas vida
                int d = 2;
                for(int i = 0; i < d; i++) {Curar(d);}
            }
            // dos sopas misteriosas
            if(nIzq == 3) {
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
                        int q = 1;
                        for(int i = 0; i < q; i++) {Curar(q);}
                        break;
                    case 3:
                        nPremio += nApuesta*2; // saturation recupera 1 de vida
                        int r = 1;
                        for(int i = 0; i < r; i++) {Curar(r);}
                        break;
                    case 4:
                        nPremio += nApuesta*3; // jump boost (permite una tirada gratis)
                        break;
                    case 5:
                        nPremio -= nApuesta*6; // poison (quita 2 de vida)
                        int d = 2;
                        for(int i = 0; i < d; i++) {Damage(d);}
                        break;
                    case 6:
                        nPremio += nApuesta*4; // regeneration (restaura 2 de vida)
                        int t = 2;
                        for(int i = 0; i < t; i++) {Curar(t);}
                        break;
                    case 7:
                        nPremio += nApuesta*3; // night vision
                        break;
                    case 8:
                        nPremio += nApuesta*2; // night vision
                        break;
                    case 9:
                        nPremio -= nApuesta*4; // weakness
                        int a = 1;
                        for(int i = 0; i < a; i++) {Damage(a);}
                        break;
                    case 10:
                        nPremio -= nApuesta*5; // whiter (quita 2 de vida + efecto withered)
                        int e = 2;
                        for(int i = 0; i < e; i++) {Damage(e);}
                        break;
                }
            }
            // dos netherite upgrade
            if(nIzq == 4) nPremio += nApuesta;
            // dos espadas de netherita
            if(nIzq == 5) nPremio += nApuesta*5;
            // dos ojos de ender
            if(nIzq == 6) nPremio += nApuesta; // al juntar 11 abre la battalla contra la dragona
            // dos lingotes de netherite
            if(nIzq == 7) nPremio += nApuesta*7;
            // dos cristales del end
            if(nIzq == 10) {
                if((nDineroActual - nApuesta * 5) > 0) {
                    nPremio -= nApuesta * 5; // a menos que estes en el combate contra la dragona
                    int d = 3;
                    for (int i = 0; i < d; i++) {
                        Damage(d);
                    }
                }else{
                    nDineroActual = 0;
                    dineroActual.setText(String.valueOf(nDineroActual));
                }
            }
            // dos totems
            if(nIzq == 11){
                nPremio += nApuesta*8;  // una vida extra
                int d = 6;
                for(int i = 0; i < d; i++) {Curar(d);}
            }
            // dos lavas
            if(nIzq == 13) {
                if((nDineroActual - nApuesta * 6) > 0) {
                    nPremio -= nApuesta * 6; // te quemas y pierdes vida
                    int d = 3;
                    for (int i = 0; i < d; i++) {
                        Damage(d);
                    }
                }else{
                    nDineroActual = 0;
                    dineroActual.setText(String.valueOf(nDineroActual));
                }
            }
        }
        if(nCentro == nDer && nCentro!= nIzq && nCentro!= 12 && nCentro!= 8 && nCentro!= 9) {
            // dos espadas de diamantes
            if(nCentro == 0) nPremio += nApuesta*3;
            // dos creepers
            if(nCentro == 1) {
                if((nDineroActual - nApuesta * 2) > 0) {
                    nPremio -= nApuesta * 2; // te quitan vida
                    int d = 2;
                    for (int i = 0; i < d; i++) {
                        Damage(d);
                    }
                }else{
                    nDineroActual = 0;
                    dineroActual.setText(String.valueOf(nDineroActual));
                }
            }
            // dos Manzana de notch
            if(nCentro == 2){
                nPremio += nApuesta*5; // recuperas vida
                int d = 2;
                for(int i = 0; i < d; i++) {Curar(d);}
            }
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
                        int q = 1;
                        for(int i = 0; i < q; i++) {Curar(q);}
                        break;
                    case 3:
                        nPremio += nApuesta*2; // saturation recupera 1 de vida
                        int r = 1;
                        for(int i = 0; i < r; i++) {Curar(r);}
                        break;
                    case 4:
                        nPremio += nApuesta*3; // jump boost (permite una tirada gratis)
                        break;
                    case 5:
                        nPremio -= nApuesta*6; // poison (quita 2 de vida)
                        int d = 2;
                        for(int i = 0; i < d; i++) {Damage(d);}
                        break;
                    case 6:
                        nPremio += nApuesta*4; // regeneration (restaura 2 de vida)
                        int t = 2;
                        for(int i = 0; i < t; i++) {Curar(t);}
                        break;
                    case 7:
                        nPremio += nApuesta*3; // night vision
                        break;
                    case 8:
                        nPremio += nApuesta*2; // night vision
                        break;
                    case 9:
                        nPremio -= nApuesta*4; // weakness
                        int a = 1;
                        for(int i = 0; i < a; i++) {Damage(a);}
                        break;
                    case 10:
                        nPremio -= nApuesta*5; // whiter (quita 2 de vida + efecto withered)
                        int e = 2;
                        for(int i = 0; i < e; i++) {Damage(e);}
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
            if(nCentro == 10) {
                if((nDineroActual - nApuesta * 3) > 0) {
                    nPremio -= nApuesta * 5; // a menos que estes en el combate contra la dragona
                    int d = 3;
                    for (int i = 0; i < d; i++) {
                        Damage(d);
                    }
                }else{
                    nDineroActual = 0;
                    dineroActual.setText(String.valueOf(nDineroActual));
                }
            }
            // dos totems
            if(nCentro == 11) {
                nPremio += nApuesta*8;  // una vida extra
                int d = 6;
                for(int i = 0; i < d; i++) {Curar(d);}
            }
            // dos lavas
            if(nCentro == 13) {
                if((nDineroActual - nApuesta * 6) > 0) {
                    nPremio -= nApuesta * 6; // te quemas y pierdes vida
                    int d = 3;
                    for (int i = 0; i < d; i++) {
                        Damage(d);
                    }
                }else{
                    nDineroActual = 0;
                    dineroActual.setText(String.valueOf(nDineroActual));
                }
            }
        }
        if(nCentro == nIzq && nCentro != nDer && nCentro != 12 && nCentro!= 8 && nCentro!= 9) {
            // dos espadas de diamantes
            if(nCentro == 0) nPremio += nApuesta*3;
            // dos creepers
            if(nCentro == 1) {
                if((nDineroActual - nApuesta * 2) > 0) {
                    nPremio -= nApuesta * 2; // te quitan vida
                    int d = 2;
                    for (int i = 0; i < d; i++) {
                        Damage(d);
                    }
                }else{
                    nDineroActual = 0;
                    dineroActual.setText(String.valueOf(nDineroActual));
                }
            }
            // dos Manzana de notch
            if(nCentro == 2) {
                nPremio += nApuesta*5; // recuperas vida
                int d = 2;
                for(int i = 0; i < d; i++) {Curar(d);}
            }
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
                        int q = 1;
                        for(int i = 0; i < q; i++) {Curar(q);}
                        break;
                    case 3:
                        nPremio += nApuesta*2; // saturation recupera 1 de vida
                        int r = 1;
                        for(int i = 0; i < r; i++) {Curar(r);}
                        break;
                    case 4:
                        nPremio += nApuesta*3; // jump boost (permite una tirada gratis)
                        break;
                    case 5:
                        nPremio -= nApuesta*6; // poison (quita 2 de vida)
                        int d = 2;
                        for(int i = 0; i < d; i++) {Damage(d);}
                        break;
                    case 6:
                        nPremio += nApuesta*4; // regeneration (restaura 2 de vida)
                        int t = 2;
                        for(int i = 0; i < t; i++) {Curar(t);}
                        break;
                    case 7:
                        nPremio += nApuesta*3; // night vision
                        break;
                    case 8:
                        nPremio += nApuesta*2; // night vision
                        break;
                    case 9:
                        nPremio -= nApuesta*4; // weakness
                        int a = 1;
                        for(int i = 0; i < a; i++) {Damage(a);}
                        break;
                    case 10:
                        nPremio -= nApuesta*5; // whiter (quita 2 de vida + efecto withered)
                        int e = 2;
                        for(int i = 0; i < e; i++) {Damage(e);}
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
            if(nCentro == 10) {
                if((nDineroActual - nApuesta * 5) > 0) {
                    nPremio -= nApuesta * 5; // a menos que estes en el combate contra la dragona
                    int d = 3;
                    for (int i = 0; i < d; i++) {
                        Damage(d);
                    }
                }else{
                    nDineroActual = 0;
                    dineroActual.setText(String.valueOf(nDineroActual));
                }
            }
            // dos totems
            if(nCentro == 11){
                nPremio += nApuesta*8;  // una vida extra
                int d = 6;
                for(int i = 0; i < d; i++) {Curar(d);}
            }
            // dos lavas
            if(nCentro == 13) {
                if((nDineroActual - nApuesta * 6) > 0) {
                    nPremio -= nApuesta * 6; // te quemas y pierdes vida
                    int d = 3;
                    for (int i = 0; i < d; i++) {
                        Damage(d);
                    }
                }
            }else{
                nDineroActual = 0;
                dineroActual.setText(String.valueOf(nDineroActual));
            }
        }

        // UNO

        // Upgrade
        if(nIzq == 4 && nCentro == 0 && nDer == 7) nPremio += nApuesta*8;

        //un pigglin y dos lingotes de oro
        if(nIzq == 8 && nCentro == 9 && nDer == 9
                || nIzq == 9 && nCentro == 8 && nDer == 9
                || nIzq == 9 && nCentro == 9 && nDer == 8)nPremio += nApuesta*7;

        // dos pigglins y un lingote de oro
        if(nIzq == nCentro && nIzq == 8|| nCentro == nDer && nDer == 8
                || nIzq == nDer && nIzq== 8) {
            if((nDineroActual - nApuesta * 5) > 0) {
                nPremio -= nApuesta * 5;
                int d = 2;
                for (int i = 0; i < d; i++) {
                    Damage(d);
                }
            }else{
                nDineroActual = 0;
                dineroActual.setText(String.valueOf(nDineroActual));
            }
        }

        // un pigglin y un lingote de oro
        if(nIzq == 8 && nCentro == 9 || nIzq == 9 && nCentro == 8
                || nCentro == 9 && nDer == 8|| nCentro == 8 && nDer == 9
                ||nDer == 9 && nIzq == 8|| nDer ==8 && nIzq== 9)nPremio += nApuesta*5;

        // un bloque de lava
        if(nIzq == 13 && nIzq != nCentro && nIzq != nDer
                || nCentro == 13 && nCentro != nIzq && nCentro != nDer
                || nDer == 13 && nDer != nIzq && nDer != nCentro) {
            if((nDineroActual - nApuesta * 4) > 0) {
                nPremio -= nApuesta * 4;
                int d = 2;
                for (int i = 0; i < d; i++) {
                    Damage(d);
                }
            }else{
                nDineroActual = 0;
                dineroActual.setText(String.valueOf(nDineroActual));
            }
        }

        // un totem
        if(nIzq ==11 && nIzq != nCentro && nIzq != nDer
                || nCentro ==11 && nCentro != nIzq && nCentro != nDer
                || nDer == 11 && nDer != nIzq && nDer != nCentro){
            nPremio += nApuesta*4;
            int d = 4;
            for(int i = 0; i < d; i++) {Curar(d);}
        }

        // una manzana de notch
        if(nIzq == 2 && nIzq != nCentro && nIzq != nDer
                || nCentro == 2 && nCentro != nIzq && nCentro != nDer
                || nDer == 2 && nDer != nIzq && nDer != nCentro){
            nPremio += nApuesta * 4;
            int d = 2;
            for(int i = 0; i < d; i++) {Curar(d);}
        }

        // un piglin
        if(nIzq == 8  && nIzq != nCentro && nIzq != nDer
                || nCentro == 8  && nCentro != nIzq && nCentro != nDer
                || nDer == 8 && nDer != nIzq && nDer != nCentro){
            if((nDineroActual - nApuesta * 3) > 0) {
                nPremio -= nApuesta * 3;
                int d = 3;
                for (int i = 0; i < d; i++) {
                    Damage(d);
                }
            }else{
                nDineroActual = 0;
                dineroActual.setText(String.valueOf(nDineroActual));
            }
        }

        // un creeper
        if(nIzq == 1 && nIzq != nCentro && nIzq != nDer
                || nCentro == 1 && nCentro != nIzq && nCentro != nDer
                || nDer ==1 && nDer != nIzq && nDer != nCentro){
            if((nDineroActual - nApuesta * 2) > 0) {
                nPremio -= nApuesta * 2;
                int d = 2;
                for (int i = 0; i < d; i++) {
                    Damage(d);
                }
            }else{
                nDineroActual = 0;
                dineroActual.setText(String.valueOf(nDineroActual));
            }
        }

        dineroActual.setText(String.valueOf(nDineroActual+nPremio));
        }

    private static void Curar(int d) {
        int n10 = c10.getIdHeart(); int n9 = c9.getIdHeart();
        int n8 = c8.getIdHeart(); int n7 = c7.getIdHeart();
        int n6 = c6.getIdHeart(); int n5 = c5.getIdHeart();
        int n4 = c4.getIdHeart(); int n3 = c3.getIdHeart();
        int n2 = c2.getIdHeart(); int n1 = c1.getIdHeart();

        for(int f = 0; f < d; f++) {
            if(n1 == 2){
                n1--;
                c1.curar(n1);
            }else if(n2 >= 2){
                n2--;
                c2.curar(n2);
            }else if(n3 >= 2){
                n3--;
                c3.curar(n3);
            }else if(n4 >= 2){
                n4--;
                c4.curar(n4);
            }else if(n5 >= 2){
                n5--;
                c5.curar(n5);
            }else if(n6 >= 2){
                n6--;
                c6.curar(n6);
            }else if(n7 >= 2){
                n7--;
                c7.curar(n7);
            }else if(n8 >= 2){
                n8--;
                c8.curar(n8);
            }else if(n9 >= 2){
                n9--;
                c9.curar(n9);
            }else if(n10 >= 2){
                n10--;
                c10.curar(n10);
            }
        }
    }

    public static void Damage(int dmg) {
        int n10 = c10.getIdHeart(); int n9 = c9.getIdHeart();
        int n8 = c8.getIdHeart(); int n7 = c7.getIdHeart();
        int n6 = c6.getIdHeart(); int n5 = c5.getIdHeart();
        int n4 = c4.getIdHeart(); int n3 = c3.getIdHeart();
        int n2 = c2.getIdHeart(); int n1 = c1.getIdHeart();

        for(int f = 0; f < dmg; f++) {

            //System.out.println(n10+" "+n9+" "+n8+" "+n7+" "+n4+" "+n3+" "+n2+" "+n1);

            if (n10 != 3) {
                n10 += 1;
                System.out.println("c10: "+n10);
                c10.quitarVida(n10);
            }else if (n9 != 3) {
                n9 += 1;
                System.out.println("c9: "+n9);
                c9.quitarVida(n9);
            }else if (n8!= 3) {
                n8 += 1;
                System.out.println("c8: "+n8);
                c8.quitarVida(n8);
            }else if (n7!= 3) {
                n7 += 1;
                System.out.println("c7: "+n7);
                c7.quitarVida(n7);
            }else if (n6!= 3) {
                n6 += 1;
                System.out.println("c6: "+n6);
                c6.quitarVida(n6);
            }else if(n5 != 3){
                n5 += 1;
                System.out.println("c5: "+n5);
                c5.quitarVida(n5);
            }else if (n4!= 3) {
                n4 += 1;
                System.out.println("c4: "+n4);
                c4.quitarVida(n4);
            }else if (n3!= 3) {
                n3 += 1;
                System.out.println("c3: "+n3);
                c3.quitarVida(n3);
            }else if (n2!= 3) {
                n2 += 1;
                System.out.println("c2: "+n2);
                c2.quitarVida(n2);
            }else if (n1!= 3) {
                n1 += 1;
                System.out.println("c1: "+n1);
                c1.quitarVida(n1);
            }else{
                nDineroActual = 0;
                dineroActual.setText(String.valueOf(nDineroActual));
            }
        }
    }
}
