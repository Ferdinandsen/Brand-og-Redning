/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TableModels;

import GUI.IObserver;
import java.util.ArrayList;


/**
 *
 * @author Shadowleet
 */
public interface Observerble  {
    
    public ArrayList<IObserver> observer = new ArrayList<>();
    public void addObserver(IObserver o);
    public void removeObserver(IObserver o);
    public void notifyObservers();
    
}
