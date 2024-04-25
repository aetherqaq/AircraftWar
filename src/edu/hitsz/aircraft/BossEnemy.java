package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.prop.*;
import edu.hitsz.application.ImageManager;


import java.util.LinkedList;
import java.util.List;

/**
 * Boss敌机
 * 可射击
 * 悬浮于界面上方左右移动
 * 环射弹道 同时发射20颗子弹，呈环形
 * 随机掉落3个道具
 *
 * @author hitsz
 */
public class BossEnemy extends AbstractEnemy {

    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 20;

    /**
     * 子弹伤害
     */
    private int power = 30;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = 1;

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
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
        int y = this.getLocationY();
        int r = ImageManager.ELITE_ENEMY_IMAGE.getWidth() + 10;
        BaseBullet bullet;
        for(int i=0; i<shootNum; i++){
            // 多个子弹以boss中心为圆心呈圆形分散
            double theta = 2 * Math.PI / shootNum * i;
            bullet = new EnemyBullet((int) (x + r * Math.cos(theta)), (int) (y + r * Math.sin(theta)),
                    (int) (Math.cos(theta)* 5), (int) (Math.sin(theta)* 5), power);
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
    }
}
