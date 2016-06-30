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

    public Card getMinGreaterCard(ArrayList<Card> deck, Card cardToBeat) {
        Card selectedCard;
        selectedCard = new Card(15, "");

        for (Card cd : deck) {
            if (cardToBeat.getCardType().equals(CardType.Trump)) {
                if (cd.getCardType().equals(CardType.Trump) && cd.getCardSize() > cardToBeat.getCardSize()
                        && cd.getCardSize() < selectedCard.getCardSize()) {
                    selectedCard = cd;
                }
            } else if (selectedCard.getCardType().equals("")) {
                if (cd.getCardType().equals(CardType.Trump)) {
                    selectedCard = cd;
                } else if (cd.getCardType().equals(cardToBeat.getCardType())
                        && cd.getCardSize() > cardToBeat.getCardSize()) {
                    selectedCard = cd;
                }
            } else {
                if (selectedCard.getCardType().equals(CardType.Trump)) {
                    if (cd.getCardType().equals(CardType.Trump) && cd.getCardSize() < selectedCard.getCardSize()) {
                        selectedCard = cd;
                    }
                    if (cd.getCardType().equals(cardToBeat.getCardType()) && cd.getCardSize() >= cardToBeat.getCardSize()) {
                        selectedCard = cd;
                    }
                }
                if (!selectedCard.getCardType().equals(CardType.Trump)) {
                    if (cd.getCardType().equals(cardToBeat.getCardType())
                            && cd.getCardSize() >= cardToBeat.getCardSize()
                            && cd.getCardSize() <= selectedCard.getCardSize()) {
                        selectedCard = cd;
                    }
                }

            }
        }
        if (!selectedCard.getCardType().equals("")) {
            return selectedCard;
        } else {
            return null;
        }

    }

    public Card getMinCard(ArrayList<Card> deck) {
        Card selectedCard = deck.get(0);
        for (Card cd : deck) {
            if (selectedCard.getCardType().equals(CardType.Trump)) {
                if (!cd.getCardType().equals(CardType.Trump)) {
                    selectedCard = cd;
                }
            } else if (!cd.getCardType().equals(CardType.Trump) && cd.getCardSize() <= selectedCard.getCardSize()) {
                selectedCard = cd;
            }

        }
        return selectedCard;
    }

    public ArrayList<Card> getCardsToBeat(ArrayList<Card> cardToBeat, ArrayList<Card> cardInHand) {
        ArrayList<Card> toReturn = new ArrayList<>();
        for (Card cd: cardToBeat) {
            toReturn.add(getMinGreaterCard(cardInHand, cd));
        }
        if (toReturn.contains(null)) {
            return null;
        } else {
            return toReturn;
        }
    }

    public ArrayList<Card> tossCard(Card cd, ArrayList<Card> cardInHand) {
        ArrayList<Card> toReturn = new ArrayList<>();
        for (Card card:cardInHand) {
            if (cd.getCardSize() == card.getCardSize()) {
                toReturn.add(card);
            }
        }
        return toReturn;
    }

    public ArrayList<Card> tossCard(ArrayList<Card> cardOnTable, ArrayList<Card> cardInHand) {
        ArrayList<Card> toReturn = new ArrayList<>();
        Set<Integer> tc = new HashSet<>();
        for (Card card:cardOnTable) {
            tc.add(card.getCardSize());
        }
        for (Card card:cardInHand) {
            if (tc.contains(card.getCardSize())) {
                toReturn.add(card);
            }
        }
        return toReturn;
    }
}
