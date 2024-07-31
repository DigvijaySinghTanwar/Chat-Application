import java.net.*;
import java.io.*;

class Server2{

ServerSocket server;
Socket socket;
BufferedReader br;
PrintWriter  pwrite;


public Server2(){                                                                     //constructor

 try{ 
 server= new ServerSocket(7777);
 System.out.println("Server is Ready");
 System.out.println("waiting to connect with Network...");
 socket =server.accept();  
 
 br= new BufferedReader(new InputStreamReader(socket.getInputStream()));
 pwrite= new PrintWriter(socket.getOutputStream());
 
    startReading();
    startWriting();
} 
    catch(Exception e){
        e.printStackTrace();
    }
    }
public void startReading(){

    Runnable r1=()->{
       
        System.out.println("Reading started");
  try {       
 while(true){
          
                String msg = br.readLine();

                if(msg.equals("exit")){
                    System.out.println("Client Exited");
              socket.close();
                     break;
                 }
                 System.out.println("Client ::=>>"+msg);
           
        }  
 } catch (IOException e) {
                //   catch block
              //  e.printStackTrace();
   System.out.println(" !! Connection is Closed !!");   
         }

    };
    new Thread(r1).start();
}
public void startWriting(){
    // thread -> data user se lega and then send to client
    Runnable r2=()->{
                 System.out.println("Writing Started");

try{ 

       while(true && !socket.isClosed()){

 BufferedReader br1= new BufferedReader(new InputStreamReader(System.in)) ;     
         String content =br1.readLine();   
         
        pwrite.println(content);
        pwrite.flush();
       if(content.equals("exit")){

            socket.close();
            break;

       }
         
       }
  } catch (Exception e) {
               //     e.printStackTrace();
System.out.println(" !! Connection is Closed !!");
           }
    };

    new Thread(r2).start();
}
    public static void main(String[] args) {
        System.out.println("This is SERVER");
        new Server();
    }
}