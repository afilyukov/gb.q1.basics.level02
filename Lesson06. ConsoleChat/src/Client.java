import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

  private DataInputStream in;
  private DataOutputStream out;
  private Socket socket;

  public Client() {
    open();
    listenToConsole();
  }

  private void open() {
    try {
      socket = new Socket("127.0.0.1", 9999);
      System.out.println(socket);
      in = new DataInputStream(socket.getInputStream());
      out = new DataOutputStream(socket.getOutputStream());

      new Thread(new Runnable() {
        @Override
        public void run() {
          while (true) {
            try {
              String message = in.readUTF();
              if (message.equalsIgnoreCase("/end")) {
                break;
              }
              System.out.println("Server: " + message);
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

  private void closeConnection() {
    try {
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      socket.close();
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
        System.out.println("Client: " + message);
      } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения");
      }
    }
  }
}
