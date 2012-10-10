/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proiectdistribuit;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author MindSlave
 */
public class Main implements Runnable{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//server serv=new server(9999, true);
        try {
    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
        }
    }
} catch (Exception e) {
    // If Nimbus is not available, you can set the GUI to another look and feel.
}
        

 


       
       
       
     MainGUI mg=new MainGUI();
        //ClientGUI cg=new ClientGUI();
    
      

//MainGUI mg=new MainGUI();
        //Tutorial t=new Tutorial();
        //t.start();
 


        //GUIserver gs=new GUIserver();

        //CautaServer cs=new CautaServer(192, 168, 1, 1, 192, 168, 1, 200);
        //cs.start();
       //PrivateGUI prv=new PrivateGUI("CU newa ion va rog");
        //PublicGUI pub=new PublicGUI("server necunoscut");
 
    }


    public void run() {
           
    }
}
