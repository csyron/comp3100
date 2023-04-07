import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class myDSClient {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.1", 50000);
            String[] ServerMax = {""};
            boolean search = false;
            String message = "";
            connection(s);
            while (!message.contains("NONE")) {
                sendmessage(s, "REDY\n");
                message = readmessage(s);
                if (message.contains("JOBN")) {
                    String[] splitJob = message.split(" ");
                    sendmessage(s, "GETS Avail " + splitJob[4] + " " + splitJob[5] + 
                    " " + splitJob[6] + "\n");
                    message = readmessage(s);
                    sendmessage(s, "OK\n");
                    message = readmessage(s);
                    sendmessage(s, "OK\n");
                    if(search == false){
                        ServerMax = findServerMax(message);
                        search = true;
                    } message = readmessage(s);
                    sendmessage(s, "SCHD " + splitJob[2] + " " + ServerMax[0] + " " + ServerMax[1] + "\n");
                    message = readmessage(s);
                    System.out.println("SCHD: " + message);
                } else if (message.contains("DATA")) {
                    sendmessage(s, "OK\n");
                }
            } sendmessage(s, "QUIT\n");
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
        public static void connection(Socket s) {
        String message = "";
        sendmessage(s, "HELO\n");
        message = readmessage(s);
        System.out.println("RCVD: " + message);
        sendmessage(s, "AUTH " + System.getProperty("user.name") + "\n");
        message = readmessage(s);
        System.out.println("RCVD: " + message);
    }


    public static synchronized String readmessage(Socket s) {
        String message = "FAIL";
        try {
            DataInputStream input = new DataInputStream(s.getInputStream());
            byte[] byteArray = new byte[input.available()];
            byteArray = new byte[0];
            while (byteArray.length == 0) {
            byteArray = new byte[input.available()];
            input.read(byteArray);
            message = new String(byteArray, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            System.out.println("IO:"+e.getMessage());
        } return message;
    }

    public static synchronized void sendmessage(Socket s, String message) {
        try {
            DataOutputStream output = new DataOutputStream(s.getOutputStream());
            byte[] byteArray = message.getBytes();
            output.write(byteArray);
            output.flush();
        } catch (IOException e) {
            System.out.println("IO:"+e.getMessage());
        }
    }

    public static String[] findServerMax(String message) {
        String[] serverContents = message.split("\n");
        int CPU = 0;
        String[] server = {""};
        for (int i = 0; i < serverContents.length; i++) {
            server = serverContents[i].split(" ");
            int selectCPU = Integer.valueOf(server[4]);
            if (selectCPU > CPU) {
                CPU = selectCPU;
            }
        } for (int i = 0; i < serverContents.length; i++) {
            server = serverContents[i].split(" ");
            int selectCPU = Integer.valueOf(server[4]);
            if (selectCPU == CPU) {
                return server;
            }
        } return server;
    }
}
