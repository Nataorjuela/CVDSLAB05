
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author natao
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import java.util.*;

@ManagedBean(name = "calculadoraBean")
@ApplicationScoped
public class megaStat {
    public ArrayList<Float> datos=new ArrayList <Float>();
    public float media;
    public float moda;
    public float desvEst;
    public float varianza;
    

    public megaStat (){}
    
    public float calculateMean(ArrayList<Float>datos){
    float sumatoria=0;
    for (int i=0;i<datos.size();i++){ 
        sumatoria+=datos.get(i);
    }
    media=sumatoria/datos.size();
    return media;
    }
    public float calculateStandardDeviation(ArrayList<Float>datos){
    desvEst=(float)Math.sqrt(getVarianza());
    return desvEst;
    }
    public float  calculateVariance(ArrayList<Float>dato){
    float sumatoria=0;
    for (int i=0;i<datos.size();i++){ 
       sumatoria+=Math.pow(2,datos.get(i)-getMedia());
    }
    varianza=sumatoria/datos.size();
    return varianza;
    }
    
    public float calculateMode(ArrayList<Float>dato){
        float moda=0;
        int conta=0;
        int mayor=0;
        float [] d=getDato();
        d= ordenarArray(d);
        for (int i=0;i<d.length;i++){
            if (i>0 && d[i-1]==d[i]){conta+=1;}
            if (i>0 && d[i-1]!=d[i]){conta=0;}
            if (conta>mayor){moda=d[i];}
        }
        return moda;
    }
    public static float[] ordenarArray(float[] n) {
        float aux;
        for (int i = 0; i < n.length - 1; i++) {
            for (int x = i + 1; x < n.length; x++) {
                if (n[x] < n[i]) {
                    aux = n[i];
                    n[i] = n[x];
                    n[x] = aux;
                }
            }
        }
    return n;
    }
    
    public void restart(){
        datos=new ArrayList<Float>();
        media=0;
        moda=0;
        desvEst=0;
        varianza=0;
    }

    public float[] getDato(){
      int n = datos.size();
      float [] vector= new float[n];
      for (int x=0;x<n;x++){ vector[x]=datos.get(x);}
      return vector;
    }
    
    public float getMedia(){
    return media;
    }
    
    public float getModa(){
    return moda;
    }
    
    public float getDesvEst(){
    return desvEst;
    }
    
    public float getVarianza(){
    return varianza;
    }
    
    public int getCantDato(){
      int n = datos.size();
    return n;
    }
    
    public void setDatos(ArrayList<Float> datos){
    this.datos=datos;
    }  
}
