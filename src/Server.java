
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

interface MapSample{
    String[] stringMap = {
            "               ",
            " BBBBB   BBBBB ",
            " B           B ",
            " BSSSS   SSSSB ",
            " B   S   S   B ",
            " B   BWWWB   B ",
            "               ",
            "               ",
            "     SSSBBB    ",
            " BTTTS    SSBS ",
            " B   STTTTS  S ",
            " BBBB   SSSBSB ",
            " B  TT  TT   B ",
            "    TTTTTT     ",
            "               "
    };
    Map map = new Map(stringMap);
}
interface Constants{
    String musicPath = "music/music.mp3";
    String brickWallPath = "textures/brickwall.jpg";
    String dBrickwallPath = "textures/dbrickwall.png";
    String steelWallPath = "textures/steelwall.jpg";
    String waterPath = "textures/water.jpg";
    String treePath = "textures/tree.png";
    //String treePath = "textures/TestTree.png";
    String playerPath = "textures/character.jpg";
    String mainTankPath = "textures/tank.png";
    String enemyTankPath = "textures/enemy.png";
    String bulletPath = "textures/bullet.png";
    int W = 960;
    int H = 960;
    int step = 64;
    int cd = 1;
}

class Position{
    private int x;
    private int y;

    public Position(){
        this.x = 0;
        this.y = 0;
    }
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setXY(int x, int y){
        setX(x);
        setY(y);
    }
    public boolean equals(Position position){
        return (this.x == position.x) && (this.y == position.y);
    }
    public String toString(){
        return "(" + x + "," + y+")";
    }
}

public class Server extends Application implements Constants, MapSample{

    @Override
    public void start(Stage primaryStage) throws IOException{
        //ServerSocket server = new ServerSocket(8000);
        //Socket socket = server.accept();
        //DataInputStream from = new DataInputStream(socket.getInputStream());
        //DataOutputStream to = new DataOutputStream(socket.getOutputStream());


        //ObjectInputStream objectFrom = new ObjectInputStream(socket.getInputStream());
        //ObjectOutputStream objectIn = new ObjectOutputStream(socket.getOutputStream());

        //System.out.println("Is connected: "+socket.isConnected());
        //to.writeUTF(socket.getInetAddress().toString());
        //to.flush();
        StackPane mainPane = new StackPane();
        AtomicBoolean onScreen = new AtomicBoolean(false);
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);

        Button resume = new Button("Resume");
        resume.setAlignment(Pos.CENTER);
        resume.setStyle("-fx-font-size:20");
        resume.setMinSize(100,50);
        Button exit = new Button("Exit");
        exit.setStyle("-fx-font-size:20");
        exit.setMinSize(100,50);
        exit.setAlignment(Pos.CENTER);
        exit.setOnAction(e-> System.exit(0));

        
        vbox.getChildren().addAll(resume,exit);

        BoxBlur blur = new BoxBlur();
        blur.setIterations(10);


        Game game = new Game(map);

        VBox textBox = new VBox();
        textBox.setAlignment(Pos.BOTTOM_CENTER);
        Text text1 = new Text("Multiplayer is not working (\"client classes are not working\")");
        text1.setFill(Color.WHITE);
        text1.setStyle("-fx-font-size: 30");
        Text text2 = new Text("YOU CAN TURN OFF MUSIC IN \"ESC\" MENU");
        text2.setFill(Color.WHITE);
        text2.setStyle("-fx-font-size: 30");
        textBox.setSpacing(5);
        textBox.getChildren().addAll(text2,text1);

        mainPane.getChildren().addAll(game,textBox);
        Scene scene = new Scene(mainPane, W, H+50, Color.BLACK);
        primaryStage.setTitle("Project-3");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e->System.exit(0));
        /*new Thread(() ->
        {
            try {
                switch (from.readUTF()) {
                    case "W":
                        game.getPlayer(0).moveUp();
                        //game.getPlayer(0).setDirection(0);
                        break;
                    case "D":
                        game.getPlayer(0).moveRight();
                        //game.getPlayer(0).setDirection(1);
                        break;
                    case "S":
                        game.getPlayer(0).moveDown();
                        //game.getPlayer(0).setDirection(2);
                        break;
                    case "A":
                        game.getPlayer(0).moveLeft();
                        //game.getPlayer(0).setDirection(3);
                        break;
                    case "s":
                        try {
                            game.shootBulletMain();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        break;
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }).start();*/
        AtomicLong lstShoot = new AtomicLong();
        AtomicLong lstMove = new AtomicLong();
        long moveCD = 100;
        long shootCD = 500;

        scene.setOnKeyPressed(e->{
            boolean isOn = true;
            long time = System.currentTimeMillis();
            switch(e.getCode()){
                case A:
                    if(time >= lstMove.get()+moveCD) {
                        game.getPlayer(0).moveLeft();
                        lstMove.set(time);
                    }
                    break;
                case W:
                    if(time >= lstMove.get()+moveCD) {
                        game.getPlayer(0).moveUp();
                        lstMove.set(time);
                    }
                    break;
                case D:
                    if(time >= lstMove.get()+moveCD) {
                        game.getPlayer(0).moveRight();
                        lstMove.set(time);
                    }
                    break;
                case S:
                    if(time >= lstMove.get()+moveCD) {
                        game.getPlayer(0).moveDown();
                        lstMove.set(time);
                    }
                    break;
                case SPACE:
                    if(time >= lstShoot.get() +shootCD) {
                        try {
                            game.shootBulletMain();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        lstShoot.set(time);
                    }
                    break;
                case ESCAPE:
                    if(!onScreen.get()) {
                        game.setEffect(blur);

                        resume.setOnAction(g->{
                            mainPane.getChildren().removeAll(vbox);
                            game.setEffect(null);
                            onScreen.set(false);
                        });


                        mainPane.getChildren().addAll(vbox);
                        onScreen.set(true);
                    }
                    else {
                        mainPane.getChildren().removeAll(vbox);
                        game.setEffect(null);
                        onScreen.set(false);
                    }
                    break;
            }
        });
    }
    //public static void main(String[] args) throws IOException {
        //ServerSocket server = new ServerSocket(8000);
        //Socket client = server.accept();
        // from = new DataInputStream(client.getInputStream());
        //DataOutputStream to = new DataOutputStream(client.getOutputStream());

    //}
}