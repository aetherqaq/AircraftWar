package edu.hitsz.shoot;

import edu.hitsz.application.ImageManager;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class RingShoot implements ShootStrategy{
    public List<BaseBullet> shoot(int locationX, int locationY, int speedX, int speedY, int power, int direction) {
        int shootNum = 20;
        List<BaseBullet> res = new LinkedList<>();
        int r = ImageManager.ELITE_ENEMY_IMAGE.getWidth() + 10;
        BaseBullet bullet = null;
        for(int i=0; i<shootNum; i++){
            // 多个子弹以boss中心为圆心呈圆形分散
            double theta = 2 * Math.PI / shootNum * i;
            if(direction==-1){
                bullet = new HeroBullet((int) (locationX + r * Math.cos(theta)), (int) (locationY + r * Math.sin(theta)),
                        (int) (Math.cos(theta)* 5), (int) (Math.sin(theta)* 5), power);
            }
            else{
                bullet = new EnemyBullet((int) (locationX + r * Math.cos(theta)), (int) (locationY + r * Math.sin(theta)),
                        (int) (Math.cos(theta)* 5), (int) (Math.sin(theta)* 5), power);
            }
            res.add(bullet);
        }
        return res;
    }
}
