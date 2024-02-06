package colorearPixelArt;

import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;

public class CuadradoColorear extends Button {
    private double lado = 20;
    private String colorCSS;
    private static String colorElegido = "-fx-background-color: black;";
    private static CuadradoColorear colorAntiguo = null;
    private boolean coloreado = false;
    public CuadradoColorear() {
        this.setShape(new Rectangle(lado,lado));
        this.setMinSize(lado, lado);
        this.setMaxSize(lado, lado);
        this.setOnAction(e->pintar());
    }
    private void pintar() {
        this.setStyle(colorElegido);
        coloreado = true;
    }
    public CuadradoColorear(String colorCSS) {
        this.setShape(new Rectangle(lado,lado));
        this.setMinSize(lado, lado);
        this.setMaxSize(lado, lado);
        this.setStyle(colorCSS+"-fx-border-color: black; -fx-border-width: 1px");
        this.colorCSS = colorCSS;
        this.setOnAction(e-> establecerColor());
    }
    private void establecerColor() {
        colorElegido=colorCSS;
        if(colorAntiguo != null)colorAntiguo.setDisable(false);
        colorAntiguo = this;
        this.setDisable(true);
    }
    public boolean getColoreado(){
        return coloreado;
    }
    public void blanquear(){
        this.setBackground(null);
    }
}
