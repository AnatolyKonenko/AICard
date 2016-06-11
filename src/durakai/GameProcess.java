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
        deck = new Deck(true);
        userstep = true;
        for (int i = 0; i < 6; i++) {
            usr1.getCard(deck.getCard());
            ai1.getCard(deck.getCard());
        }
        runGame();
    }
    
    public void startGameWith() {
        table =new Table();
        usr1=new User();
        ai1=new AIUser();
        
    }

    private void runGame() {
        boolean u1, u2 = true;
        while ((usr1.getCardInHandInfo().size() > 0 && ai1.getCardInHandInfo().size() > 0)
                || deck.getSizeOfDeck() > 0) {
            table.clearTable();
            System.out.println("\n Table cleared \n");
            if (userstep) {
                do {
                    System.out.println("You attack");
                    u1 = U1Step();
                    u2 = AIStep();
                    printTable();
                    if (!u1&&!u2) {
                        if (!table.returnUnbeatenCards().isEmpty()) {
                            ai1.getCard(table.returnAllCard());
                            table.clearTable();
                            userstep = false;
                            System.out.println("AI takes cards");
                            break;
                        }
                    }
                } while (!table.returnUnbeatenCards().isEmpty() || u1);
            } else {
                do {
                    System.out.println("You defend");
                    u1 = AIStep();
                    printTable();
                    if (!table.returnUnbeatenCards().isEmpty()) u2 = U1Step();
                    else {
                        u2=true;
                        System.out.println("Card beaten");
                        break;
                    }
                    printTable();
                    if (!u2) {
                        if (!table.returnUnbeatenCards().isEmpty()) {
                            usr1.getCard(table.returnAllCard());
                            table.clearTable();
                            userstep = true;
                            System.out.println("You takes cards");
                            break;
                        }
                    }
                } while (!table.returnUnbeatenCards().isEmpty()||u1);
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
            System.out.println("In deck "+deck.getSizeOfDeck()+" card");
            userstep = !userstep;
        }
        if (ai1.getCardInHandInfo().isEmpty()&&usr1.getCardInHandInfo().isEmpty()) System.out.println("Withdraw");
        else if (ai1.getCardInHandInfo().isEmpty()) System.out.println("AI wins this game");
        else System.out.println("User wins the game");
    }

    private boolean U1Step() {
        printCardInfo(usr1.getCardInHandInfo(),"Your card is");
        ArrayList<Card> u1c;
        if (userstep) {
            u1c = usr1.step(table);
            if ((u1c == null||u1c.isEmpty())&&!table.tableIsEmpty()) {
                return false;
            }
            else while (u1c==null||u1c.isEmpty()) {
                u1c=usr1.step(table);
            }
            while (!CheckCorrectAtt(u1c)) {
                System.out.println("incorrect card!");
                u1c = usr1.step(table);
                usr1.getCard(u1c);
                if (u1c == null||u1c.isEmpty()) {
                    return false;
                }
            }
            table.atacker.addAll(u1c);

        } else {
            u1c = usr1.step(table);
            if (u1c == null) {
                return false;
            }
            while (!CheckCorrectDef(u1c)) {
                System.out.println("incorrect card!");
                usr1.getCard(u1c);
                u1c = usr1.step(table);
                if (u1c == null) {
                    return false;
                }
            }
            table.defender.addAll(u1c);

        }

        return true;
    }

    private void printCardInfo(ArrayList<Card> crd, String title) {
        System.out.println("");
        System.out.println(title);
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
        ArrayList<Card> aiS = new ArrayList<>();
    //    printCardInfo(ai1.getCardInHandInfo(), "AI card is");
        if (userstep) {
            ai1.setAtacker(!userstep);
            aiS = ai1.makeStep(table);
            if (aiS == null|| aiS.isEmpty()) {
                return false;
            }
            table.defender.addAll(aiS);
        } else {
            ai1.setAtacker(!userstep);
            aiS = ai1.makeStep(table);
            if (aiS.isEmpty()||aiS==null) {
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
