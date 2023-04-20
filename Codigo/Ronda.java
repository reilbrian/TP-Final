import java.util.ArrayList;
public class ronda{

    public ArrayList<partido> partidos = new ArrayList<partido>();
    public void ListarGanadores()
    {
        for (int i = 0; i < partidos.size() ; i++) {
            System.out.println("el ganador del partido fue :"+partidos.get(i).getGanador());
        }
        System.out.println("--------------------------------");
    }
}