/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proiectdistribuit;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MindSlave
 */
public class Anima extends Thread {
    proiectdistribuit.MainGUI mg=null;
    proiectdistribuit.CautareLogGUI clg=null;
    proiectdistribuit.ServerLogGUI slg=null;
    int spaceing=5;
    int animationdelay=0;
    int animationamount=2;
    int x;


    public Anima() {
    }

    public Anima(proiectdistribuit.MainGUI mg) {
        this.mg=mg;
    }
    public Anima(proiectdistribuit.CautareLogGUI clg,int x) {
        this.clg=clg;
        this.x=x;     
    }
    public Anima(proiectdistribuit.ServerLogGUI slg,int x) {
        this.slg=slg;
        this.x=x;
    }

   

    @Override
    public void run() {

        if(mg!=null){
                animaMainGUI();
        }
        if(clg!=null){
            if(x==0){
            animaCautaLoginchide();
        }
                if(x==1){
                    animaCautaLogindeschide();
                }
        }
        if(slg!=null){
           if(x==0){
            animaServerLoginchide();
        }
                if(x==1){
                    animaServerLogindeschide();
                }
        }
    }

    void animaMainGUI(){

        while(mg.getSize().width<500||mg.getSize().height<500){
            if(mg.getSize().width<500){
                mg.setBounds(mg.getX(), mg.getY(), mg.getWidth()+animationamount, mg.getHeight());
            }

            if(mg.getSize().height<500){
                mg.setBounds(mg.getX(), mg.getY(), mg.getWidth(), mg.getHeight()+animationamount);
            }
            delay(animationdelay);
        }

        while(mg.connect.getX()<spaceing){
            mg.connect.setBounds(mg.connect.getX()+animationamount, mg.connect.getY(), mg.connect.getWidth(), mg.connect.getHeight());
            delay(animationdelay);
        }

        while(mg.host.getX()<mg.connect.getX()+mg.connect.getWidth()+spaceing){
            mg.host.setBounds(mg.host.getX()+animationamount, mg.host.getY(), mg.host.getWidth(), mg.host.getHeight());
            delay(animationdelay);
        }

        while(mg.reset.getX()<mg.host.getX()+mg.host.getWidth()+spaceing){
            mg.reset.setBounds(mg.reset.getX()+animationamount, mg.reset.getY(), mg.reset.getWidth(), mg.reset.getHeight());
            delay(animationdelay);
        }

        while(mg.panou.getX()<spaceing){
            mg.panou.setBounds(mg.panou.getX()+animationamount, mg.panou.getY(), mg.panou.getWidth(), mg.panou.getHeight());
            delay(animationdelay);
        }

        while(mg.from.getX()<spaceing){
            mg.from.setBounds(mg.from.getX()+animationamount, mg.from.getY(), mg.from.getWidth(), mg.from.getHeight());
            delay(animationdelay);
        }

        while(mg.from1.getX()<mg.from.getX()+mg.from.getWidth()+spaceing){
            mg.from1.setBounds(mg.from1.getX()+animationamount, mg.from1.getY(), mg.from1.getWidth(), mg.from1.getHeight());
            delay(animationdelay);
        }

        while(mg.from2.getX()<mg.from1.getX()+mg.from1.getWidth()+spaceing){
            mg.from2.setBounds(mg.from2.getX()+animationamount, mg.from2.getY(), mg.from2.getWidth(), mg.from2.getHeight());
            delay(animationdelay);
        }

        while(mg.from3.getX()<mg.from2.getX()+mg.from2.getWidth()+spaceing){
            mg.from3.setBounds(mg.from3.getX()+animationamount, mg.from3.getY(), mg.from3.getWidth(), mg.from3.getHeight());
            delay(animationdelay);
        }

        while(mg.from4.getX()<mg.from3.getX()+mg.from3.getWidth()+spaceing){
            mg.from4.setBounds(mg.from4.getX()+animationamount, mg.from4.getY(), mg.from4.getWidth(), mg.from4.getHeight());
            delay(animationdelay);
        }

        while(mg.to.getX()<spaceing){
            mg.to.setBounds(mg.to.getX()+animationamount, mg.to.getY(), mg.to.getWidth(), mg.to.getHeight());
            delay(animationdelay);
        }

        while(mg.to1.getX()<mg.to.getX()+mg.to.getWidth()+spaceing){
            mg.to1.setBounds(mg.to1.getX()+animationamount, mg.to1.getY(), mg.to1.getWidth(), mg.to1.getHeight());
            delay(animationdelay);
        }

        while(mg.to2.getX()<mg.to1.getX()+mg.to1.getWidth()+spaceing){
            mg.to2.setBounds(mg.to2.getX()+animationamount, mg.to2.getY(), mg.to2.getWidth(), mg.to2.getHeight());
            delay(animationdelay);
        }

        while(mg.to3.getX()<mg.to2.getX()+mg.to2.getWidth()+spaceing){
            mg.to3.setBounds(mg.to3.getX()+animationamount, mg.to3.getY(), mg.to3.getWidth(), mg.to3.getHeight());
            delay(animationdelay);
        }

        while(mg.to4.getX()<mg.to3.getX()+mg.to3.getWidth()+spaceing){
            mg.to4.setBounds(mg.to4.getX()+animationamount, mg.to4.getY(), mg.to4.getWidth(), mg.to4.getHeight());
            delay(animationdelay);
        }

        while(mg.cauta.getX()<spaceing){
            mg.cauta.setBounds(mg.cauta.getX()+animationamount, mg.cauta.getY(), mg.cauta.getWidth(), mg.cauta.getHeight());
            delay(animationdelay);
        }

        while(mg.atentie.getX()<spaceing){
            mg.atentie.setBounds(mg.atentie.getX()+animationamount, mg.atentie.getY(), mg.atentie.getWidth(), mg.atentie.getHeight());
            delay(animationdelay);
        }

        while(mg.durata.getX()<mg.atentie.getX()+mg.atentie.getWidth()+spaceing){
            mg.durata.setBounds(mg.durata.getX()+animationamount, mg.durata.getY(), mg.durata.getWidth(), mg.durata.getHeight());
            delay(animationdelay);
        }

        while(mg.secunde.getX()<mg.durata.getX()+mg.durata.getWidth()+spaceing){
            mg.secunde.setBounds(mg.secunde.getX()+animationamount, mg.secunde.getY(), mg.secunde.getWidth(), mg.secunde.getHeight());
            delay(animationdelay);
        }




        //se deschide prin marire si heigh si whide se inchide invers
        //toate componentele fly din stanga
    }


    void animaCautaLoginchide(){
        
            while(clg.getWidth()>clg.ascunde.getWidth()){
                clg.setBounds(clg.getX()+animationamount, clg.getY(), clg.getWidth()-animationamount, clg.getHeight());
                delay(animationdelay);
            }
    }

    void animaCautaLogindeschide(){
            while(clg.getWidth()<clg.framewidth){
                clg.setBounds(clg.getX()-animationamount, clg.getY(), clg.getWidth()+animationamount, clg.getHeight());
                delay(animationdelay);
        }
    }

    void animaServerLoginchide(){

            while(slg.getWidth()>slg.ascunde.getWidth()){
                slg.setBounds(slg.getX()+animationamount, slg.getY(), slg.getWidth()-animationamount, slg.getHeight());
                delay(animationdelay);
            }
    }

    void animaServerLogindeschide(){
            while(slg.getWidth()<slg.framewidth){
                slg.setBounds(slg.getX()-animationamount, slg.getY(), slg.getWidth()+animationamount, slg.getHeight());
                delay(animationdelay);
        }
    }

    void delay(int animationdelay1){
        try {
                sleep(animationdelay1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Anima.class.getName()).log(Level.SEVERE, null, ex);
            }
    }


}

