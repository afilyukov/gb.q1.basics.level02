package endpoints;

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Client1 extends JFrame {

  private JTextArea chatArea;
  private JTextField msgInputField;
  private DataInputStream in;
  private DataOutputStream out;
  private Socket socket;

  public Client1() {
    drawGUI();
    try {
      open();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void open() throws IOException {
    Socket socket = new Socket("localhost", Server.PORT);
    System.out.println("Connected to server: " + socket);
    in = new DataInputStream(socket.getInputStream());
    out = new DataOutputStream(socket.getOutputStream());

    Thread thread = new Thread(() -> {
      try {
        out.writeUTF("/auth l1 p1");
        while (true) {
          String message = in.readUTF();
          if (message.startsWith("/authok")) {
            chatArea.append("***Service message: You are authorized!***");
            chatArea.append("\n");
            break;
          }
        }
        while (true) {
          try {
            String message = in.readUTF();
            if (message.equals("/end")) {
              chatArea.append("***Service message: Session closed. Cau!***");
              chatArea.append("\n");
              break;
            }
            chatArea.append(message);
            chatArea.append("\n");
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        closeConnection();
      }

    });
    thread.start();
  }

  public void closeConnection() {
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

  public void drawGUI() {
    setBounds(600, 300, 500, 500);
    setTitle("Barboss");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    chatArea = new JTextArea();
    chatArea.setEditable(false);
    chatArea.setLineWrap(true);
    add(new JScrollPane(chatArea), BorderLayout.CENTER);

    JPanel bottomPanel = new JPanel(new BorderLayout());
    JButton btnSendMsg = new JButton("Отправить");
    bottomPanel.add(btnSendMsg, BorderLayout.EAST);
    msgInputField = new JTextField();
    msgInputField.addActionListener(e -> sendMessage());
    add(bottomPanel, BorderLayout.SOUTH);
    bottomPanel.add(msgInputField, BorderLayout.CENTER);
    btnSendMsg.addActionListener(e -> sendMessage());

    setVisible(true);
  }

  public void sendMessage() {
    if (!msgInputField.getText().trim().isEmpty()) {
      try {
        out.writeUTF(msgInputField.getText());
        msgInputField.setText("");
        msgInputField.grabFocus();
      } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения");
      }
    }
  }

}
