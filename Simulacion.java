import java.io.File;
import java.util.Scanner;

public class Simulacion {

    public static int estado(char []alfabeto, char caracterCadena) {
        for( int i = 0; i < alfabeto.length; i++ ) {
            if( alfabeto[i] == caracterCadena )
                return i; }  return -1; }


    public static void main(String[] args) {
        String scanString; StringBuilder imprimirCadena = new StringBuilder();  String entrada = ""; char []alfabeto; int estadoInicial = -1; int []estadoFinal; int [][] transicion; int n=0;

        //-------------------------------------------------------------------------------------------------------------------------------------------
        try(
                Scanner scan = new Scanner(new File("D:\\Escritorio- Data\\automata.txt")))  { //Se busca el archivo en el escritorio
            entrada = scan.nextLine(); //Se captura la primera linea del archivo (La cadena)
            scanString = scan.nextLine();
            alfabeto = new char[scanString.split(";").length]; //Se toma el alfabeto dividido con punto y coma usando .split

            do{
                alfabeto[n] = scanString.split(";")[n].charAt(0); //Se itera el arreglo para guardar el alfabeto separado por ;
                n++;
            }while
                    (n<scanString.split(";").length);

            estadoInicial = Integer.parseInt(scan.nextLine());  //Se escanea la siguiente linea que es el estado inicial
            scanString = scan.nextLine();                       //Se guarda en scanString para despues pasarlo al estado final
            estadoFinal = new int[scanString.split(";").length]; //Se escanea la siguiente linea que son los estados finales
            n=0;//reinicia n

            do {
                estadoFinal[n] = Integer.parseInt(scanString.split(";")[n]); //Se guardan los estados finales separados por ;
                n++;
            }while
                    (n<scanString.split(";").length); //Condición para cubrir todos los estados finales

            scanString = ""; //Se vacía el scanner de apoyo
            do {
                scanString += scan.nextLine()+"\n"; //Se guardan lo que queda del archivo, (transiciones)
            }while(scan.hasNextLine()); //se siguen guardando mientras haya más lineas

            transicion = new int[scanString.split("\n").length][alfabeto.length]; //Se reservan los espacios de transición
            for(int i=0; i<scanString.split("\n").length; i++) {
                for(int h=0; h<alfabeto.length; h++) {
                    transicion[i][h] = Integer.parseInt(String.valueOf(scanString.split("\n")[i].split(";")[h].charAt(0))); }
                //Se llena la matriz de un lado a los que van con 0(a) y del otro a los que van con 1(b)
            }
            //-------------------------------------------------------------------------------------------------------------------------------------------
            System.out.printf("%s\nSecuencia de estados: %s\n",
                    IteracionCadena(entrada.toCharArray(),
                            alfabeto,
                            estadoInicial,
                            estadoFinal,
                            transicion,
                            imprimirCadena)?
                            "Aceptada":"Denegada",
                    imprimirCadena.toString()); //Se imprime la cadena como se pide

        }
        catch(Exception ex) { //En caso de no encontrar el archivo imprimira error
            System.out.println("Error: El archivo no ha sido encontrado"); }
    }

    public static boolean IteracionCadena(char []palabra, char []alfabeto, int estadoInicial, int []estadoFinal, int [][] transicion, StringBuilder reccorido) {
        boolean regresa = false;
        int estadoActual = estadoInicial;           //Comienza en el estado inicial
        reccorido.append(String.format("%d/",estadoActual)); //guarda el estado inicial en el recorrido
        for(char caracterCadena : palabra) {
            estadoActual = transicion[estadoActual][estado(alfabeto,caracterCadena)]; //itera la cadena llamando a la funcion estado
            reccorido.append(String.format("%d/",estadoActual)); }          //Se concatena el recorrido de los estados
        for(int estadofinal : estadoFinal) {
            regresa |= estadofinal == estadoActual; }return regresa;   //si el estado final es igual al actual la cadena se acepta
    }


}

