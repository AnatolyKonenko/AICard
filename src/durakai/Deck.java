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

    Deck(boolean useTrump) {
        if (!useTrump) {
            createEmpty();
            generateDeck();
        }
        else {
            
            createEmpty();
            int randomState = (int) (Math.random()*4);
            switch (randomState) {
                case 0:
                    generateDeckWithTrump(CardType.Bubna);
                    break;
                case 1:
                    generateDeckWithTrump(CardType.Cherva);
                    break;
                case 2:
                    generateDeckWithTrump(CardType.Pika);
                    break;
                case 3:
                    generateDeckWithTrump(CardType.Trefa);
                    break;
            }
        }
    }
    
    private void createEmpty(){
        deck = new ArrayList<>();
        sortedDeck = new ArrayList<>();
        for (int i = 6; i <= 14; i++) {
            sortedDeck.add(new Card(i, CardType.Bubna));
            sortedDeck.add(new Card(i, CardType.Cherva));
            sortedDeck.add(new Card(i, CardType.Trefa));
            sortedDeck.add(new Card(i, CardType.Pika));
        }
        
    }
    
    private void generateDeck() {
        int numberOfCard = (int) (Math.random() * sortedDeck.size());
        while (sortedDeck.size() > 0) {
            deck.add(sortedDeck.get(numberOfCard));
            sortedDeck.remove(numberOfCard);
            numberOfCard = (int) (Math.random() * sortedDeck.size());
        }
    }

    private void generateDeckWithTrump(String toReplace) {
        Card crd;
        int numberOfCard = (int) (Math.random() * sortedDeck.size());
        while (sortedDeck.size() > 0) {
            crd = sortedDeck.get(numberOfCard);
            if (crd.getCardType().equals(toReplace)) {
                deck.add(new Card(crd.getCardSize(),CardType.Trump));
            } else deck.add(crd);
            sortedDeck.remove(numberOfCard);
            numberOfCard = (int) (Math.random() * sortedDeck.size());
        }
    }

    public Card getCard() {
        Card toReturn = deck.get(0);
        deck.remove(0);
        return toReturn;
    }

    public ArrayList<Card> getCard(int countOfCards) {
        ArrayList<Card> toReturn = null;
        if (countOfCards < deck.size()) {
            toReturn = new ArrayList<Card>(deck.subList(0, countOfCards));
        } else {
            toReturn = new ArrayList<Card>(deck);
        }
        deck.removeAll(toReturn);
        return toReturn;
    }

    public int getSizeOfDeck() {
        return deck.size();
    }

}
