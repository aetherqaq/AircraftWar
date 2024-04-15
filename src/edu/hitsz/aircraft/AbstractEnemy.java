package edu.hitsz.aircraft;

import edu.hitsz.application.Main;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.prop.BaseProp;

import java.util.List;

/**
 * 所有种类敌机的抽象父类：
 * BOSS, ELITE, MOB
 *
 * @author hitsz
 */
public abstract class AbstractEnemy extends AbstractAircraft {

    public AbstractEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    public void generateSpeedX() {

    }

    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    public abstract List<BaseProp> generateProp();

}
