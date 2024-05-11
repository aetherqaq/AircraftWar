package edu.hitsz.prop;

import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.aircraft.HeroAircraft;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 所有种类道具的抽象父类：
 * 加血道具、火力道具、炸弹道具
 *
 * @author hitsz
 */
public abstract class BaseProp extends AbstractFlyingObject {

    protected static Thread shootThread = null;

    public BaseProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    /**
     * 道具生效方法
     *
     */
    public abstract void active();

    public void update(){};
}



