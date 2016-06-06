/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package durakai;

/**
 *
 * @author Linea
 */
public class Card {
    private int cardSize;
    private String cardType;
    
    Card (int cardSize, String cardType){
        this.cardSize=cardSize;
        this.cardType=cardType;
    }
    
    public Card getCardInfo(){
        return this;
    }
    
    public String getCardType(){
        return cardType;
    }
    
    public int getCardSize(){
        return cardSize;
    }
    
}
