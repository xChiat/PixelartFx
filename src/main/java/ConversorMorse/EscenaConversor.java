package ConversorMorse;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class EscenaConversor extends Scene {
    private VBox principal;
    private Label conversor, textoResultante;
    private HBox panel,panelResult;
    private Button btnConvertir;
    private TextField texto;

    public EscenaConversor(Parent contenedor, double ancho, double alto) {
        super(contenedor, ancho, alto);
        principal = (VBox) contenedor;
        conversor = new Label("Conversor morse/texto");
        textoResultante = new Label();
        conversor.setAlignment(Pos.TOP_CENTER);
        panel = new HBox(10);
        panelResult = new HBox(10);
        btnConvertir = new Button("convertir");
        texto = new TextField("Escriba El texto que desea convertir");
        texto.setMaxSize(300,25);
        texto.setMinSize(300,25);
        texto.setStyle("-fx-border-color: black; -fx-font-family: serif; -fx-font-size: large; -fx-border-width: 1px");

        montarEscena();
    }

    private void montarEscena() {
        panel.getChildren().addAll(btnConvertir,texto);
        panel.setAlignment(Pos.CENTER);
        panelResult.getChildren().add(textoResultante);
        panelResult.setAlignment(Pos.BOTTOM_CENTER);
        btnConvertir.setOnAction(e->convertirTexto());
        principal.getChildren().addAll(panel,panelResult);
        principal.setAlignment(Pos.CENTER);
    }

    private void convertirTexto() {
        HashMap<String, String> txtToMorse = new HashMap<String, String>();
        txtToMorse.put("a",".-"); txtToMorse.put("b","-..."); txtToMorse.put("c","-.-.");
        txtToMorse.put("d","-.."); txtToMorse.put("e","."); txtToMorse.put("f","..-.");
        txtToMorse.put("g","--."); txtToMorse.put("h","...."); txtToMorse.put("i","..");
        txtToMorse.put("j",".---"); txtToMorse.put("k","-.-"); txtToMorse.put("l",".-..");
        txtToMorse.put("m","--"); txtToMorse.put("n","-."); txtToMorse.put("o","---");
        txtToMorse.put("p",".--."); txtToMorse.put("q","--.-"); txtToMorse.put("r",".-.");
        txtToMorse.put("s","..."); txtToMorse.put("t","-"); txtToMorse.put("u","..-");
        txtToMorse.put("v","...-"); txtToMorse.put("w",".--"); txtToMorse.put("x","-..-");
        txtToMorse.put("y","-.--"); txtToMorse.put("z","--.."); txtToMorse.put(" ","/");

        // Obtener el texto ingresado por el usuario
        String textoIngresado = texto.getText().toLowerCase(); // Convertir a minúsculas para manejar mayúsculas y minúsculas

        // Convertir cada caracter y construir la cadena en morse y en texto
        StringBuilder morseResult = new StringBuilder();
        StringBuilder textoResult = new StringBuilder();

        // Variable para distinguir entre caracteres en morse
        boolean esMorse = false;

        for (int i = 0; i <= textoIngresado.length(); i++) {
            char caracter = (i < textoIngresado.length()) ? textoIngresado.charAt(i) : ' ';
            String simbolo = Character.toString(caracter);

            if (simbolo.equals(".") || simbolo.equals("-")) {
                morseResult.append(simbolo);
                esMorse = true;
            } else if (simbolo.equals(" ")) {
                // Espacio indica el final de una letra en morse
                if (esMorse) {
                    // Buscar la letra en el HashMap y agregar al resultado en texto
                    boolean letraEncontrada = false;
                    for (String key : txtToMorse.keySet()) {
                        if (txtToMorse.get(key).equals(morseResult.toString())) {
                            textoResult.append(key);
                            letraEncontrada = true;
                            break;
                        }
                    }

                    if (!letraEncontrada) {
                        // Si no se encuentra una letra, puede ser un espacio entre palabras
                        textoResult.append(" ");
                    }

                    morseResult.setLength(0);  // Limpiar el StringBuilder para la siguiente letra en morse
                } else {
                    textoResult.append(" ");  // Espacio en blanco entre palabras
                }
                esMorse = false;
            } else {
                // Caracter no es Morse, buscar en el HashMap y agregar al resultado en morse
                if (txtToMorse.containsKey(simbolo)) {
                    morseResult.append(txtToMorse.get(simbolo)).append(" ");
                } else {
                    morseResult.append(" ");
                }
                esMorse = false;
            }
        }

        // Imprimir o utilizar los resultados
        String resultadoMorse = morseResult.toString().trim();
        String resultadoTexto = textoResult.toString().trim();

        boolean esMrs = resultadoTexto.isEmpty();
        System.out.println(esMrs);

        if (esMrs) {
            textoResultante.setText("Texto resultante: " + resultadoMorse);
        } else {
            textoResultante.setText("Texto resultante: " + resultadoTexto);
        }

    }
}
