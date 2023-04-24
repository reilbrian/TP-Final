import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        //conexion con la db
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp", "root", "ratchet124");
        Statement stmt = con.createStatement();

        //Lectura de la tabla resultados

        String[] nombresequipo = new String[100];
        int[] golesanotados = new int[100];
        int[] rondanum= new int[100];
        int equiposcant = 0;
        int rondacant = 0;
        int j=0;

        ResultSet rs=stmt.executeQuery("SELECT * FROM resultados");
        for (int i = 0; rs.next(); i++) {

            rondanum[j]= rs.getInt(2);
            nombresequipo[i] = rs.getString(3);
            golesanotados[i] = rs.getInt(4);
            nombresequipo[i + 1] = rs.getString(5);
            golesanotados[i + 1] = rs.getInt(6);
            rondacant=rondanum[i];
            i++;j++;
            equiposcant = equiposcant + 2;
        }
        // lectura del pronostico de los partidos

        ResultSet rs1=stmt.executeQuery("SELECT * FROM pronosticos");
        String[] resultadospro = new String[100];
        String[] equiposs=new String[100];
        ArrayList<String> nombres=new ArrayList<String>();

        int proncant = 0;
        j=0;
        for (int i = 0;rs1.next(); i++) {

            equiposs[j]=rs1.getString(2);
            resultadospro[i] = rs1.getString(3);
            resultadospro[i + 1] = rs1.getString(4);
            resultadospro[i + 2] = rs1.getString(5);
            equiposs[j+1]=rs1.getString(6);
            nombres.add(rs1.getString(7));
            i = i + 2;
            j=j+2;
            proncant++;
        }
        con.close();

        //formador de equipos
        Equipo equipos[] = new Equipo[equiposcant];
        ArrayList<Partido> partidoss = new ArrayList<Partido>();
        ArrayList<Ronda> rondas = new ArrayList<Ronda>();
        Ronda ronda1 = new Ronda ();
        Ronda ronda2 = new Ronda ();

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

        j=0;int z=0;
        Pronostico[] pronosticos = new Pronostico[proncant];
        for (int i = 0; i < proncant; i++) {
            pronosticos[i] = new Pronostico
                    (equiposs[z],resultadospro[j], resultadospro[j + 1],resultadospro[j + 2],equiposs[z+1],nombres.get(i));
            System.out.println(pronosticos[i].getapuesta()+"  "+pronosticos[i].getapostador());
            j=j+3;z=z+2;
        }

        rondas.get(0).ListarGanadores();
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
        //suma puntaje extra
        /*
        j=0; z=0; aux= pronosticos[0].getapostador();aux1=rondanum[0];boolean addpoint= true;
        for (int i = 0; i < pronosticos.length; i++) {

            if(aux.equals(nombres.get(i))){}
            else {
                j++;z=0;aux= pronosticos[i].getapostador();
            }
            if(aux1!=rondanum[z]){
                if(addpoint){
                    puntaje[j]=puntaje[j]+1;
                }
            }
            if ((partidoss.get(z).getGanador()).equalsIgnoreCase(pronosticos[i].getapuesta()))  {}
            else{ addpoint=false; }
            z++;
        }
        for (int i=0 ;i<2;i++) {System.out.println(aux+" sumo :" + puntaje[i] + " puntos");}*/

        System.out.println(aux+" sumo :" + puntaje[j] + " puntos");

    }
}