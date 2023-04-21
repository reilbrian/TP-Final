package org.example;
import java.util.ArrayList;

public class Ronda{

    public ArrayList<Partido> partidos = new ArrayList<Partido>();
    public void ListarGanadores()
    {
        for (int i = 0; i < partidos.size() ; i++) {
            System.out.println("el ganador del partido fue :"+ partidos.get(i).getGanador());
        }
        System.out.println("--------------------------------");
    }

}
