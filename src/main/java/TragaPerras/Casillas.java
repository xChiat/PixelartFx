package TragaPerras;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Casillas extends Label {
    private Image[] imagenes;
    private ImageView[] vistas;
    private int cImage = 13; // cambiar este numero para agregar mas imagenes.

    public Casillas() {
        imagenes = new Image[13]; // cambiar este numero para agregar mas imagenes.
        vistas = new ImageView[13]; // cambiar este numero para agregar mas imagenes.

        prepararImagenes();
    }

    private void prepararImagenes() {
        for(int i = 0; i <cImage; i++){
            String ruta = "file:./imagenes/"+i+".png";
            imagenes[i] = new Image(ruta);
            vistas[i] = new ImageView(imagenes[i]);
            vistas[i].setId(String.valueOf(i));
        }
        setGraphic(vistas[0]);
    }
    public void tirar(Button btn){ // recibe un boton para rehabilitar el btn depues de acabar la animacion
        Thread hilo = new Thread(){
            public void run(){
                for(int i = 0; i < 13; i++){
                    try {
                        // para cambiar el numero de imagenes recuerda agrandar las listas vistas y imagenes
                        int aux = (int) Math.round(Math.random() *12); // genera un numero aleatorio del 0 al 12
                        if(aux == 2) aux = (int) Math.round(Math.random() *12); // si el numero es igual a dos repite la animacion nuevamente

                        int n = aux;

                        Platform.runLater(()-> setGraphic(vistas[n]));

                        Thread.sleep(80); // aumentamos el tiempo de la animacion para que pueda verse

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(btn != null) {
                    btn.setDisable(false);
                    Platform.runLater(() -> EscenaTragaperras.comprobarTiradas());
                }
            }
        };

        hilo.start();
    }
    public int getIdImagen(){
        int n = Integer.parseInt(getGraphic().getId());
        return n;
    }
}
