import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

class Tank extends MyPlayer implements Constants{
    Node texture;
    private int direction;
    private int health;
    //Bullet bullet;
    /*Tank(){
        texture = new ImageView(new Image(new File(mainTankPath).toURI().toString()));
        texture.relocate(step+1, step+1);

    }
    Tank(int x, int y){
        super(x,y);
        texture = new ImageView(new Image(new File(mainTankPath).toURI().toString()));
        //System.out.println("Char pos:  " + getPosition().toString());
        texture.relocate(miniPosition.getX()*step, miniPosition.getY()*step);
    }*/
    Tank(Map map){
        super(map);
        //System.out.println("Char pos:  " + getPosition().toString());
        texture = new ImageView(new Image(new File(mainTankPath).toURI().toString()));
        texture.relocate(miniPosition.getX()*step,miniPosition.getY()*step);
    }

    public void setPosition(int x, int y){
        texture.relocate(x*step,y*step);
        //bullet.setPosition(x,y);
        miniPosition.setXY(x,y);
    }
    @Override
    public void moveUp(){
        super.moveUp();
        texture.relocate(miniPosition.getX()*step,miniPosition.getY()*step);
        setDirection(0);
    }
    @Override
    public void moveRight(){
        super.moveRight();
        texture.relocate(miniPosition.getX()*step,miniPosition.getY()*step);
        setDirection(1);
    }
    @Override
    public void moveDown(){
        super.moveDown();
        texture.relocate(miniPosition.getX()*step,miniPosition.getY()*step);
        setDirection(2);
    }
    @Override
    public void moveLeft(){
        super.moveLeft();
        texture.relocate(miniPosition.getX()*step,miniPosition.getY()*step);
        setDirection(3);
    }
    public void setHealth(int health){
        this.health = health;
    }
    public int getHealth(){
        return this.health;
    }
    @Override
    public Position getPosition() {
        return miniPosition;
    }
    public void setPosition(Position position) {
        this.miniPosition = position;
    }
    public int getDirection() {
        return direction;
    }
    public void setDirection(int direction) {
        texture.setRotate(direction*90);
        this.direction = direction;
    }
    public Node getTexture() {
        return texture;
    }
    public void setTexture(ImageView texture) {
        this.texture = texture;
    }
}
