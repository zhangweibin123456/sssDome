package socket;

import java.io.DataInputStream;  
import java.io.DataOutputStream;  
import java.io.IOException;  
import java.net.InetSocketAddress;  
import java.net.Socket;  
import java.net.SocketAddress;  
  
import org.apache.log4j.Logger;  
  
  
public class Client {  
    private Logger logger = Logger.getLogger(Client.class);  
    private int port = 10000;  
    private String host = "localhost";  
    private Socket socket;  
      
    public Client(){  
        try {  
            socket = new Socket();  
            //关闭socket时，立即释放socket绑定端口以便端口重用，默认为false  
            socket.setReuseAddress(true);  
            //关闭传输缓存，默认为false  
            socket.setTcpNoDelay(true);  
            //如果输入流等待1000毫秒还未获得服务端发送数据，则提示超时，0为永不超时  
            socket.setSoTimeout(10000);  
            //关闭socket时，底层socket不会直接关闭，会延迟一会，直到发送完所有数据  
            //等待10秒再关闭底层socket连接，0为立即关闭底层socket连接  
            socket.setSoLinger(true, 10);  
            //设置性能参数，可设置任意整数，数值越大，相应的参数重要性越高（连接时间，延迟，带宽）  
            socket.setPerformancePreferences(3, 2, 1);  
            SocketAddress address = new InetSocketAddress(host, port);  
            //socket创建超时时间为1000毫秒  
            socket.connect(address, 10000);  
              
            logger.info("client ip:"+socket.getLocalAddress());  
            logger.info("client port:"+socket.getLocalPort());  
            logger.info("servetr ip:"+socket.getInetAddress());  
            logger.info("servetr port:"+socket.getPort());  
        } catch (IOException e) {  
            e.printStackTrace();  
            logger.error("Cilent socket establish failed!");  
        }  
        logger.info("Client socket establish success!");  
    }  
      
    public void request(){  
        DataOutputStream output = null;  
        DataInputStream input = null;  
        try{  
            output = SocketIO.getOutput(socket);  
            input = SocketIO.getInput(socket);  
              
            String question = "your name?";  
            byte[] bytes = question.getBytes("utf-8");  
            int len = bytes.length;  
            output.writeInt(len);  
            output.write(bytes);  
              
            len = input.readInt();  
            bytes = new byte[len];  
            input.read(bytes);  
              
            logger.info("server answer:"+new String(bytes,"utf-8"));  
        }catch(Exception e){  
            e.printStackTrace();  
            logger.error("client request error");  
        }finally{  
            if(socket != null){  
                try{  
                    socket.close();  
                }catch(Exception e){  
                    e.printStackTrace();  
                    logger.error("socket close error");  
                }  
            }  
        }  
    }  
      
    public static void main(String[] args){  
        Client client = new Client();  
        client.request();  
    }  
}  
