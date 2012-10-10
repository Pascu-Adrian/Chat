/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proiectdistribuit;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author MindSlave
 */
public class CautareLogGUI extends JFrame {
    MainGUI mg;
    JScrollPane pane;
    JButton inchide = new JButton("STOP!");
    JTextArea text;
    JButton ascunde = new JButton("»");
    JButton afiseaza = new JButton("«");
    int spaceing=5;
    Toolkit toolkit =  Toolkit.getDefaultToolkit ();
    Anima a;
    int frameheight=200;
    int framewidth=250;
    CautaCrescatoareServer ccs;
    int f1,f2,f3,f4,t1,t2,t3,t4;

    public CautareLogGUI(MainGUI mg) {
        this.mg=mg;
        incarcaipuri();
        setTitle("LOG SERVER SEARCH");
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setAlwaysOnTop(true);
        setVisible(true);
        setBounds((int)toolkit.getScreenSize().getWidth()-framewidth-spaceing,
                (int)toolkit.getScreenSize().getHeight()-frameheight-(spaceing*10),
                framewidth,
                frameheight);
        make();
        
        ascunde.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                animaascundere();
                ascunde.setVisible(false);
                afiseaza.setVisible(true);
            }
        });
        afiseaza.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                animaafisare();
                afiseaza.setVisible(false);
                ascunde.setVisible(true);
            }
        });
        inchide.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                inchide();
            }
        });
    }

    void make(){
        afiseaza.setVisible(false);
        afiseaza.setBounds(0, 0, 35,frameheight );
        add(afiseaza);
        ascunde.setVisible(true);
        ascunde.setBounds(0, 0, 35,frameheight );
        add(ascunde);
        inchide.setVisible(true);
        inchide.setBounds(afiseaza.getWidth()+spaceing,frameheight-30-spaceing, framewidth-afiseaza.getWidth()-(spaceing*2), 30);
        add(inchide);
        text = new JTextArea();
        text.setVisible(true);
        text.setEditable(false);
        pane = new JScrollPane(text);
        pane.setVisible(true);
        pane.setBounds(afiseaza.getWidth()+spaceing, spaceing, 
                framewidth-afiseaza.getWidth()-(spaceing*2), frameheight-inchide.getHeight()-(spaceing*2));
        add(pane);
        ccs=new CautaCrescatoareServer(f1, f2, f3, f4, t1, t2, t3, t4, mg, this,true);
        ccs.start();
        mg.from3.setEnabled(false);
        mg.from4.setEnabled(false);
        mg.to3.setEnabled(false);
        mg.to4.setEnabled(false);
        mg.cauta.setEnabled(false);

    }

    void incarcaipuri(){
        f1=Integer.parseInt(String.valueOf(mg.from1.getValue()));
        f2=Integer.parseInt(String.valueOf(mg.from2.getValue()));
        f3=Integer.parseInt(String.valueOf(mg.from3.getValue()));
        f4=Integer.parseInt(String.valueOf(mg.from4.getValue()));
        t1=Integer.parseInt(String.valueOf(mg.to1.getValue()));
        t2=Integer.parseInt(String.valueOf(mg.to2.getValue()));
        t3=Integer.parseInt(String.valueOf(mg.to3.getValue()));
        t4=Integer.parseInt(String.valueOf(mg.to4.getValue()));
    }

    void adaugaText(String srt){
        text.setText(srt+"\n"+text.getText());

    }

    void inchide(){
        mg.from3.setEnabled(true);
        mg.from4.setEnabled(true);
        mg.to3.setEnabled(true);
        mg.to4.setEnabled(true);
        mg.cauta.setEnabled(true);
        ccs.inchidecautare();
        this.dispose();

    }

    void animaafisare(){

        a=new Anima(this, 1);
        a.start();
        

    }

    void animaascundere(){

        a=new Anima(this, 0);
        a.start();
        
    }

}
