package TragaPerras;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Casillas extends Label {
    private Image[] imagenes;
    private ImageView[] vistas;
    private int cImage = 13;

    public Casillas() {
        imagenes = new Image[13];
        vistas = new ImageView[13];

        prepararImagenes();
    }

    private void prepararImagenes() {
        for(int i = 0; i <cImage; i++){
            String ruta = "file:./imagenes/"+i+".png";
            imagenes[i] = new Image(ruta);
            vistas[i] = new ImageView(imagenes[i]);
        }
        setGraphic(vistas[0]);
    }
    public void tirar(Button btn){
        Thread hilo = new Thread(){
            public void run(){
                for(int i = 0; i < 13; i++){
                    try {
                        int aux = (int) Math.round(Math.random() *12);
                        if(aux == 2) aux = (int) Math.round(Math.random() *12);

                        int n = aux;

                        Platform.runLater(()-> setGraphic(vistas[n]));

                        Thread.sleep(80);

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(btn != null) btn.setDisable(false);
            }
        };

        hilo.start();
    }
}
