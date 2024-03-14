package Librosss;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompradorView extends Scene {
    private BorderPane raiz;
    private Stage escenario;
    private VendedorView vendedorView;
    private static List<Libro> listaLibros;
    private int ganancias = 0;

    private Label totalLabel, nombreLabel, precioLabel, stockLabel, cantidadLabel;
    private VBox librosContainer;
    private VBox principal;

    private Libro libro1;
    private Libro libro2;
    private Libro libro3;
    private Button btnAgregar, comprar;
    private HBox libroBox, pnlInf;

    public CompradorView(Parent raiz, double ancho, double alto, Stage escenario) {
        super(raiz, ancho, alto);
        this.raiz = (BorderPane) raiz;
        this.escenario = escenario;



        principal = new VBox(10);
        principal.setPadding(new Insets(10));

        librosContainer = new VBox(10);
        librosContainer.setAlignment(Pos.CENTER_LEFT);

        libro1 = new Libro(1, "El retrato de Dorian Gray", 4500, 50);
        libro2 = new Libro(2, "La caída de los reinos", 6200, 50);
        libro3 = new Libro(3, "Las crónicas de la torre", 8700, 50);

        listaLibros = new ArrayList<>(Arrays.asList(libro1, libro2, libro3));

        comprar = new Button("Comprar");
        comprar.setAlignment(Pos.CENTER_RIGHT);
        totalLabel = new Label("Total: 0");
        totalLabel.setAlignment(Pos.CENTER_RIGHT);
        pnlInf = new HBox();
        pnlInf.setAlignment(Pos.CENTER_RIGHT);

        montarEscena();
    }

    private void montarEscena() {
        librosContainer.getChildren().addAll(
                crearLibroLabel(libro1),
                crearLibroLabel(libro2),
                crearLibroLabel(libro3)
        );
        librosContainer.setAlignment(Pos.CENTER);
        comprar.setOnAction(e -> comprarLibros());
        pnlInf.getChildren().addAll(totalLabel, comprar);
        pnlInf.setPadding(new Insets(5,15,5,0));
        principal.getChildren().addAll(librosContainer,pnlInf);
        raiz.setCenter(principal);

    }

    private HBox crearLibroLabel(Libro libro) {
        nombreLabel = new Label("Nombre: " + libro.getNombre());
        precioLabel = new Label("Precio: " + libro.getPrecio());
        stockLabel = new Label("Stock: " + libro.getStock());

        btnAgregar = new Button("+");
        btnAgregar.setShape(new Rectangle(15,15));

        Label cantidadLabel = new Label("0");

        btnAgregar.setOnAction(e -> comprarLibro(libro, cantidadLabel));


        libroBox = new HBox(10);
        libroBox.getChildren().addAll(nombreLabel, precioLabel, stockLabel, btnAgregar, cantidadLabel);
        libroBox.setMaxSize(400,25);
        libroBox.setMinSize(400,25);
        libroBox.setAlignment(Pos.CENTER);
        libroBox.setStyle("-fx-border-color: black; -fx-border-width: 1px");

        return libroBox;
    }

    private void comprarLibro(Libro libro, Label cantidadLabel) {
        int cantidadActual = Integer.parseInt(cantidadLabel.getText());
        if (cantidadActual < libro.getStock()) {
            cantidadActual++;
            cantidadLabel.setText(String.valueOf(cantidadActual));
            ganancias += libro.getPrecio();
            vendedorView.venderLibro(libro.getPrecio(), 1);
            actualizarTotal();
        } else {
            // Puedes manejar el caso donde no hay suficiente stock.
            System.out.println("No hay suficiente stock para " + libro.getNombre());
        }
    }
    public static List<Libro> getListaLibros() {
        return listaLibros;
    }

    private void comprarLibros() {
        vendedorView.actualizarResultados();
    }

    private void actualizarTotal() {
        totalLabel.setText("Total: " + ganancias);
    }

    public void setVendedorView(VendedorView vendedorView) {
        this.vendedorView = vendedorView;
    }

}