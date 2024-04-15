package edu.hitsz.prop;

/**
 * 火力道具工厂
 *
 * @author hitsz
 */
public class BulletFactory extends PropFactory{
    public BulletFactory(int locationX, int locationY){
        super(locationX, locationY);
    }
    @Override
    public BaseProp createProp(){
        return new PropBullet(locationX, locationY, 0, 10);
    }
}
