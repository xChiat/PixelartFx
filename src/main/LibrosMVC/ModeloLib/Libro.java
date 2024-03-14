package ModeloLib;

public class Libro{
    private int idLib;
    private String nombre;
    private int precio;
    private int stock;
    public Libro(int idLib, String nombre, int precio, int stock){
        this.idLib = idLib;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
    public int getID(){
        return idLib;
    }
    public String getNombre(){
        return nombre;
    }
    public int getPrecio(){
        return precio;
    }
    public int getStock(){
        return stock;
    }

    public void setIdLib(int idLib) {
        this.idLib = idLib;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}
