
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

class Bullet extends Thread implements Constants{
    private ImageView texture;
    private Position position;
    private int direction;

    Bullet(Position position){
        this.texture = new ImageView(new Image(new File(bulletPath).toURI().toString()));
        setPosition(position);
        texture.relocate(position.getX()*step,position.getY()*step);
        //System.out.println(position);
        direction = 0;
    }
    public void setPosition(Position pos){
        //int x = 0, y= 0;
        this.position = pos;
        //x = pos.getX()*step;
        //y = pos.getY()*step;
        //texture.relocate(x,y);
    }
    public Position getPosition(){
        return this.position;
    }
    public void setPosition(int x, int y){
        position.setXY(x,y);
        texture.relocate(x*step,y*step);
    }
    public void setTexture(ImageView image){
        this.texture = image;
        this.texture.setFitWidth(step);
        this.texture.setFitHeight(step);
    }
    public Node getTexture(){
        return texture;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.getTexture().setRotate(90*direction);
        this.direction = direction;
    }
}
