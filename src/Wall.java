import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

abstract class Wall{
    protected ImageView texture;
    protected Position position;
    protected boolean destructible;
    public ImageView getTexture(){
        return texture;
    }
}

class BrickWall extends Wall implements Constants{
    private int health;
    BrickWall(int x, int y){

        health = 1;
        destructible = true;
        texture = new ImageView(new Image(new File(brickWallPath).toURI().toString()));
        //texture = brickWallTexture;
        position = new Position(x,y);
        texture.setLayoutX(position.getX()*step);
        texture.setLayoutY(position.getY()*step);
    }
    @Override
    public ImageView getTexture(){
        //System.out.println("Texture POS:" + texture.getLayoutX() + ", " + texture.getLayoutY());
        return texture;
    }
    public Position getPosition(){
        return position;
    }
    public void destruct(int afterTime){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(health>0) {
                    health--;
                    texture.setImage(new Image(new File(dBrickwallPath).toURI().toString()));
                }
                else if(health == 0) texture.setImage(null);
            }
        };
        timer.schedule(task,afterTime*50);
        //texture = new ImageView();
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
}
class SteelWall extends Wall implements Constants {
    SteelWall(int x, int y){
        destructible = false;
        texture = new ImageView(new Image(new File(steelWallPath).toURI().toString()));
        position = new Position(x,y);
        texture.setLayoutX(position.getX()*step);
        texture.setLayoutY(position.getY()*step);
    }
}
class TreeWall extends  Wall implements Constants{
    TreeWall(int x, int y){
        destructible = false;
        texture = new ImageView(new Image(new File(treePath).toURI().toString()));
        position = new Position(x,y);
        texture.setLayoutX(position.getX()*step);
        texture.setLayoutY(position.getY()*step);
    }
}
class Water extends Wall implements Constants{
    Water(int x, int y){
        destructible = false;
        texture = new ImageView(new Image(new File(waterPath).toURI().toString()));
        position = new Position(x,y);
        texture.setLayoutX(position.getX()*step);
        texture.setLayoutY(position.getY()*step);
    }
}