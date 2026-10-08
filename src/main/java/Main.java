import java.util.ArrayList;
import java.util.List;
import java.util.*;


public class Main {
    public static List<NumberToken> tokenizer(String input) {
        List<NumberToken> result = new ArrayList<>();

        Integer num = null;
        int signo = 1;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '-' && num == null) {
                signo = -1;
            } else if (Character.isDigit(c)) {
                int digit = Character.getNumericValue(c);

                if (num == null) {
                    num = digit;
                } else {
                    num = num * 10 + digit;
                }
            } else if (num != null) {
                result.add(new NumberToken(num * signo));

                num = null;
                signo = 1;
            }
        }

        if (num != null) {
            result.add(new NumberToken(num * signo));
        }

        return result;
    }



    public static String traductor(List<Integer> numbers) {
        return tokenizer(numbers.toString()).toString();
    }
    // una pirvada y otro publico
    public static String solucion(List<NumberToken> nums, int target){
        if (nums == null || nums.isEmpty()){
            return "La lista de numeros esta vacia";
        }
        int resultadoInicial = nums.get(0).getNumber();
        // sirve para recordar la operaciones que llevaron a ese resultado
        String expresionInicial = String.valueOf(resultadoInicial);
        // crear los estado fallidos, usamos un Set para esto, un set es parecido a un alista solo que no permite duplicados
        Set<String> estadosFallidos = new HashSet<>();
        return solucionPrivada(nums, target, 1, resultadoInicial, expresionInicial, estadosFallidos);
    }

    private static String solucionPrivada(List<NumberToken> nums, int target, int posicion, int resultadoActual, String expresion, Set<String> estadosFallidos){
        // caso base
        if (posicion == nums.size()){
            if (resultadoActual == target){
                return expresion;
            }else{
                return null;
            }
        }

        String estadoActual = resultadoActual + "," + posicion;
        // poda
        if (estadosFallidos.contains(estadoActual)){
            return null;
        }
        // caso recursivo
        // no representa como tal el siguiente numero, sino el numero actual
        NumberToken numeroActual = nums.get(posicion);
        int valorActual = numeroActual.getNumber();
        // hay que definir bien que son las podas
        // una poda normalmente se define como una condicion que nos permite descartar un camino de busqueda
        // aqui realmente la unica poda que tenemos es la division por cero, por que sabemos que no podemos dividir por cero, y si lo hacemos, el resultado es indefinido
        // para más exactitud, en este problema podemos decir que una poda es una condicion que permite descartar una eleccion antes de consumir los numeros de la lista
        // por que sabemos que es invalida (division por 0), o que ya fue explorada (memoizacion)
       for (char c : OPERADORES){
            int nuevoResultado = 0;
            switch (c){
                case '+':
                    nuevoResultado = resultadoActual + valorActual;
                    break;
                case '-':
                    nuevoResultado = resultadoActual - valorActual;
                    break;
                case '*':
                    nuevoResultado = resultadoActual * valorActual;
                    break;
                case '/':
                    // esto es muy importante, aqui van las podas
                    if (valorActual == 0) {
                        continue; // evitar division por cero
                    }
                    if (resultadoActual % valorActual != 0) {
                        continue; // evitar resultados no enteros
                    }
                    nuevoResultado = resultadoActual / valorActual;
                    break;
            }
            String nuevaExpresion = "(" + expresion + " " + c + " " + valorActual + ")";
            String solucion = solucionPrivada(nums, target, posicion + 1, nuevoResultado, nuevaExpresion, estadosFallidos);
            if (solucion != null){
                return solucion;
            }
       }
       estadosFallidos.add(estadoActual);
       return null;
    }

    private static final char[] OPERADORES = {'+', '-', '*', '/'};


    public static void main(String[] args) {
        List<NumberToken> tokens = Main.tokenizer("234, -232");

        System.out.println("Tokens: ");
        System.out.println(tokens);

        System.out.println("Simbolo: ");
        for (NumberToken token : tokens) {
            System.out.println(token);
        }
    }
}
