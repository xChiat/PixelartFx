package colorearPixelArt;


import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class EscenaPixelArt extends Scene {
    private VBox principal,filas;
    private HBox[] cuadrados; //Cuadrados x filas
    private HBox colores;
    private Button btnGuardar;
    public EscenaPixelArt(Parent contenedor, double alto, double ancho) {
        super(contenedor, alto, ancho);
        principal = (VBox) contenedor;
        principal.setSpacing(20);
        filas = new VBox(1);
        cuadrados = new HBox[25];
        colores = new HBox(7);
        colores.setAlignment(Pos.CENTER);
        btnGuardar = new Button("Guardar Imagen");

        montarEscena();

    }

    private void montarEscena() {
        principal.setAlignment(Pos.TOP_CENTER);

        // preparacion Cuadros de Pintura

        for(int i = 0; i<cuadrados.length;i++) {

            cuadrados[i]= new HBox(1);
            cuadrados[i].setAlignment(Pos.TOP_CENTER);

            for(int j=0; j<25;j++) {
                cuadrados[i].getChildren().add(new CuadradoColorear());
            }
            filas.getChildren().add(cuadrados[i]);
        }

        prepararColores();

        btnGuardar.setOnAction(e-> guardarImagen());

        principal.getChildren().addAll(filas,colores,btnGuardar);
    }

    private void guardarImagen() {
        quitarBlancos();

        WritableImage imagen = filas.snapshot(new SnapshotParameters(),null);
        File archivo = new File(System.getProperty("user.home")+"/OneDrive/Obrázky/ndea/loquesea.png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(imagen,null),"png",archivo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        restaurarBlancos();
    }

    private void restaurarBlancos() {
        for(int i = 0;i<cuadrados.length;i++){
            List fila = cuadrados[i].getChildren();
            for(int j = 0;j<fila.size();j++) {
                CuadradoColorear c = (CuadradoColorear)fila.get(j);
                if(!c.getColoreado())fila.set(j,new CuadradoColorear());
            }
        }
    }

    private void quitarBlancos() {
        for(int i = 0;i<cuadrados.length;i++){
            List fila = cuadrados[i].getChildren();
            for(int j = 0;j<fila.size();j++) {
                CuadradoColorear c = (CuadradoColorear)fila.get(j);
                if(!c.getColoreado())c.blanquear();
            }
        }
    }

    private void prepararColores() {
        String[] nombresColores = {"black","#F0F8FF","#5F9EA0","#DC143C","#FFF8DC","#8A2BE2","#F5F5DC",
                "#FFD700","#ADFF2F","#FFA500","#6B8E23","#FFA07A","#6A5ACD","#663399","#BC8F8F","#708090",
                "#FFFF00","#F5F5F5","#D8BFD8","#9ACD32"};
        for(String s : nombresColores) {
            colores.getChildren().add(new CuadradoColorear("-fx-background-color: " + s + ";"));
        }
    }
}