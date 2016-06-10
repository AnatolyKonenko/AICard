/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package durakai;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Linea
 */
public class User {
    private String name;
    protected boolean isAI;
    protected boolean isAtacker;
    protected ArrayList<Card> cardInHand;


    User(){
        name="Nm";
        isAI=true;
        isAtacker=true;
        cardInHand=new ArrayList<>();
    }

    public User(String name, boolean isAI) {
        this.name = name;
        this.isAI = isAI;
        cardInHand=new ArrayList<>();
    }
    
    public void getCard(Card card){
        cardInHand.add(card);
    }
    
    public void getCard(ArrayList<Card> cards){
        cardInHand.addAll(cards);
    }
    
    public int getCountOfCard(){
        return cardInHand.size();
    }
    
    public void switchRole(){
        isAtacker=!isAtacker;
    }
    
    private Card makeStep(int cardNumber){
        if (cardNumber>=cardInHand.size()) return null;
        Card tmp=cardInHand.get(cardNumber);
        cardInHand.remove(tmp);
        return tmp;
    }
    
    public ArrayList<Card> getCardInHandInfo(){
        return cardInHand;
    }
    
    public ArrayList<Card> step(Table table){
        ArrayList<Card> toReturn=new ArrayList<>();
        Scanner read=new Scanner(System.in);
        String result=read.nextLine();
        if ("end".equals(result)) {
            isAtacker=!isAtacker;
            return null;
        }
        String[] sp=result.split(" ");
        Card cd;
        for (String sp1 : sp) {
            cd=makeStep(Integer.parseInt(sp1));
            if (cd!=null) toReturn.add(cd);
        }
        return toReturn;
    }
    
    public void setAtacker(boolean atacker){
        this.isAtacker=atacker;
    }
    
    
}
