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
    
    private Card makeStep(int cardNumer){
        Card tmp=cardInHand.get(cardNumer);
        cardInHand.remove(tmp);
        return tmp;
    }
    
    public ArrayList<Card> getCardInHandInfo(){
        return cardInHand;
    }
    
    public Card step(){
        Scanner read=new Scanner(System.in);
        String result=read.nextLine();
        if ("end".equals(result)) {
            isAtacker=false;
            return null;
        }
        return makeStep(Integer.parseInt(result));
    }
    

    
    
}
