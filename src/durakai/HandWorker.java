/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package durakai;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Linea
 */
public class HandWorker {
    public ArrayList<Card> getCardByType(ArrayList<Card> deck,Card card){
       ArrayList<Card> tmpHand=new ArrayList<>();
       Card tmpCard;
       CardType ct=new CardType();
        for (int i=0;i<deck.size();i++){
            tmpCard=deck.get(i);
            if (tmpCard.getCardType().equals(card.getCardType())||
                    tmpCard.getCardType().equals(ct.Kozir))
                tmpHand.add(tmpCard);
        }
        return tmpHand;
    }
        
    public Card getMinGreaterCard(ArrayList<Card> deck,Card card){
        Card selectedCard;
        CardType ct=new CardType();
        selectedCard= new Card(15,"");
        for (int i=0;i<deck.size();i++){
            if (selectedCard.getCardType().equals(ct.Kozir))
            if ((deck.get(i).getCardSize()>=card.getCardSize()&&selectedCard.getCardSize()>deck.get(i).getCardSize())&&(
                    deck.get(i).getCardType().equals(ct.Kozir)||deck.get(i).getCardType().equals(card.getCardType()))) {
                selectedCard=deck.get(i);
                
            }
        }
        if (!selectedCard.getCardType().equals("")) return selectedCard;
        else return null;
    }  
    
    public Card getMinCard(ArrayList<Card> deck){
        CardType ct=new CardType();
        Card selectedCard=deck.get(0);
        for (int i=0;i<deck.size();i++){
            if ((!deck.get(i).getCardType().equals(ct.Kozir)&&deck.get(i).getCardSize()<=selectedCard.getCardSize())||
                    (selectedCard.getCardType().equals(ct.Kozir)&&deck.get(i).getCardSize()<selectedCard.getCardSize()))
                    selectedCard=deck.get(i);
        }
        return selectedCard;
    }
    
    public ArrayList<Card> getCardsToBeat(ArrayList<Card> cardToBeat, ArrayList<Card> cardInHand){
        ArrayList<Card> toReturn=new ArrayList<>();
        for (int i=0;i<cardToBeat.size();i++){
            toReturn.add(getMinGreaterCard(cardInHand, cardToBeat.get(i)));
        }
        if (toReturn.contains(null)) return null;
        else return toReturn;
    }
    
    public ArrayList<Card> tossCard(Card cd,ArrayList<Card> cardInHand){
        ArrayList<Card> toReturn= new ArrayList<>();
        for (int i=0;i<cardInHand.size();i++){
            if (cd.getCardSize()==cardInHand.get(i).getCardSize()) toReturn.add(cardInHand.get(i));
        }
        return toReturn;
    }
    
    public ArrayList<Card> tossCard(ArrayList<Card> cardOnTable, ArrayList<Card> cardInHand){
        ArrayList<Card> toReturn= new ArrayList<>();
        Set<Integer> tc = new HashSet<>();
        for (int i=0;i<cardOnTable.size();i++){
            tc.add(cardOnTable.get(i).getCardSize());
        }
        for (int i=0;i<cardInHand.size();i++){
            if (tc.contains(cardInHand.get(i).getCardSize())) toReturn.add(cardInHand.get(i));
        }
        return toReturn;
    }
}
