package Principal;

import Controlador.CMat;
import Modelo.MMat;
import Vista.IMat;
import Vista.VMat;

public class Principal {
    public static void main(String[] args){
        MMat modelo= new MMat();
        IMat vista= new VMat();

        CMat controlador= new CMat(modelo,vista);

        vista.setControlador(controlador);
        vista.arrancar();
    }
}
