import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

class Game extends BorderPane implements Constants{
    private Map map;
    private final ArrayList<Tank> players;
    private final ArrayList<BrickWall> brickWalls;
    private final ArrayList<SteelWall> steelWalls;
    private final ArrayList<TreeWall> treeWalls;
    private final ArrayList<Water> waterBlocks;
    private final ArrayList<Enemy> enemies;
    public Game(Map map){
    
        //this.getChildren().add(new MediaView(audioPlayer));
        this.setStyle("-fx-background-color:black");
        setMap(map);
        brickWalls = new ArrayList<>();
        steelWalls = new ArrayList<>();
        treeWalls = new ArrayList<>();
        waterBlocks = new ArrayList<>();
        enemies = new ArrayList<>();
        enemies.add(new Enemy(map, 0,0));
        enemies.add(new Enemy(map, 14,14));
        for (int i = 0; i < 2; i++) {
            this.getChildren().add(enemies.get(i).getTexture());
            enemies.get(i).run();
        }
        players = new ArrayList<>();
        players.add(new Tank(map));
        players.get(0).setPosition(5,6);
        //this.getChildren().add(players.get(0).getBullet().getTexture());

        this.getChildren().add(players.get(0).getTexture());
        //addPlayer();
        scanWalls();
        this.getChildren().add(map);
    }



    public void shootBulletMain() throws InterruptedException {
        Bullet bullet = new Bullet(players.get(0).getPosition());
        bullet.setDirection(players.get(0).getDirection());
        this.getChildren().add(bullet.getTexture());
        shootBullet(bullet);
    }

