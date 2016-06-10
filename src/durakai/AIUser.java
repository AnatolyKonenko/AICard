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
public class AIUser extends User {

    public int makeBetterChoice() {
        return 0;
    }

    public ArrayList<Card> makeStep(Table table) {
        ArrayList<Card> toReturn = new ArrayList<>();
        HandWorker hw = new HandWorker();
        if (isAtacker) { // make atack
            if (table.atacker.isEmpty()) {
                Card cd;
                cd = hw.getMinCard(cardInHand);
                toReturn.add(cd);
                toReturn.addAll(hw.tossCard(cd, cardInHand));
                return toReturn;
            } else { //toss cards
                toReturn.clear();
                toReturn.addAll(hw.tossCard(table.returnAllCard(), cardInHand));
                return toReturn;
            }
        } else { //def
            toReturn = hw.getCardsToBeat(table.returnUnbeatenCards(), cardInHand);
            return toReturn;
        }

    }

    public Card makeStep() {
        HandWorker hw = new HandWorker();
        Card cd;
        if (isAtacker) {
            cd = hw.getMinCard(cardInHand);
            return cd;
        } else {

        }
        return null;
    }
}
