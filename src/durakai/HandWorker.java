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
public class HandWorker {
    public ArrayList<Card> getCardByType(ArrayList<Card> deck,String cardType){
       ArrayList<Card> tmpHand=new ArrayList<>();
       Card tmpCard;
       CardType ct=new CardType();
        for (int i=0;i<deck.size();i++){
            tmpCard=deck.get(i);
            if (tmpCard.getCardType().equals(cardType)||
                    tmpCard.getCardType().equals(ct.Kozir))
                tmpHand.add(tmpCard);
        }
        return tmpHand;
    }
        
    public Card getMinGreaterCard(ArrayList<Card> deck,int cardSize){
        Card selectedCard;
        selectedCard= new Card(15,"");
        for (int i=0;i<deck.size();i++){
            if (deck.get(i).getCardSize()>=cardSize&&selectedCard.getCardSize()>deck.get(i).getCardSize()) {
                selectedCard=deck.get(i);
                
            }
        }
        return selectedCard;
    }  
    
    public Card getMinCard(ArrayList<Card> deck){
        CardType ct=new CardType();
        Card selectedCard=deck.get(0);
        for (int i=0;i<deck.size();i++){
            if ((!deck.get(i).getCardType().equals(ct.Kozir)&&deck.get(i).getCardSize()<selectedCard.getCardSize())||
                    (selectedCard.getCardType().equals(ct.Kozir)&&deck.get(i).getCardSize()<selectedCard.getCardSize()))
                    selectedCard=deck.get(i);
        }
        return selectedCard;
    }
}
