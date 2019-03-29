import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSearcher {
    public static void main(String[] args) throws IOException {
        System.out.println("UDPSearcher start");
        //作为搜索方，无需指定端口，让系统直接分配
        DatagramSocket ds = new DatagramSocket();

        //构建一份请求数据
        String requestData = "Holle world";
        byte[] requestDataBytes = requestData.getBytes();


        DatagramPacket requestPacket = new DatagramPacket(requestDataBytes, requestDataBytes.length
        );
        requestPacket.setAddress(InetAddress.getLocalHost());
        requestPacket.setPort(20000);

        ds.send(requestPacket);


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


        System.out.println("UDPSearcher close");
        ds.close();
    }


}
