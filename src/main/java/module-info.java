module yosolito.pixelartfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;
    requires javafx.swing;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires javafx.graphics;
    requires javafx.media;

    opens colorearPixelArt to javafx.graphics;
    opens TragaPerras to javafx.graphics;
    opens ConversorMorse to javafx.graphics;
    opens VistaLib to javafx.graphics;
    opens Main to javafx.graphics;
    opens ModeloLib to javafx.graphics;
    opens Librosss to javafx.graphics;


    exports colorearPixelArt;
}