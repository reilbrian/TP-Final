package org.example;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;

public class Main {
    public static String PRONOSTICO = "C:\\Users\\brian\\OneDrive\\Escritorio\\escuela\\Argentina Programa\\tp integrador\\TP-Final\\src\\main\\entrega1\\pronostico.csv";
    public static String RESULTADOS = "C:\\Users\\brian\\OneDrive\\Escritorio\\escuela\\Argentina Programa\\tp integrador\\TP-Final\\src\\main\\entrega1\\resultados.csv";
    public static void main(String[] args) throws IOException {
        String pronostico = PRONOSTICO;
        String resultados = RESULTADOS;

        System.out.println("Este es nuestro string path:" + resultados);
        System.out.println("Este es nuestro string path:" + pronostico);

        //Lectura de los resultados de los partidos

        String lineares; //string que lee los resultados uno por uno
        BufferedReader br = new BufferedReader(new FileReader(resultados));
        lineares = br.readLine();
        String[] nombresequipo = new String[100];
        int[] golesanotados = new int[100];
        int[] rondanum= new int[100];
        int equiposcant = 0;
        int rondacant = 0;
        int j=0;
        for (int i = 0; (lineares = br.readLine()) != null; i++) {
            String[] posicion = lineares.split(";");
            rondanum[j]=Integer.parseInt(posicion[0]);
            nombresequipo[i] = posicion[1];
            golesanotados[i] = Integer.parseInt(posicion[2]);
            nombresequipo[i + 1] = posicion[3];
            golesanotados[i + 1] = Integer.parseInt(posicion[4]);
            rondacant=rondanum[i];
            i++;j++;
            equiposcant = equiposcant + 2;
        }

        //formador de equipos
        Equipo equipos[] = new Equipo[equiposcant];
        ArrayList<Partido> partidoss = new ArrayList<Partido>();
        ArrayList<Ronda> rondas = new ArrayList<Ronda>();
        Ronda ronda1 = new Ronda ();
        Ronda ronda2 = new Ronda ();

        // Pasar a una fase las rondas instanciadas.

        for (int i = 0; i < equiposcant; i++) {
            equipos[i] = new Equipo(nombresequipo[i], i);
        }

        //formador de partidos
        j = 0;int aux1=rondanum[0];
        for (int i = 0; i < (equiposcant / 2); i++) {
            partidoss.add(new Partido(equipos[j], equipos[j + 1], golesanotados[j], golesanotados[j + 1]));
            j = j + 2;
            if (aux1==rondanum[i]){
                ronda1.partidos.add(partidoss.get(i));
            }
            if (aux1!=rondanum[i]){
                ronda2.partidos.add(partidoss.get(i));
            }
        }

        rondas.add(ronda1);
        rondas.add(ronda2);

        // lectura del pronostico de los partidos

        String lineapro;
        BufferedReader br1 = new BufferedReader(new FileReader(pronostico));
        String[] resultadospro = new String[100];
        String[] equiposs=new String[100];
        ArrayList<String> nombres=new ArrayList<String>();

        lineapro = br1.readLine();
        int proncant = 0;
        j=0;
        for (int i = 0; (lineapro = br1.readLine()) != null; i++) {

            String[] posicion = lineapro.split(";");
            equiposs[j]=posicion[0];
            resultadospro[i] = posicion[1];
            resultadospro[i + 1] = posicion[2];
            resultadospro[i + 2] = posicion[3];
            equiposs[j+1]=posicion[4];
            nombres.add(posicion[5]);
            i = i + 2;
            j=j+2;
            proncant++;
        }
        j=0;int z=0;
        Pronostico[] pronosticos = new Pronostico[proncant];
        for (int i = 0; i < proncant; i++) {
            pronosticos[i] = new Pronostico
                    (equiposs[z],resultadospro[j], resultadospro[j + 1],resultadospro[j + 2],equiposs[z+1],nombres.get(i));
            System.out.println(pronosticos[i].getapuesta()+"  "+pronosticos[i].getapostador());
            j=j+3;z=z+2;
        }

        rondas.get(1).ListarGanadores();
        //detecta los pronosticos
        int[] puntaje = new int[10];
        j=0; z=0;String aux= pronosticos[0].getapostador();
        for (int i = 0; i < pronosticos.length; i++) {

            if(aux.equals(nombres.get(i))){}
            else {
                System.out.println(aux+" sumo :" + puntaje[j] + " puntos");
                j++;z=0;aux= pronosticos[i].getapostador();
            }

            if ((partidoss.get(z).getGanador()).equalsIgnoreCase(pronosticos[i].getapuesta()))  {
                puntaje[j]++;
            }
            z++;
        }
        System.out.println(aux+" sumo :" + puntaje[j] + " puntos");
    }
}