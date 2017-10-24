package socket;

import java.io.DataInputStream;  
import java.io.DataOutputStream;  
import java.io.IOException;  
import java.net.Socket;  
  
import org.apache.log4j.Logger;  
  
  
public class ServiceHandler implements Runnable {  
    private Logger logger = Logger.getLogger(ServiceHandler.class);  
    private Socket socket;  
      
    public ServiceHandler(Socket socket){  
        this.socket = socket;  
    }  
      
    @Override  
    public void run() {  
        DataOutputStream output = null;  
        DataInputStream input = null;  
        try{  
            output = SocketIO.getOutput(socket);  
            input = SocketIO.getInput(socket);  
              
            int len = input.readInt();  
            byte[] bytes = new byte[len];  
            input.read(bytes);  
              
            String request = new String(bytes, "utf-8");  
              
            logger.info("client request:"+request);  
              
            String answer = "not supported";  
            if(request.equals("your name?")){  
                answer = "server";  
            }  
            bytes = answer.getBytes("utf-8");  
            len = bytes.length;  
            output.writeInt(len);  
            output.write(bytes);  
        }catch(Exception e){  
            e.printStackTrace();  
            logger.error("Service handler run exception!");  
        }finally{  
            if(socket != null){  
                try {  
                    socket.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                    logger.error("socket close error");  
                }  
            }  
        }  
    }  
}