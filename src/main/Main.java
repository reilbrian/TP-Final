import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) throws IOException {
        String pronostico = "C:\\Users\\brian\\OneDrive\\Escritorio\\escuela\\Argentina Programa\\tp integrador\\untitledtp\\pronostico.csv";
        String resultados = "C:\\Users\\brian\\OneDrive\\Escritorio\\escuela\\Argentina Programa\\tp integrador\\untitledtp\\resultados.csv";
        System.out.println("Este es nuestro string path:" + resultados);
        System.out.println("Este es nuestro string path:" + pronostico);

          //       lectura de los resultados de los partidos
        String lineares; //string que lee los resultados uno por uno
        BufferedReader br = new BufferedReader(new FileReader(resultados));
        lineares = br.readLine();
        String[] nombresequipo = new String[100];
        int[] golesanotados = new int[100];
        int equiposcant = 0;

        for (int i = 0; (lineares = br.readLine()) != null; i++) {
            String[] posicion = lineares.split(";");
            nombresequipo[i] = posicion[0];
            golesanotados[i] = Integer.parseInt(posicion[1]);
            nombresequipo[i + 1] = posicion[2];
            golesanotados[i + 1] = Integer.parseInt(posicion[3]);
            i++;
            equiposcant = equiposcant + 2;
        }

        equipo equipos[] = new equipo[equiposcant];
        partido partidos[] = new partido[equiposcant];
        ronda ronda1 = new ronda();
        for (int i = 0; i < equiposcant; i++) {
            equipos[i] = new equipo(nombresequipo[i], i);
        }

         //formador de partidos
         int j = 0;
         for (int i = 0; i < (equiposcant / 2); i++) {
             partidos[i] = new partido(equipos[j], equipos[j + 1], golesanotados[j], golesanotados[j + 1]);
             ronda1.partidos.add(partidos[i]);
             j = j + 2;
         }

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
        pronostico[] pronosticos= new pronostico[proncant];
        for (int i = 0; i < proncant; i++) {
            pronosticos[i] = new pronostico
                    (equiposs[z],resultadospro[j], resultadospro[j + 1],resultadospro[j + 2],equiposs[z+1],nombres.get(i));
            System.out.println(pronosticos[i].getapuesta());
            j=j+3;z=z+2;
        }

        ronda1.ListarGanadores();
        ronda1.ListarGanadores();
        //detecta los pronosticos
        int[] puntaje = new int[10];
       j=0; z=0;String aux=pronosticos[0].getapostador();
      for (int i = 0; i <pronosticos.length; i++) {

          if(aux.equals(nombres.get(i))){}
          else {
              System.out.println(aux+" sumo :" + puntaje[j] + " puntos");
              j++;z=0;aux=pronosticos[i].getapostador();
          }

          if (partidos[z].getGanador().equalsIgnoreCase(pronosticos[i].getapuesta()))  {
            puntaje[j]++;
            System.out.println("aaaa");
      }
      z++;
    }
    System.out.println("sumaste :" + puntaje[1] + " puntos con tu pronostico "+aux);
    System.out.println(aux+" sumo :" + puntaje[j] + " puntos");
    }
}
