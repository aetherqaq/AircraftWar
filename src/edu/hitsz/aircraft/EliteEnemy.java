package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.prop.*;


import java.util.LinkedList;
import java.util.List;

/**
 * 精英敌机
 * 可射击
 * 向下同时左右移动
 *
 * @author hitsz
 */
public class EliteEnemy extends AbstractEnemy {

    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 1;

    /**
     * 子弹伤害
     */
    private int power = 30;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = 1;

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction*2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction*5;
        BaseBullet bullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
            res.add(bullet);
        }
        return res;
    }

    @Override
    public List<BaseProp> generateProp(){
        double rand = Math.random();
        List<BaseProp> res = new LinkedList<>();
        PropFactory propFactory;
        BaseProp prop;
        int x = this.getLocationX();
        int y = this.getLocationY();
        if(rand < 0.1){
            return res;
        }
        else if(rand < 0.4){
            propFactory = new BloodFactory(x,y);
            prop = propFactory.createProp();
            res.add(prop);
        }
        else if(rand < 0.7){
            propFactory = new BombFactory(x,y);
            prop = propFactory.createProp();
            res.add(prop);
        }
        else{
            propFactory = new BulletFactory(x,y);
            prop = propFactory.createProp();
            res.add(prop);
        }
        return res;
    }

    /**
     * 精英机随机左右移动
     */
    public void forward() {
        super.forward();
        if(Math.random()<0.05){
            speedX *= -1;
        }
    }
}
