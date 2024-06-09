import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerClass {

  private DataInputStream in;
  private DataOutputStream out;

  public ServerClass() {
    open();
    listenToConsole();
  }

  private void open() {
    try {
      ServerSocket serverSocket = new ServerSocket(9999);
      System.out.println("Server is running on 8888");

      Socket client = serverSocket.accept();
      System.out.println(client);
      System.out.println(String.format("Client connected: %s", client.getLocalSocketAddress()));

      in = new DataInputStream(client.getInputStream());
      out = new DataOutputStream(client.getOutputStream());

      new Thread(new Runnable() {
        @Override
        public void run() {
          while (true) {
            try {
              String message = in.readUTF();
              if (message.equalsIgnoreCase("/end")) {
                break;
              }
              System.out.println("Client: " + message);
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
      }).start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void listenToConsole() {
    System.out.println("Start to chat: ");
    Scanner scn = new Scanner(System.in);
    String message = "";

    while (true) {
      if (message.equalsIgnoreCase("/end")) {
        break;
      }
      if (scn.hasNextLine()) {
        message = scn.nextLine();
        sendMessage(message);
      }
    }
  }

  private void sendMessage(String message) {
    if (!message.trim().isEmpty()) {
      try {
        out.writeUTF(message);
        System.out.println("Server: " + message);
      } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения");
      }
    }
  }
}
