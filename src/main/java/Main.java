import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


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
        return solucionPrivada(nums, target);
    }

    private static String solucionPrivada(List<NumberToken> nums, int target){
        // logica para encontrar la solucion
        Stack<NumberToken> stack = new Stack<>();
        int countSelecciones = 0;
        int resultado = 0;
        boolean encontrado = false;
        // eleccion
        // restricciones
        // solucion
        // retroceder, hay que podar
        // se pude memorizar el resultado de la solucion para no repetir el mismo calculo
        // operaciones : '+', '-', '*', '/', no creo que haga falta el enum

        // caso base
        if (resultado == target && encontrado) {
            return "Solucion encontrada: " + stack.toString();
        }

        // eleccion y caso recursivo
        for (NumberToken num : nums) {
            stack.push(num);
            // los problemas son varios, hay que tener cuidado con el resultado, si es mayor al target, hay que retroceder
            // y hay que probar con las demas operaciones
            resultado += num.getNumber();
            countSelecciones++;
            if (resultado == target) {
                encontrado = true;
                return "Solucion encontrada: " + stack.toString();
            } else if (resultado < target) {
                String res = solucionPrivada(nums, target);
                if (res != null) {
                    return res;
                }
            }
            // esto no sé si es correcto, pero hay que retroceder y probar con las demas operaciones
            stack.pop();
            resultado -= num.getNumber();
            countSelecciones--;
        }
        return null;
    }

    // podriamos hacer como hace el profe, un metodo que
    // se una lista de numeros, que devuelvan una lista de numeros que sumen, multipliquen, dividan o resten a un target,
    // y que devuelva una lista de listas de numeros que cumplan con la condicion
    private List<NumberToken> combinaciones(List<NumberToken> nums, int target) {
        List<NumberToken> result = new ArrayList<>();
        // logica para encontrar las combinaciones
        NumberToken tokenSuma = new NumberToken(target);// aqui deberia de ir la suma de los numeros, pero no se como hacerlo
        NumberToken tokenResta = new NumberToken(target);
        NumberToken tokenMultiplicacion = new NumberToken(target);
        NumberToken tokenDivision = new NumberToken(target);

        return result;
    }

    public static void main(String[] args) {
        List<NumberToken> tokens = tokenizer("234, -232");

        System.out.println("Tokens: ");
        System.out.println(tokens);

        System.out.println("Simbolo: ");
        for (NumberToken token : tokens) {
            System.out.println(token);
        }
    }
}
