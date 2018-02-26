import java.io.File;
import java.util.Scanner;

public class Simulacion {

    public static int estado(char []alfabeto, char simbolo) {
        for( int i = 0; i < alfabeto.length; i++ ) {
            if( alfabeto[i] == simbolo )
                return i; }  return -1; }

    public static void main(String[] args) {
        String scanString; StringBuilder concatenar = new StringBuilder(); String entrada = ""; char []alfabeto; int estadoInicial = -1; int []estadoFinal; int [][] transicion; int n=0;
        try(Scanner scan = new Scanner(new File("D:\\Escritorio- Data\\automata.txt")))  { //Se busca el archivo en el escritorio
            entrada = scan.nextLine(); //Se captura la primera linea del archivo (La cadena)
            scanString = scan.nextLine();
            alfabeto = new char[scanString.split(";").length]; //Se toma el alfabeto dividido con punto y coma usando .split

            do{                                                       //Se hace un loop para tomar los simbolos del alfabeto
                alfabeto[n] = scanString.split(";")[n].charAt(0); //Se itera el arreglo para guardar lo que este separado por ";"
                n++;
            }while(n<scanString.split(";").length);

            estadoInicial = Integer.parseInt(scan.nextLine());  //Se escanea la siguiente linea que es el estado inicial
            scanString = scan.nextLine();                       //Se guarda en scanString para despues pasarlo al estado final
            estadoFinal = new int[scanString.split(";").length]; //Se escanea la siguiente linea que son los estados finales
            n=0;//reinicia n

            do {
                estadoFinal[n] = Integer.parseInt(scanString.split(";")[n]); //Se guarda el estado final
                n++;
            }while (n<scanString.split(";").length); //se escanea la longitud en el archivo.txt

            scanString = "";
            do {            //Se hace un loop para obtener las transiciones
                scanString += scan.nextLine()+"\n"; //Se escanea la siguiente linea
            }while(scan.hasNextLine());
            transicion = new int[scanString.split("\n").length][alfabeto.length]; //Se guarda la cadena del alfabeto
            for(int i=0; i<scanString.split("\n").length; i++) {
                for(int h=0; h<alfabeto.length; h++) {
                    transicion[i][h] = Integer.parseInt(String.valueOf(scanString.split("\n")[i].split(";")[h].charAt(0))); }
            }
            System.out.printf("%s\nSecuencia de estados: %s\n",
                    IteracionCadena(entrada.toCharArray(),
                            alfabeto,
                            estadoInicial,
                            estadoFinal,
                            transicion,
                            concatenar)?
                            "Aceptada":"Rechazada",concatenar.toString()); //Se imprime la cadena como se pide
        }
        catch(Exception ex) { //En caso de tener problemas con el archivo se imprimira un error
            System.out.println("Error: "
                    +ex.getMessage()); }
    }

    public static boolean IteracionCadena(char []palabra, char []alfabeto, int estadoInicial, int []estadoFinal, int [][] transicion, StringBuilder reccorido) {
        boolean regresa = false;
        int estadoActual = estadoInicial;           //Comienza en el estado inicial
        reccorido.append(String.format("%d/",estadoActual)); //guarda el estado inicial en el recorrido
        for(char car : palabra) {
            estadoActual = transicion[estadoActual][estado(alfabeto,car)]; //itera los estados para guardarlos en la siguiente instruccion llando a la funcion estado
            reccorido.append(String.format("%d/",estadoActual)); }          //hace un toString de los estados
        for(int estadofinal : estadoFinal) {
            regresa |= estadofinal == estadoActual; }return regresa;             //Se guarda el estado ginal
    }
}
