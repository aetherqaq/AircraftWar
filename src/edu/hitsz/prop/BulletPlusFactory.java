package edu.hitsz.prop;

/**
 * 超级火力道具工厂
 *
 * @author hitsz
 */
public class BulletPlusFactory extends PropFactory{
    public BulletPlusFactory(int locationX, int locationY){
        super(locationX, locationY);
    }
    @Override
    public BaseProp createProp(){
        return new PropBulletPlus(locationX, locationY, 0, 10);
    }
}
