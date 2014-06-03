package BE;

import TableModels.ITableObserver;

import java.util.ArrayList;


/**
 *
 * @author Jakob
 */
public abstract class IBESubject {
     private ArrayList<ITableObserver> observers 
      = new ArrayList<>();

    
       public void addObserver(ITableObserver observer){
      observers.add(observer);		
   }
       public void remove(ITableObserver obersver){
           observers.remove(obersver);
       }

   public void notifyAllObservers(){
      for (ITableObserver observer : observers) {
         observer.update();
      }
}}
