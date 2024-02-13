package Modelo;

public class MMat {
    public double calcular(double numA, double numB, char operador){
        double resp = 0.0;
        if(operador == '+')
            resp = numA + numB;
        else if(operador == '-')
            resp = numA - numB;
        else if(operador == '*')
            resp = numA * numB;
        else if(operador == '/')
            resp = numA / numB;
        return resp;
    }
}
