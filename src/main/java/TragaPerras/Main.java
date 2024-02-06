package TragaPerras;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage escenario) throws Exception {
        EscenaTragaperras escena = new EscenaTragaperras(new BorderPane(),600,500);
        escenario.setTitle("Tragaperras");
        escenario.setScene(escena);
        escenario.show();

    }
}
