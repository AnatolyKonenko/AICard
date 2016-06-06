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
public class Deck {
    private ArrayList<Card> deck;
    private ArrayList<Card> sortedDeck;
    
    Deck(){
        deck=new ArrayList<>();
        sortedDeck=new ArrayList<>();
        CardType ct=new CardType();
        for (int i=6;i<=14;i++){
            sortedDeck.add(new Card(i,ct.Bubna));
            sortedDeck.add(new Card(i,ct.Cherva));
            sortedDeck.add(new Card(i,ct.Trefa));
            sortedDeck.add(new Card(i,ct.Pika));
        }
        generateDeck();
    }
    
    private void generateDeck() {
        int numberOfCard=(int) (Math.random() * sortedDeck.size());
            while (sortedDeck.size()>0){
                deck.add(sortedDeck.get(numberOfCard));
                sortedDeck.remove(numberOfCard);
                numberOfCard=(int) (Math.random()*sortedDeck.size());
            }
   }
    
   public Card getCard(){
       Card toReturn=deck.get(0);
       deck.remove(0);
       return toReturn;
   }
   
   public ArrayList<Card> getCard(int countOfCards){
       ArrayList<Card> toReturn=(ArrayList<Card>) deck.subList(0, countOfCards);
       deck.removeAll(toReturn);
       return toReturn;
   }
   
   public int getSizeOfDeck(){
       return deck.size();
   }
   
   
}
