package sample;

import javax.sound.sampled.Line;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class ServerSide implements Runnable {
    private static Socket socket;

    public void run()
    {
        HashMap<String,int[][]> LineDetails = new HashMap<String, int[][]>();
        try
        {

            int port = 8080;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server Started and listening to the port 8080");

            //Server is running always. This is done using this while(true) loop
            while(true)
            {
                //Reading the message from the client
                Socket socket = serverSocket.accept();




                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String number = br.readLine();
                String number1 = br.readLine();
                String number2 = br.readLine();
                String number3 = br.readLine();
                //System.out.println("Message received from client is "+number1);
                //System.out.println("Message received from client is "+number2);
                //Multiplying the number by 2 and forming the return message
                String machine = number1.split(":")[1];
                boolean state =Boolean.parseBoolean(number2.split(":")[1].split(" ")[1]);
                String line = number3.split(":")[1].split(" ")[1];

                if (!LineDetails.containsKey(line)){
                    int[][] x = {{0,0},{0,0},{0,0},{0,0}};
                    LineDetails.put(line,x);
                }
                if(machine.equals(" S1")){
                    if(state == true){
                        LineDetails.get(line)[0][0]++;
                    }
                    LineDetails.get(line)[0][1]++;
                }else if(machine.equals(" S2")){
                    if(state == true){
                        LineDetails.get(line)[1][0]++;
                    }
                    LineDetails.get(line)[1][1]++;
                }else if(machine.equals(" S3")){
                    if(state == true){
                        LineDetails.get(line)[2][0]++;
                    }
                    LineDetails.get(line)[2][1]++;
                }else if(machine.equals(" S4")){
                    if(state == true){
                        LineDetails.get(line)[3][0]++;
                    }
                    LineDetails.get(line)[3][1]++;
                }

                System.out.println(machine+" "+state);

                //Sending the response back to the client.
                OutputStream os = socket.getOutputStream();
                Scanner sc=new Scanner(System.in);
                String inp=null;
                String t="HTTP/1.1 200 OK\r\n";
                byte[]s=t.getBytes("UTF-8");
                os.write(s);

                t="Content-Length: 788\r\n";
                s=t.getBytes("UTF-8");
                os.write(s);
                t="Content-Type: text/html\r\n\r\n";
                s=t.getBytes("UTF-8");
                os.write(s);
                os.flush();

                for(String sline : LineDetails.keySet()){
                    System.out.println(Arrays.toString(LineDetails.get(sline)[3]) + " "+sline);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e){}
        }
    }
}
