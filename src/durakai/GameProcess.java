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
public class GameProcess {

    Table table;
    User usr1;
    AIUser ai1;
    Deck deck;
    boolean userstep;

    public void startGame() {
        table = new Table();
        usr1 = new User();
        ai1 = new AIUser();
        deck = new Deck();
        userstep = true;
        for (int i = 0; i < 6; i++) {
            usr1.getCard(deck.getCard());
            ai1.getCard(deck.getCard());
        }
        runGame();
    }

    private void runGame() {
        boolean u1, u2 = true;
        while ((usr1.getCardInHandInfo().size() > 0 || ai1.getCardInHandInfo().size() > 0)
                && deck.getSizeOfDeck() > 0) {
            table.clearTable();
            if (userstep) {
                do {
                    System.out.println("\f");
                    System.out.println("You attack");
                    u1 = U1Step();
                    u2 = AIStep();
                    printTable();
                    if (!u2) {
                        if (!table.returnUnbeatenCards().isEmpty()) {
                            ai1.getCard(table.returnAllCard());
                            table.clearTable();
                            userstep = false;
                            break;
                        }
                    }
                } while (!table.tableIsEmpty() && u1 && u2);
            } else {
                do {
                    System.out.println("You defend");
                    u1 = AIStep();
                    printTable();
                    u2 = U1Step();
                    printTable();
                    if (!u2) {
                        if (!table.returnUnbeatenCards().isEmpty()) {
                            usr1.getCard(table.returnAllCard());
                            table.clearTable();
                            userstep = true;
                            break;
                        }
                    }
                } while (!table.tableIsEmpty() && u2 && u1);
            }

            if (userstep && usr1.getCountOfCard() < 6) {
                usr1.getCard(deck.getCard(6 - usr1.getCountOfCard()));
                if (ai1.getCountOfCard() < 6) {
                    ai1.getCard(deck.getCard(6 - ai1.getCountOfCard()));
                }
            } else {
                if (ai1.getCountOfCard() < 6) {
                    ai1.getCard(deck.getCard(6 - ai1.getCountOfCard()));
                }
                if (usr1.getCountOfCard() < 6) {
                    usr1.getCard(deck.getCard(6 - usr1.getCountOfCard()));
                }
            }
            userstep = !userstep;
        }
    }

    private boolean U1Step() {
        printCardInfo(usr1.getCardInHandInfo());
        ArrayList<Card> u1c;
        if (userstep) {
            u1c = usr1.step(table);
            if (u1c == null) {
                return false;
            }
            while (!CheckCorrectAtt(u1c)) {
                System.out.println("incorrect card!");
                u1c = usr1.step(table);
            }
            table.atacker.addAll(u1c);

        } else {
            u1c = usr1.step(table);
            if (u1c == null) {
                return false;
            }
            while (!CheckCorrectAtt(u1c)) {
                System.out.println("incorrect card!");
                u1c = usr1.step(table);
            }
            table.defender.addAll(u1c);

        }

        return true;
    }

    private void printCardInfo(ArrayList<Card> crd) {
        System.out.println("");
        System.out.println("Your Card is:");
        for (int i = 0; i < crd.size(); i++) {
            System.out.print(i + ")(");
            switch (crd.get(i).getCardSize()) {
                case 11:
                    System.out.print("V");
                    break;
                case 12:
                    System.out.print("D");
                    break;
                case 13:
                    System.out.print("K");
                    break;
                case 14:
                    System.out.print("A");
                    break;
                default:
                    System.out.print(crd.get(i).getCardSize());
            }
            System.out.print(";" + crd.get(i).getCardType() + ")");
            System.out.println("");
        }
        System.out.println("");
    }

    private boolean AIStep() {
        ArrayList<Card> aiS = new ArrayList<Card>();
        if (userstep) {
            ai1.setAtacker(!userstep);
            aiS = ai1.makeStep(table);
            if (aiS == null) {
                return false;
            }
            table.defender.addAll(aiS);
        } else {
            ai1.setAtacker(!userstep);
            aiS = ai1.makeStep(table);
            if (aiS == null) {
                return false;
            }
            table.atacker.addAll(aiS);
        }
        return true;
    }

    private boolean CheckCorrect() {
        return false;
    }

    private boolean CheckCorrectDef(ArrayList<Card> crds) {
        boolean correct = true;
        for (Card cd : crds) {
            if (!CheckCorrectDef(cd)) {
                correct = false;
            }
        }
        return correct;
    }

    private boolean CheckCorrectDef(Card crd) {
        CardType ct = new CardType();
        Card unb = table.returnUnbeatenCards().get(0);
        return (unb.getCardSize() < crd.getCardSize() && unb.getCardType().equals(crd.getCardType()))
                || (!unb.getCardType().equals(ct.Kozir) && crd.getCardType().equals(ct.Kozir));
    }

    private boolean CheckCorrectAtt(ArrayList<Card> crds) {
        boolean correct = true;
        for (Card cd : crds) {
            if (!CheckCorrectAtt(cd)) {
                correct = false;
            }
        }
        return correct;
    }

    private void printTable() {
        System.out.println("On table:");
        ArrayList<Card> cd = table.atacker;
        for (Card crd : cd) {
            printCardInfo(crd);
        }
        System.out.println("");
        cd = table.defender;
        for (Card crd : cd) {
            printCardInfo(crd);
        }
        System.out.println("");
    }

    private void printCardInfo(Card crd) {
        switch (crd.getCardSize()) {
            case 11:
                System.out.print("(V");
                break;
            case 12:
                System.out.print("(D");
                break;
            case 13:
                System.out.print("(K");
                break;
            case 14:
                System.out.print("(A");
                break;
            default:
                System.out.print("(" + crd.getCardSize());
        }
        System.out.print(";" + crd.getCardType() + ")");
    }

    private boolean CheckCorrectAtt(Card crd) {
        if (table.tableIsEmpty()) {
            return true;
        }
        return table.returnCardNumbers().contains(crd.getCardSize());
    }
}
