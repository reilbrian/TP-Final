public class Pronostico {
    public Pronostico(String equipo1,String gana1,String empate,String gana2,String equipo2,String apostador){
     this.equipo1 = equipo1;
     this.equipo2 = equipo2;
     this.empate = empate;
     this.apostador= apostador;
     this.gana1=gana1;
     this.gana2=gana2;
    }
    private String equipo1;
    
    private String empate;
    
    private String equipo2;
    
    private String apostador;
    
    private String gana1;
    
    private String gana2;
    
    public String getapostador(){return apostador; }
    
    public String getequipo1(){return equipo1;}
    
    public String getempate(){return empate; }
    
    public String getequipo2(){return equipo2; }
    

    public String getapuesta(){
     if (gana1.equals("x")){
      return equipo1;
     }
     if (gana2.equals("x")){
      return equipo2;
     }
     else{
      return "empate";
     }
    }
}