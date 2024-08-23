package aed;

class Funciones {
    int cuadrado(int x) {
        return x*x;
    }

    double distancia(double x, double y) {
        return Math.sqrt(x*x+y*y);
    }

    boolean esPar(int n) {
        return (n % 2 == 0) ? true : false;
    }

    boolean esBisiesto(int n) {
        return (n % 100 == 0) ? ((n % 4 == 0 && n % 400 == 0) ? true : false) : ((n % 4 == 0) ? true : false) ;
    }

    int factorialIterativo(int n) {
        if (n == 0) {return 1; }

        int factorial = 1;
        
        for(int i = 1; i <= n; i++) {factorial *= i; }
        
        return factorial;
    }

    int factorialRecursivo(int n) {
        if (n == 0) {return 1; }

        return n * factorialRecursivo(n-1);
    }

    boolean esPrimo(int n) {
        if(n==0) return false;
        if(n==1) return false;
        
        boolean primo = true;

        for(int i = 2; i <= Math.sqrt(n); i++){
            if(n%i == 0){
                System.out.println(n);
                System.out.println(i);
                primo = false;
                break;
            }
        }
        
        return primo;
    }

    int sumatoria(int[] numeros) {
        if(numeros.length == 0) return 0;

        int sum = 0;
        
        for(int num : numeros) sum += num;

        return sum;
    }

    int busqueda(int[] numeros, int buscado) {
        for(int i = 0; i < numeros.length; i++) if(buscado == numeros[i]) return i;

        return -1;
    }

    boolean tienePrimo(int[] numeros) {
        for(int num : numeros) if(esPrimo(num)) return true;

        return false;
    }

    boolean todosPares(int[] numeros) {
        for(int num : numeros) if(!esPar(num)) return false;
        
        return true;
    }

    boolean esPrefijo(String s1, String s2) {
        //Casos especiales
        if(s1.length() > s2.length()) return false;
        if(s1.equals(s2)) return true; 

        //Caso regular
        if(s1.equals(s2.substring(0, s1.length()))) return true;
        
        return false;
    }

    String reverse(String s1){
        String reverso = "";

        for (int i = s1.length()-1; i >= 0; i--) reverso += s1.charAt(i);
        
        return reverso;
    }

    boolean esSufijo(String s1, String s2) {
        return esPrefijo(reverse(s1), reverse(s2));
    }
}
