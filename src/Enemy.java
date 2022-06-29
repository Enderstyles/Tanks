import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

class Enemy extends Tank implements Constants, Runnable{
    public Enemy(Map map, int x, int y){
        super(map);
        this.texture = new ImageView(new Image(new File(enemyTankPath).toURI().toString()));
        this.setPosition(x,y);
    }
    @Override
    public synchronized void run() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Random random = new Random();
                int dir = random.nextInt(4);
                System.out.println("Enemy direction: " + dir);
                switch (dir) {
                    case 0:
                        if (map.getValueAt(miniPosition.getX(), miniPosition.getY() - 1) == ' ') moveUp();
                        break;
                    case 1:
                        if (map.getValueAt(miniPosition.getX() + 1, miniPosition.getY()) == ' ') moveRight();
                        break;
                    case 2:
                        if (map.getValueAt(miniPosition.getX(), miniPosition.getY() + 1) == ' ') moveDown();
                        break;
                    case 3:
                        if (map.getValueAt(miniPosition.getX() - 1, miniPosition.getY()) == ' ') moveLeft();
                        break;
                    default:
                        System.out.println("Enemy direction error");
                        break;
                }
            }

        };
                /*boolean[] directions = {false, false, false, false};
                if(miniPosition.getY()>0) {
                    if (map.getValueAt(miniPosition.getX(), miniPosition.getY() - 1) == ' ') directions[0] = true;
                }
                if(miniPosition.getX() < (W/step)) {
                    if (map.getValueAt(miniPosition.getX() + 1, miniPosition.getY()) == ' ') directions[1] = true;
                }
                if(miniPosition.getY() < (H/step)) {
                    if (map.getValueAt(miniPosition.getX(), miniPosition.getY() + 1) == ' ') directions[2] = true;
                }
                if(miniPosition.getX()>0) {
                    if (map.getValueAt(miniPosition.getX() - 1, miniPosition.getY()) == ' ') directions[3] = true;
                }
                for(int i = 0; i < 4; i++){
                    if(directions[i]){
                        switch (i){
                            case 0:
                                moveUp();
                                break;
                            case 1:
                                moveRight();
                                break;
                            case 2:
                                moveDown();
                                break;
                            case 3:
                                moveLeft();
                                break;
                        }
                    }
                }
            }
        };*/
        timer.schedule(task, 0, 1000L);
    }

    @Override
    public void findPos(){
        if(map == null) return;
        for(int i = 0; i < map.getSize();i++){
            for(int j = 0; j < map.getSize(); j++){
                if(map.getValueAt(j,i) == 'E'){
                    setPosition(j,i);
                }
            }
        }
    }

}

