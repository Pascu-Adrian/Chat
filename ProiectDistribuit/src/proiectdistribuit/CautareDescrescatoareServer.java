/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proiectdistribuit;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MindSlave
 */
public class CautareDescrescatoareServer extends Thread{
    
     MainGUI mg;
    CautareLogGUI clg;
    boolean cauta=false;
    int f1,f2,f3,f4,t1,t2,t3,t4,c,d,m;
    InetAddress ia=null;
    Vector<String> VectorObiectServer=new Vector<String>();
        int timeout=1100;
        public Socket socket=new Socket();

    public CautareDescrescatoareServer() {
    }



    public CautareDescrescatoareServer(int f1, int f2, int f3, int f4, int t1, int t2, int t3, int t4, MainGUI mg, CautareLogGUI clg) {
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
        this.f4 = f4;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.mg = mg;
        this.clg = clg;
    }




    @Override
    public void run() {
while(cauta){
		c=f3;
		d=f4;
        if(c==t3){
            for(int i=t4;i>=d;i--)
            {
            if(cauta){
                try {
                                ia=InetAddress.getByName(f1 + "." + f2 + "." + c + "." + i);
                                adaugainmonitor("SEARCHING IP: "+f1 + "." + f2 + "." + c + "." + i);
                                if(ia.isReachable(timeout)){

                                    adaugainmonitor("ADDRESS OK!");
                                 if(cauta){
                                 adaugainmonitor("TESTING PORT: "+9999);
                               socket = new Socket(ia, 9999);

                                if(socket.isBound()){
                                    adaugainmonitor("PORT OK!");
                                    adaugaserver(socket);
                                }
                                    }

                                    


                                }


                                 else{
                                    adaugainmonitor("SHIT IP ADDRESS: "+f1 + "." + f2 + "." + c + "." + i);
                                    VectorObiectServer=new Vector<String>();
                                    VectorObiectServer.add(ia.getHostName());
                                    VectorObiectServer.add(ia.getHostAddress());
                                    VectorObiectServer.add(String.valueOf(9999));
                                    stergeserver(VectorObiectServer);;
                                   
                                    }


                            } catch (UnknownHostException ex) {
                                continue;
                            } catch (IOException ex) {
                                   adaugainmonitor("SHIT PORT: "+9999);
                                    VectorObiectServer=new Vector<String>();
                                    VectorObiectServer.add(ia.getHostName());
                                    VectorObiectServer.add(ia.getHostAddress());
                                    VectorObiectServer.add(String.valueOf(9999));
                                    stergeserver(VectorObiectServer);
                                continue;
                            }
                }


            else{
                break;
            }

            }
        }

        else{
               while(c<t3){
               for(int i=254;i>=d;i--)
            {
                if(cauta){
                try {

                                ia=InetAddress.getByName(f1 + "." + f2 + "." + c + "." + i);
                                adaugainmonitor("SEARCHING IP: "+f1 + "." + f2 + "." + c + "." + i);
                                if(ia.isReachable(timeout)){
                                    adaugainmonitor("ADDRESS OK!");
                                     if(cauta){
                                         adaugainmonitor("TESTING PORT: "+9999);
                               socket = new Socket(ia, 9999);

                                if(socket.isBound()){
                                    adaugainmonitor("PORT OK!");
                                    adaugaserver(socket);
                                }
                                    }
                                    

                                }


                                 else{
                                    adaugainmonitor("SHIT IP ADDRESS: "+f1 + "." + f2 + "." + c + "." + i);
                                    VectorObiectServer=new Vector<String>();
                                    VectorObiectServer.add(ia.getHostName());
                                    VectorObiectServer.add(ia.getHostAddress());
                                    VectorObiectServer.add(String.valueOf(9999));
                                    stergeserver(VectorObiectServer);
                                    }
                            } catch (UnknownHostException ex) {
                                continue;
                            } catch (IOException ex) {
                                   adaugainmonitor("SHIT PORT: "+9999);
                                   VectorObiectServer=new Vector<String>();
                                    VectorObiectServer.add(ia.getHostName());
                                    VectorObiectServer.add(ia.getHostAddress());
                                    VectorObiectServer.add(String.valueOf(9999));
                                    stergeserver(VectorObiectServer);
                                continue;
                            }
                 }
            else{
                break;
            }
            }
             d=1;
             c++;
             if(c==t3){
                        for(int i=t4;i>=d;i--)
            {
                 if(cauta){
                try {
                                ia=InetAddress.getByName(f1 + "." + f2 + "." + c + "." + i);
                                adaugainmonitor("SEARCHING IP: "+f1 + "." + f2 + "." + c + "." + i);
                                if(ia.isReachable(timeout)){
                                    adaugainmonitor("ADDRESS OK!");
                                     if(cauta){
                                      adaugainmonitor("TESTING PORT: "+9999);
                               socket = new Socket(ia, 9999);

                                if(socket.isBound()){
                                  adaugainmonitor("PORT OK!");
                                  adaugaserver(socket);
                                }
                                    }
                                    
                                }
                                 else{
                                    adaugainmonitor("SHIT IP ADDRESS: "+f1 + "." + f2 + "." + c + "." + i);
                                    VectorObiectServer=new Vector<String>();
                                    VectorObiectServer.add(ia.getHostName());
                                    VectorObiectServer.add(ia.getHostAddress());
                                    VectorObiectServer.add(String.valueOf(9999));
                                    stergeserver(VectorObiectServer);
                                    }
                            } catch (UnknownHostException ex) {
                                continue;
                            } catch (IOException ex) {
                                   adaugainmonitor("SHIT PORT: "+9999);
                                   VectorObiectServer=new Vector<String>();
                                    VectorObiectServer.add(ia.getHostName());
                                    VectorObiectServer.add(ia.getHostAddress());
                                    VectorObiectServer.add(String.valueOf(9999));
                                    stergeserver(VectorObiectServer);
                                continue;
                            }
                }
            else{
                break;
            }
            }
                   }
            }
        }
    }

    }

    void adaugaserver(Socket sok){
       VectorObiectServer=new Vector<String>();
       VectorObiectServer.add(sok.getInetAddress().getHostName());
       VectorObiectServer.add(sok.getInetAddress().getHostAddress());
       VectorObiectServer.add(String.valueOf(sok.getPort()));
       mg.adaugaintabel(VectorObiectServer);



    }

    void stergeserver(Vector<String> vect){

       mg.stergedintabel(vect);

    }

    void adaugainmonitor(String str){
        clg.adaugaText(str);
    }
}
