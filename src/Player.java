
interface Player{
    void moveUp();
    void moveDown();
    void moveLeft();
    void moveRight();
    void setMap(Map map);
    Position getPosition();
}
class MyPlayer implements Player, Constants{
    protected Map map;
    protected Position miniPosition;

    public MyPlayer(Map map){
        //System.out.println(texture);
        //resizeTexture();
        this.miniPosition = new Position();
        System.out.println("MyPlayer:"+miniPosition.toString());
        this.map = map;
        //findPos();
    }

    public void findPos(){
        if(map == null) return;
        for(int i = 0; i < map.getSize(); i++){
            for(int j = 0; j < map.getSize(); j++){
                if(map.getValueAt(i, j) == 'P'){
                    miniPosition.setX(j);
                    miniPosition.setY(i);
                }
            }
        }
    }
    @Override
    public void moveUp() {
        if((miniPosition.getY()>0)){
            if((map.getValueAt(miniPosition.getX(), miniPosition.getY()-1) == ' ') || (map.getValueAt(miniPosition.getX(), miniPosition.getY()-1) == 'P') ||
                    (map.getValueAt(miniPosition.getX(), miniPosition.getY()-1) == 'T')) {
                miniPosition.setY(miniPosition.getY() - 1);
                //System.out.println("Up-"+getPosition().toString());
            }
            //else System.out.println("Invalid position");
        }
    }
    @Override
    public void moveDown() {
        if((miniPosition.getY()<map.getSize()-1)){
            if((map.getValueAt(miniPosition.getX(), miniPosition.getY()+1) == ' ') || (map.getValueAt(miniPosition.getX(), miniPosition.getY()+1) == 'P') ||
                    (map.getValueAt(miniPosition.getX(), miniPosition.getY()+1) == 'T')) {
                miniPosition.setY(miniPosition.getY() + 1);
              //  System.out.println("Down-"+getPosition().toString());
            }
            //else System.out.println("Invalid position");
        }
    }
    @Override
    public void moveLeft() {
        if((miniPosition.getX()>0)){
            if((map.getValueAt(miniPosition.getX()-1, miniPosition.getY()) == ' ') || (map.getValueAt(miniPosition.getX()-1, miniPosition.getY()) == 'P') ||
                    (map.getValueAt(miniPosition.getX()-1, miniPosition.getY()) == 'T')) {
                miniPosition.setX(miniPosition.getX() - 1);
              //  System.out.println("Left-"+getPosition().toString());
            }
            //else System.out.println("Invalid position");
        }
    }
    @Override
    public void moveRight() {
        if((miniPosition.getX()<map.getSize()-1)){
            if((map.getValueAt(miniPosition.getX()+1, miniPosition.getY()) == ' ') || (map.getValueAt(miniPosition.getX()+1, miniPosition.getY()) == 'P') ||
                    (map.getValueAt(miniPosition.getX()+1, miniPosition.getY()) == 'T')) {
                miniPosition.setX(miniPosition.getX() + 1);
              //  System.out.println("Right-"+getPosition().toString());
            }
            //else System.out.println("Invalid position");
        }
    }
    public Map getMap(){
        return map;
    }
    @Override
    public void setMap(Map map) {
        this.map = map;
        for(int i = 0; i < map.getSize(); i++){
            for(int j = 0; j < map.getSize(); j++) {
                if(map.getValueAt(j, i) == 'P'){
                    miniPosition.setX(j);
                    miniPosition.setY(i);
                }
            }
        }
    }
    @Override
    public Position getPosition() {
        return miniPosition;
    }
    public void setPosition(int x, int y){
        miniPosition.setX(x);
        miniPosition.setY(y);
    }

}