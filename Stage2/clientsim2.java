 import java.net.*;
 import java.io.*;
 import java.util.ArrayList;
 
 public class clientsim2 {
 
     public static void main(String args[]){
     	 String response = "";            
     	 String message = "";    
      	 String serverArray[]; 
      	 int serverCount = 0; 
      	 int serverJob = 0; 
         Socket socket = null;
         
         
         try {
             socket = new Socket("localhost",50000);
             DataOutputStream output = new DataOutputStream(socket.getOutputStream());
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             message = "HELO\n";
             output.write(message.getBytes());
             output.flush();
             response = input.readLine();
             System.out.println("S: "+response);
             String user =  System.getProperty("user.name");
             message = "AUTH "+user+"\n";
             output.write(message.getBytes());
             output.flush();
             response = input.readLine();
             System.out.println("S:"+response);
             message = "REDY\n";
             output.write(message.getBytes());
             output.flush();
             response = input.readLine();
             System.out.println("S: "+response);
	     while(!response.equals("NONE")){ 
             if(response.contains("JOBN")) {
 	     String serverResponse = response;
 	     
 	     
       try {
            String serverString[] = serverResponse.split(" ",-1);
            message = "GETS Avail "+ serverString[4] + " " + serverString[5] + " " + serverString[6] +"\n";
            output.write(message.getBytes());
            output.flush();
            response = input.readLine();
            System.out.println("S:"+response);
            serverResponse = response;
            serverArray = serverResponse.split(" ", -1);
            if(Integer.parseInt(serverArray[1])!=0) {
                    System.out.println("Inside the avail function");
                    serverCount = Integer.parseInt(serverArray[1]);
                    serverArray = new String[serverCount];
                    message = "OK\n";
                    output.write(message.getBytes());
                    output.flush();
            for(int i = 0; i < serverCount; i++){
                        serverArray[i] = input.readLine();
                        System.out.println("S: " +serverArray[i]);
                    }

                    message = "OK\n";
                    output.write(message.getBytes());
                    output.flush();
                    response = input.readLine();
                    String serverOne[] = serverArray[0].split(" ", -1);
                    serverJob = Integer.parseInt(serverString[2]);
                    message = "SCHD" + " " + serverJob +" " + serverOne[0] + " " + serverOne[1] + "\n";
                    System.out.println("Job is schd as = "+message);
                    output.write(message.getBytes());
                    output.flush();
                    response = input.readLine();
                    System.out.println("S: "+response);

           } else {
                    System.out.println("Inside the capable function");
                    message = "OK\n";
                    output.write(message.getBytes());
                    output.flush();
                    response = input.readLine();
                    System.out.println("S:"+response);
                    message = "GETS Capable "+ serverString[4] + " " + serverString[5] + " " + serverString[6] +"\n";
                    output.write(message.getBytes());
                    output.flush();
                    response = input.readLine();
                    System.out.println("S: "+response);
                    serverResponse = response;
                    serverArray = serverResponse.split(" ", -1);
                    serverCount = Integer.parseInt(serverArray[1]);
                    serverArray = new String[serverCount];
                    message = "OK\n";
                    output.write(message.getBytes());
                    output.flush();

           for(int i = 0; i < serverCount; i++){
                        serverArray[i] = input.readLine();
                        System.out.println("S: " +serverArray[i]);
                    }

                    message = "OK\n";
                    output.write(message.getBytes());
                    output.flush();
                    response = input.readLine();
                    String serverOne[] = serverArray[0].split(" ", -1);                 
                    serverJob = Integer.parseInt(serverString[2]);
                    message = "SCHD" + " " + serverJob +" " + serverOne[0] + " " + serverOne[1] + "\n";
                    System.out.println("Job is schd as = "+message);
                    output.write(message.getBytes());
                    output.flush();
                    response = input.readLine();
                    System.out.println("S: "+response);
                 }   
          } catch (Exception e) {
                 System.out.println("Invalid array:"+e.getMessage());
             }   
         }
         message = "REDY\n";
         output.write(message.getBytes());
         output.flush();
         response = input.readLine();
         System.out.println("S: "+response);
         }
         message = "QUIT\n";
         output.write(message.getBytes());
         output.flush();
         response = input.readLine();
         output.close();
         } 
         
         
         
         
         catch (UnknownHostException e){
             System.out.println("Socket:"+e.getMessage());
             } catch (EOFException e){
             System.out.println("EOF:"+e.getMessage());
             } catch (IOException e){
             System.out.println("IO:"+e.getMessage());
         } finally {
             if(socket!=null)
              try {
                 socket.close();
             } catch (IOException e){
                 System.out.println("close:"+e.getMessage());
	}
}
} 
}
         
 
