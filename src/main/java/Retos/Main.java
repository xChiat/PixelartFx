package Retos;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        /*
         * Escribe un programa que imprima los 50 primeros números de la sucesión
         * de Fibonacci empezando en 0.
         * - La serie Fibonacci se compone por una sucesión de números en
         *   la que el siguiente siempre es la suma de los dos anteriores.
         *   0, 1, 1, 2, 3, 5, 8, 13...
         */
        long num1 = 1;
        long num2 = 0;
        List<Long> Fibonacci = new ArrayList<Long>();
        Fibonacci.add(num2);
        Fibonacci.add(num1);
        for(int i = 1; i < 50; i++){
            num1 = num1 + num2;
            Fibonacci.add(num1);
            num2 = Fibonacci.get(i);
        }
        System.out.println(Fibonacci);
    }
}
