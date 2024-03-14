package Controller;
import ModeloLib.Libro;
import VistaLib.Cliente;
public class CLib {
    public String infoLib1, infoLib2, infoLib3;
    private Libro lib1,lib2,lib3;
    private Cliente cliente;
    public CLib(Libro lib1,Libro lib2,Libro lib3,Cliente cliente){
        this.lib1=lib1;
        this.lib2=lib2;
        this.lib3=lib3;
        this.cliente=cliente;

        lib1.setIdLib(1);
        lib1.setNombre("El retrato de Dorian Gray.");
        lib1.setPrecio(4500);
        lib1.setStock(50);

        lib2.setIdLib(2);
        lib2.setNombre("La caída de los reinos.");
        lib2.setPrecio(6700);
        lib2.setStock(50);

        lib3.setIdLib(3);
        lib3.setNombre("Las crónicas de la torre.");
        lib3.setPrecio(8200);
        lib3.setStock(50);

        String n1=lib1.getNombre();
        int p1 = lib1.getPrecio();
        int s1 = lib1.getStock();
        infoLib1= "Nombre: "+n1+" Precio:"+p1+" Stock:"+s1;

        String n2=lib2.getNombre();
        int p2 = lib2.getPrecio();
        int s2 = lib2.getStock();
        infoLib2= "Nombre: "+n2+" Precio:"+p2+" Stock:"+s2;

        String n3=lib3.getNombre();
        int p3 = lib3.getPrecio();
        int s3 = lib3.getStock();
        infoLib3= "Nombre: "+n3+" Precio:"+p3+" Stock:"+s3;
    }
    public String getInfoLib1(){
        return infoLib1;
    }
    public String getInfoLib2(){
        return infoLib2;
    }
    public String getInfoLib3(){
        return infoLib3;
    }
}
