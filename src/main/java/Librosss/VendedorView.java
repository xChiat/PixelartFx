package Librosss;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import Librosss.CompradorView;

public class VendedorView extends Scene {
    private BorderPane raiz;
    private Stage escenario;
    private int ganancias = 0;
    private int totalLibrosVendidos = 0;
    private Libro libroConMayorRecaudacion;

    private Label mayorRecaudacionLabel,totalLibrosVendidosLabel;
    private VBox resultadosContainer;
    private VBox principal;

    private Label resultadoLabel;

    public VendedorView(Parent raiz, double ancho, double alto, Stage escenario) {
        super(raiz, ancho, alto);
        this.raiz = (BorderPane) raiz;
        this.escenario = escenario;

        principal = new VBox(10);
        principal.setPadding(new Insets(10));

        mayorRecaudacionLabel = new Label("Libro con mayor recaudación: ");
        mayorRecaudacionLabel.setStyle("-fx-border-color: black; -fx-border-width: 1px");
        mayorRecaudacionLabel.setMaxSize(300,25);
        totalLibrosVendidosLabel = new Label("Cantidad total de libros vendidos: ");
        totalLibrosVendidosLabel.setStyle("-fx-border-color: black; -fx-border-width: 1px");
        totalLibrosVendidosLabel.setMaxSize(300,25);
        resultadosContainer = new VBox(10);

        montarEscena();

    }

    private void montarEscena() {
        principal.getChildren().addAll(mayorRecaudacionLabel, totalLibrosVendidosLabel, resultadosContainer);
        principal.setAlignment(Pos.CENTER_LEFT);
        principal.setStyle("-fx-border-color: black; -fx-border-width: 1px");
        raiz.setCenter(principal);
    }

    public void actualizarResultados() {
        // Actualizar el libro con mayor recaudación
        Libro libroConMayorRecaudacionAnterior = libroConMayorRecaudacion;
        libroConMayorRecaudacion = encontrarLibroConMayorRecaudacion();

        // Verificar si el libro con mayor recaudación ha cambiado
        if (libroConMayorRecaudacion != libroConMayorRecaudacionAnterior) {
            mayorRecaudacionLabel.setText("Libro con mayor recaudación: " + (libroConMayorRecaudacion != null ? libroConMayorRecaudacion.getNombre() : ""));
        }

        // Actualizar la etiqueta de cantidad total de libros vendidos
        totalLibrosVendidosLabel.setText("Cantidad total de libros vendidos: " + totalLibrosVendidos);

        // Actualizar la etiqueta de resultados por libro
        resultadosContainer.getChildren().clear();
        for (Libro libro : obtenerLibros()) {
            double porcentajeVentas = (libro.getPrecio() * libro.getStock() == 0) ? 0 : (ganancias * 100.0) / (libro.getPrecio() * libro.getStock());

            Label resultadoLabel = new Label("Nombre del Libro: " + libro.getNombre() +
                    " || Recaudación: " + libro.getPrecio() * libro.getStock() +
                    " || Porcentaje de Ventas: " + String.format("%.2f", porcentajeVentas) + "%");

            resultadosContainer.getChildren().add(resultadoLabel);
        }
    }

    private Libro[] obtenerLibros() {
        List<Libro> listaLibros = CompradorView.getListaLibros();
        return listaLibros.toArray(new Libro[0]);
    }

    private Libro encontrarLibroConMayorRecaudacion() {
        Libro libroConMayorRecaudacion = null;
        int mayorRecaudacion = 0;

        for (Libro libro : obtenerLibros()) {
            int recaudacionLibro = libro.getPrecio() * libro.getStock();
            if (libroConMayorRecaudacion == null || recaudacionLibro > mayorRecaudacion) {
                mayorRecaudacion = recaudacionLibro;
                libroConMayorRecaudacion = libro;
            }
        }

        return libroConMayorRecaudacion;
    }

    public void venderLibro(int precio, int cantidad) {
        ganancias += precio * cantidad;
        totalLibrosVendidos += cantidad;
        actualizarResultados();
    }
}
