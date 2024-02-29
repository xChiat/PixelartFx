package TragaPerras;

import javafx.application.Platform;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BarraDeVida extends Label {
    private Image[] corazones;
    private ImageView[] vistas;
    private int cImage = 4;
    public BarraDeVida() {
        corazones = new Image[4];
        vistas = new ImageView[4];

        prepararImagenes();
    }

    private void prepararImagenes() {
        for(int i = 1; i <cImage; i++){
            String ruta = "file:./vida/"+i+".png";
            corazones[i] = new Image(ruta);
            vistas[i] = new ImageView(corazones[i]);
            vistas[i].setId(String.valueOf(i));
        }
        setGraphic(vistas[1]);
    }
    public int getIdHeart(){
        int n = Integer.parseInt(getGraphic().getId());
        return n;
    }
    public void quitarVida(int idh){
        System.out.println(idh);
        Thread hilo = new Thread(){
            public void run(){
                try {
                    int n = idh +1;
                    Platform.runLater(()-> setGraphic(vistas[n]));

                    Thread.sleep(23); // aumentamos el tiempo de la animacion para que pueda verse
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        hilo.start();
    }
}
