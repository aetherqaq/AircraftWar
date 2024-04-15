package edu.hitsz.prop;

/**
 * 炸弹道具工厂
 *
 * @author hitsz
 */
public class BombFactory extends PropFactory{
    public BombFactory(int locationX, int locationY){
        super(locationX, locationY);
    }
    @Override
    public BaseProp createProp(){
        return new PropBomb(locationX, locationY, 0, 10);
    }
}