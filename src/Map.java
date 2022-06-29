

import javafx.scene.Group;

import java.util.Arrays;
import java.util.Scanner;

class Map extends Group implements Constants{
    private final int size;
    private final String[]map;


    public Map(Scanner input) throws InvalidMapException{
        String buffer;
        size = input.nextInt();
        if(size <= 0) {
            throw new InvalidMapException("Map size can not be zero");
        }
        input.nextLine();
        map = new String[size];
        for(int i = 0; i < size; i++){
            buffer = input.nextLine();
            buffer = buffer.replaceAll("\\s", "");
            if(buffer.length() != getSize()){
                throw new InvalidMapException("Not enough map elements");
            }
            for(int j = 0; j < buffer.length(); j++) {
                //System.out.print(" "+buffer.toCharArray()[j]);
                if(!(buffer.toCharArray()[j] == '0' || buffer.toCharArray()[j] == '1' || buffer.toCharArray()[j] == 'P')) {
                    throw new InvalidMapException("Not enough map elements");
                }
            }
            map[i]=buffer;
            //System.out.println();
        }
    }
    public Map(String[] strMap){

        map = strMap;
        size = strMap.length;
    }
    public int getSize(){
        return size;
    }
    public char getValueAt(int x, int y) {
        if((x >= 0 && x < size) &&(y >= 0 && y < size)) {
            return map[y].toCharArray()[x];
        }
        return 'e';
    }
    public void setValueAt(int x, int y, char c){
        StringBuilder buffer = new StringBuilder(map[y]);
        buffer.setCharAt(x,c);
        map[y] = buffer.toString();
        print();
    }
    public void print(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < map[i].length(); j++) {
                System.out.print(map[i].toCharArray()[j]+" ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }
}


class InvalidMapException extends Exception {
    InvalidMapException(String exception){
        super(exception);
    }
}