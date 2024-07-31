import java.io.*;
import java.net.*;

public class Client {
    Socket socket;
    BufferedReader br;
    PrintWriter  pwrite;

//       Constructor        //    
// Initialize  socket , BufferReader 

public Client(){

try {
        System.out.println("Sending Request to Server");
        socket= new Socket("127.0.0.1",7777);
        System.out.println("Connected");
        br= new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pwrite= new PrintWriter(socket.getOutputStream());
        
           // Calling both Methods -- Reading and Writing 
              startReading();
              startWriting();

    } catch (Exception e) {
        e.printStackTrace();
                                 }
}

 //  start Reading Method 
public void startReading(){

        Runnable r1=()->{
           
            System.out.println("Reading started");
try{
            while(true){
                           String msg = br.readLine();
                           if(msg.equals("exit")) {
                           System.out.println("Server terminated the chat");
                           socket.close();
                           break;
                                                        }
                         System.out.println("Server ::=>>"+msg);
                         }
}
catch (Exception e) {
                    //   catch block
                  //  e.printStackTrace();
System.out.println(" !! Connection is Closed !!");
                          }  
    
                            };
new Thread(r1).start();
}

//   start writing Method 

public void startWriting(){
        
// thread -> data user se lega and then send to client

Runnable r2=()->{
System.out.println("Writing Started");

 try {          

 while(true && !socket.isClosed()){
              
            BufferedReader br1= new BufferedReader(new InputStreamReader(System.in)) ;     
             String content =br1.readLine();
             pwrite.println(content);
             pwrite .flush();
           if(content.equals("exit")){

            socket.close();
            break;

                                         }
           }
   } catch (Exception e) {
         // e.printStackTrace();
      System.out.println(" !! Connection is Closed !!");
               }

        };
    
        new Thread(r2).start();
    }

    public static void main(String[] args) {
     System.out.println("This is our CLIENT");
   
     new Client();
        
    }
}
