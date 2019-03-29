import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * UDP服务提供方
 */
public class UDPProvider {
    public static void main(String[] args) throws IOException {
        System.out.println("UDPProvider start");
        //作为接收者，定义一个端口用于监听
        DatagramSocket ds = new DatagramSocket(20000);

        //创建一个接收实体
        final byte[] buff = new byte[255];
        DatagramPacket receivePacket = new DatagramPacket(buff, buff.length);
        //接收
        ds.receive(receivePacket);
        //得到发送端的IP地址
        String ip = receivePacket.getAddress().getHostAddress();
        //发送者IP端口
        int port = receivePacket.getPort();
        //数据大小
        int datalen = receivePacket.getLength();
        //数据信息
        String data = new String(receivePacket.getData(), 0, datalen);
        System.out.println("UDPProvider ip from :" + ip + ":" + port);
        System.out.println("UDPProvider data : " + data);

        //构建一份回送数据
        String responseData = "receive data to len " + datalen;
        byte[] responseDataBytes = responseData.getBytes();


        DatagramPacket responsePacket = new DatagramPacket(responseDataBytes, responseDataBytes.length,
                receivePacket.getAddress(), receivePacket.getPort());

        ds.send(responsePacket);

        System.out.println("UDPProvider close");
        ds.close();
    }
}
