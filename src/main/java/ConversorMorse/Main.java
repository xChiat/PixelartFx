package ConversorMorse;

import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage escenario) throws Exception {
        EscenaConversor escena = new EscenaConversor(new VBox(),600,300);
        escenario.setTitle("Conversor Morse");
        escenario.setScene(escena);
        escenario.show();
    }
}
