package MetodoNewtonRaphson;

import com.singularsys.jep.JepException;
import javax.swing.JOptionPane;
import org.nfunk.jep.JEP;

//Desarrlloar un problema que encuentre raices mediante el metodo de biseccion 
public class MetodoNR{
    
    //declaramos las variables que vamos a utilizar
    JEP jep;
    
    JEP jep2;
    //la funcion que va a recibir
    private String entrada="";
     private String derivada="";
    private double resultado = 0.0; 
    private String error;
    private double errorOperacion;
    private double error1;
    private double error1Anterior;
   
    
    //variables para el metodo de falsa posicion
    private double xi =0.0; //valor inicial
    private double Fxi; //evaluacion del valor inicial en la funcion
    private double Fderivadaxi; //evaluacion del valor inicial en la funcion derivada
    private double Xr;//almacenar el valor
    private double XrAnterior;
    private int iterar;

public MetodoNR(){
        
    }


//recibe los valores del valor inicial
    public void setIntervaloA(double xi){
        this.xi= xi;
    }
    
    
    
    public void setFuncion(String entrada){
        this.entrada = entrada;
    }
    
    public void setDerivadaFuncion(String derivada){
        this.derivada = derivada;
    }

    
    public double getResultadoFuncion(){

        return this.resultado;
    }
    
    public String getError(){
        return this.error;
    }
    
    
    public double getErrorOperacion(){
        return this.errorOperacion;
    }
    
    

 
    public void evaluar() throws JepException{
    jep = new JEP();
    jep2 = new JEP();
    
    //añade los valores de pi, euler, funciones trigonometricas, etc.
    System.out.println("El valor inicial es: " + xi);
        this.jep.addStandardFunctions();
        this.jep.addStandardConstants();
        
        this.jep2.addStandardFunctions();
        this.jep2.addStandardConstants();
        
     //obtiene la funcion digitada por el usuario
        this.jep.parseExpression(this.entrada);

        
        //obtiene la funcion dijitada por el usuario en la derivada
                
        this.jep2.parseExpression(this.derivada);
        
        
        
        
        // evaluamos el valor inicial en la funcion 
        this.jep.addVariable("x", this.xi);
        Fxi= this.jep.getValue();
        
        // evaluamos el valor inicial en la funcion derivada
        this.jep2.addVariable("x", this.xi);
        Fderivadaxi= this.jep2.getValue();
        
        Xr=xi-(Fxi/Fderivadaxi);
        
        iterar=Integer.parseInt(JOptionPane.showInputDialog("Cuantas veces deceas iterar la funcion"));
        
        
        error1Anterior=Xr;
        
        
        
        System.out.println("La funcion evaluada es" + Fxi);
        System.out.println("La funcion derivada es "+Fderivadaxi);
        System.out.println("el resultado es: " + Xr);
        System.out.println("El error es 0");
        //bucle con el numero de iteraciones
        for(int i=0;i<iterar;i++){
        // evaluamos el valor inicial en la funcion 
        this.jep.addVariable("x", Xr);
        Fxi= this.jep.getValue();
        
        // evaluamos el valor inicial en la funcion derivada
        this.jep2.addVariable("x", Xr);
        Fderivadaxi= this.jep2.getValue();
        
        Xr=Xr-(Fxi/Fderivadaxi);
            
        error1=(Xr-error1Anterior)/Xr*100;    
         error1Anterior=error1;
         
         System.out.println("el resultado es: " + Xr);
        System.out.println("El error es: " + error1);
        }
        
        
        
        //llamar resultados
        this.errorOperacion=error1;
             this.resultado = Xr;
             
             
             //captura el error
        this.error = (this.jep.hasError())? "Existe un error, revisa tus entradas de datos.":"Solucion exitosa.";
    }

            
          
}
