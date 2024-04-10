package edu.hitsz.prop;

public class BombFactory extends PropFactory{
    public BombFactory(int locationX, int locationY){
        super(locationX, locationY);
    }
    @Override
    public BaseProp createProp(){
        return new PropBomb(locationX, locationY, 0, 10);
    }
}