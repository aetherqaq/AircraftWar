package edu.hitsz.prop;

/**
 * 加血道具工厂
 *
 * @author hitsz
 */
public class BloodFactory extends PropFactory{
    public BloodFactory(int locationX, int locationY){
        super(locationX, locationY);
    }
    @Override
    public BaseProp createProp(){
        return new PropBlood(locationX, locationY, 0, 10);
    }
}
