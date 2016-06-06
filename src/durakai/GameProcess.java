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
    
    
    public void startGame(){
        table=new Table();
        usr1=new User();
        ai1=new AIUser();
        deck=new Deck();
        for (int i=0;i<6;i++){
            usr1.getCard(deck.getCard());
            ai1.getCard(deck.getCard());
        }
        runGame();
    }
    
    private void runGame(){
        boolean userstep=true;
        while ((usr1.getCardInHandInfo().size()>0||ai1.getCardInHandInfo().size()>0)
                &&deck.getSizeOfDeck()>0){
            if (userstep) U1Step();
            else AIStep();
            if (userstep&&usr1.getCountOfCard()<6) {
                usr1.getCard(deck.getCard(6-usr1.getCountOfCard()));
                if (ai1.getCountOfCard()<6) 
                    ai1.getCard(deck.getCard(6-ai1.getCountOfCard()));
            }
        }
    }
    
    private void U1Step(){
        printCardInfo(usr1.getCardInHandInfo());
        Card u1c;
        u1c=usr1.step();
        if (u1c==null) return;
        else {
            if (CheckCorrectAtt(u1c)){
                table.atacker.add(u1c);
                AIStep();
                U1Step();
            }
        }
        
    }
    
    private void printCardInfo(ArrayList<Card> crd){
        for (int i=0;i<crd.size();i++){
            System.err.println(i+")"+crd.get(i).getCardSize()+";"+crd.get(i).getCardSize());
        }
    }
    
    private void AIStep(){
        
    }
    
    
    private boolean CheckCorrect(){
        return false;
    }
    
    private boolean CheckCorrectDef(Card crd){
        CardType ct=new CardType();
        Card unb=table.returnUnbeatenCards().get(0);
        return (unb.getCardSize()<crd.getCardSize()&&unb.getCardType().equals(crd.getCardType()))||
                (!unb.getCardType().equals(ct.Kozir)&&crd.getCardType().equals(ct.Kozir));
    }
    
    private boolean CheckCorrectAtt(Card crd){
        return table.returnCardNumbers().contains(crd.getCardSize());
    }
}
