import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
public class Client  extends Application implements Runnable{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Socket socket = new Socket("localhost", 8000);
        DataInputStream from = new DataInputStream(socket.getInputStream());
        DataOutputStream in = new DataOutputStream(socket.getOutputStream());
        //ObjectInputStream objectFrom = new ObjectInputStream(socket.getInputStream());
        //ObjectOutputStream objectIn = new ObjectOutputStream(socket.getOutputStream());

        System.out.println("Is connected: " + socket.isConnected());

        BorderPane mainPane = new BorderPane();
        
        //TextArea textField = new TextArea();
        //textField.setText(from.readUTF());

        //mainPane.setCenter(textField);
        Scene scene = new Scene(mainPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Client");
        new Thread(()-> {
                scene.setOnKeyReleased(e -> {
                    try {
                        switch (e.getCode()) {
                            case W:
                                //objectIn.writeObject(KeyCode.W);
                                in.writeUTF("W");
                                break;
                            case D:
                                //objectIn.writeObject(KeyCode.D);
                                in.writeUTF("D");
                                break;
                            case S:
                                //objectIn.writeObject(KeyCode.S);
                                in.writeUTF("S");
                                break;
                            case A:
                                in.writeUTF("A");
                                //objectIn.writeObject(KeyCode.A);
                                break;
                            case SPACE:
                                in.writeUTF("s");
                                //objectIn.writeObject(KeyCode.SPACE);
                                break;
                        }
                        //in.flush();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }

                });

        }).start();
        primaryStage.show();
    }

    @Override
    public void run() {

    }
}
