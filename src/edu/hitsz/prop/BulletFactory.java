package edu.hitsz.prop;

public class BulletFactory extends PropFactory{
    public BulletFactory(int locationX, int locationY){
        super(locationX, locationY);
    }
    @Override
    public BaseProp createProp(){
        return new PropBullet(locationX, locationY, 0, 10);
    }
}
