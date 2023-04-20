public class Partido {
    public Partido(equipo equipo1, equipo equipo2, int goles1, int goles2) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.goles1 = goles1;
        this.goles2 = goles2;
    }

    private equipo equipo1;
    private equipo equipo2;
    private int goles1;
    private int goles2;


    public int getGoles1() {return goles1;} public int getGoles2() {return goles2;}


    public String getGanador() {
        String empate ="empate";
        if (goles1 > goles2) {
           return equipo1.getNombre();

        }
        if (goles2 > goles1){
            return equipo2.getNombre();
        }
        else{
            return empate;
        }
    }
}