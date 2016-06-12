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
public class DurakAI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //    GameProcess gp=new GameProcess();
        //    gp.startGame();

        AIUser ai1 = new AIUser();
        ai1.isAtacker = false;
        Deck dck = new Deck(true);
        Table tbl = new Table();
        ArrayList<Card> res = new ArrayList<Card>();
        long estimatedtime = 0;
        while (res != null) {
            tbl.atacker.addAll(dck.getCard(1));
            ai1.getCard(dck.getCard(2));

            long startTime = System.nanoTime();
            res = ai1.makeStep(tbl);

            estimatedtime = System.nanoTime() - startTime;
        }
        System.out.println(estimatedtime);
        if (res == null) {
            System.out.println("oops");
        } else {
            for (Card cd : res) {
                System.out.println(cd.getCardSize() + ";" + cd.getCardType());
            }
        }
    }

}
