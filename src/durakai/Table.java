/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package durakai;

import java.util.ArrayList;

/**
 *
 * @author Linea
 */
public class Table {
    public ArrayList<Card> atacker;
    public ArrayList<Card> defender;

    public Table() {
        atacker=new ArrayList<>();
        defender=new ArrayList<>();
    }
    
    public void addAtacker(Card crd){
       atacker.add(crd);
    }

    public void addDefender(Card crd){
        defender.add(crd);
    }
    
    public void clearTable(){
        atacker=new ArrayList<>();
        defender=new ArrayList<>();
    }
    
    public ArrayList<Card> returnAllCard(){
        ArrayList<Card> lst= new ArrayList<>();
        lst.addAll(atacker);
        lst.addAll(defender);
        clearTable();
        return lst;
    }
    
}
