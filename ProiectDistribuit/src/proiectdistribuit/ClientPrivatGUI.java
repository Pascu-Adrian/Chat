/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proiectdistribuit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author MindSlave
 */
public class ClientPrivatGUI extends JFrame {
    JScrollPane panou;
    JTextArea content=new JTextArea();
    JTextField input=new JTextField();
    JButton send=new JButton("SEND");
    ClientGUI cg;
    public String nick;



    public ClientPrivatGUI(final ClientGUI cg,final String nick) {
        this.cg=cg;
        this.nick=nick;
        setTitle("Contact privat:"+nick);
        setVisible(true);
        setBounds(200, 200, 500, 500);
        setLayout(null);
        setResizable(true);

        make();


        addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent ev) {
                inchideprivat();
            }
        });
        send.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                trimite();
                    adaugatext(cg.nick+": "+input.getText());
                input.setText("");
            }
        });
        input.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {

            }

            public void keyPressed(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==10){
                    trimite();
                    adaugatext(cg.nick+": "+input.getText());
                input.setText("");
                }
            }
        });
    }

     public void make(){

        content=new JTextArea();
        content.setVisible(true);
        content.setEditable(false);
        panou=new JScrollPane(content);
        panou.setVisible(true);
        panou.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panou.setBounds(10, 10, 200, 200);
        add(panou);

        input.setVisible(true);
        input.setBounds(10, 220, 200, 100);
        add(input);

        send.setVisible(true);
        send.setBounds(220, 220, 100, 100);
        add(send);

    }

    void adaugatext(String msg){

        content.setText(msg+"\n"+content.getText());
    }

    void trimite(){
        cg.trimitePRV(nick+"«"+input.getText());
    }
    void inchideprivat(){
        cg.inchideprivat(this);
    }

}