    public void shootBullet(Bullet bullet) throws InterruptedException {
        new Thread(()-> {
            int steps = 0;
            int x = bullet.getPosition().getX();
            int y = bullet.getPosition().getY();
            boolean intersect = false;
            TranslateTransition animation = new TranslateTransition();
            animation.setNode(bullet.getTexture());

            FadeTransition fade = new FadeTransition();
            fade.setNode(bullet.getTexture());
            fade.setFromValue(1);

            switch (bullet.getDirection()) {
                case 0:
                    for (int i = 0; i < map.getSize(); i++) {
                        if (!intersect) {
                            if (map.getValueAt(x, y - i - 1) == ' ' || map.getValueAt(x, y - i - 1) == 'P' || map.getValueAt(x, y - i - 1) == 'T' || map.getValueAt(x, y - i - 1) == 'W') {
                                steps++;
                            } else intersect = true;
                            if (map.getValueAt(x, y - i - 1) == 'B') {
                                for (int j = 0; j < brickWalls.size(); j++) {
                                    if (brickWalls.get(j).getPosition().getX() == x && brickWalls.get(j).getPosition().getY() == y - i - 1) {
                                        brickWalls.get(j).destruct(steps);
                                        if (brickWalls.get(j).getHealth() == 0) {
                                            map.setValueAt(x, y - i - 1, ' ');
                                            players.get(0).getMap().setValueAt(x, y - i - 1, ' ');
                                            //map.print();
                                        }
                                    }
                                }
                            }
                        }
                    }
                    animation.setDuration(Duration.seconds(0.05 * steps));
                    animation.setByY(bullet.getPosition().getY() - steps * step);
                    animation.play();
                    break;
                case 1:
                    for (int i = 0; i < map.getSize(); i++) {
                        if (!intersect) {
                            if (map.getValueAt(x + i + 1, y) == ' ' || map.getValueAt(x + i + 1, y) == 'P' || map.getValueAt(x + i + 1, y) == 'W' || map.getValueAt(x + i + 1, y) == 'T') {
                                steps++;
                            } else intersect = true;
                            if (map.getValueAt(x + i + 1, y) == 'B') {
                                for (int j = 0; j < brickWalls.size(); j++) {
                                    if (brickWalls.get(j).getPosition().getX() == x + i + 1 && brickWalls.get(j).getPosition().getY() == y) {
                                        brickWalls.get(j).destruct(steps);
                                        if (brickWalls.get(j).getHealth() == 0) {
                                            map.setValueAt(x + i + 1, y, ' ');
                                            players.get(0).getMap().setValueAt(x + i + 1, y, ' ');
                                        }
                                    }
                                }
                            }
                        }
                    }
                    animation.setDuration(Duration.seconds(0.05 * steps));
                    animation.setByX(bullet.getPosition().getX() + steps * step);
                    animation.play();
                    break;
                case 2:
                    for (int i = 0; i < map.getSize(); i++) {
                        if (!intersect) {
                            if (map.getValueAt(x, y + i + 1) == ' ' || map.getValueAt(x, y + i + 1) == 'P' || map.getValueAt(x, y + i + 1) == 'W' || map.getValueAt(x, y + i + 1) == 'T') {
                                steps++;
                            } else intersect = true;
                            if (map.getValueAt(x, y + i + 1) == 'B') {
                                for (int j = 0; j < brickWalls.size(); j++) {
                                    if (brickWalls.get(j).getPosition().getX() == x && brickWalls.get(j).getPosition().getY() == y + i + 1) {
                                        brickWalls.get(j).destruct(steps);
                                        if (brickWalls.get(j).getHealth() == 0) {
                                            map.setValueAt(x, y + i + 1, ' ');
                                            players.get(0).getMap().setValueAt(x, y + i + 1, ' ');
                                        }
                                    }
                                }
                            }
                        }
                    }
                    animation.setDuration(Duration.seconds(0.05 * steps));
                    animation.setByY(bullet.getPosition().getY() + steps * step);
                    animation.play();
                    break;
                case 3:
                    for (int i = 0; i < map.getSize(); i++) {
                        if (!intersect) {
                            if (map.getValueAt(x - i - 1, y) == ' ' || map.getValueAt(x - i - 1, y) == 'P' || map.getValueAt(x - i - 1, y) == 'W' || map.getValueAt(x - i - 1, y) == 'T') {
                                steps++;
                            } else intersect = true;
                            if (map.getValueAt(x - i - 1, y) == 'B') {
                                for (int j = 0; j < brickWalls.size(); j++) {
                                    if (brickWalls.get(j).getPosition().getX() == x - i - 1 && brickWalls.get(j).getPosition().getY() == y) {
                                        brickWalls.get(j).destruct(steps);
                                        if (brickWalls.get(j).getHealth() == 0) {
                                            map.setValueAt(x - i - 1, y, ' ');
                                            players.get(0).getMap().setValueAt(x - i - 1, y, ' ');
                                        }
                                    }
                                }
                            }
                        }
                    }
                    animation.setDuration(Duration.seconds(0.05 * steps));
                    animation.setByX(bullet.getPosition().getX() - steps * step);
                    animation.play();
                    break;
            }
            fade.setDuration(Duration.seconds(1.5));
            fade.setToValue(0);
            fade.play();
        }).start();
    }
    public void scanWalls(){
        int brickCnt = 0;
        int steelCnt = 0;
        int treeCnt = 0;
        int waterBlocksCnt = 0;
        for(int i = 0; i < map.getSize(); i++){
            for(int j = 0; j < map.getSize(); j++){
                switch(map.getValueAt(j,i)){
                    case 'B':
                        brickWalls.add(new BrickWall(j,i));
                        this.getChildren().add(brickWalls.get(brickCnt++).getTexture());
                        break;
                    case 'S':
                        steelWalls.add(new SteelWall(j,i));
                        this.getChildren().add(steelWalls.get(steelCnt++).getTexture());
                        break;
                    case 'T':
                        treeWalls.add(new TreeWall(j,i));
                        this.getChildren().add(treeWalls.get(treeCnt++).texture);
                        break;
                    case 'W':
                        waterBlocks.add(new Water(j,i));
                        this.getChildren().add(waterBlocks.get(waterBlocksCnt++).texture);
                        break;
                }
            }
        }
    }

    public void setMap(Map map){
        this.map = map;
    }
    public Map getMap() {
        return map;
    }
    public void addPlayer(){
        Tank tank = new Tank(map);
        this.players.add(tank);
        tank.setMap(this.getMap());
    }
    public Tank getPlayer(int index){
        return players.get(index);
    }
    public Enemy getEnemy(int index){
        return enemies.get(index);
    }
    public ArrayList<BrickWall> getBrickWalls(){
        return brickWalls;
    }
}