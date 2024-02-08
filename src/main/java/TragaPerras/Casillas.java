package TragaPerras;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Casillas extends Label {
    private Image[] imagenes;
    private ImageView[] vistas;
    private int cImage = 10;

    public Casillas() {
        imagenes = new Image[10];
        vistas = new ImageView[10];

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
}
