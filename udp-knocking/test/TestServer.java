package test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TestServer extends Thread {

    public static void main(String[] args) throws InterruptedException {

        Thread server = new Thread(() -> {
            try {
                DatagramSocket socket = new DatagramSocket(50000);

                byte[] buffer = new byte[256];

                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                InetAddress clientAddress = request.getAddress();
                int clientPort = request.getPort();

                String data = "Message from server";
                buffer = data.getBytes();

                DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
                socket.send(response);


            } catch (Exception e){
                e.printStackTrace();
            }


        });


        Thread client = new Thread(() -> {
            try {
                String hostname = "localhost";
                int port = 50000;

                InetAddress address = InetAddress.getByName(hostname);
                DatagramSocket socket = new DatagramSocket();

                byte[] buffer = new byte[512];

                DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, port);
                socket.send(request);



                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket.receive(response);

                String quote = new String(buffer, 0, response.getLength());

                System.out.println("XD: " + quote);

            } catch (Exception e){
                e.printStackTrace();
            }
        });


                server.start();
                Thread.sleep(1000);
                client.start();
    }

}

