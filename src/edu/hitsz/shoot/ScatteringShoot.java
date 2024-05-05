package edu.hitsz.shoot;

import edu.hitsz.bullet.*;

import java.util.LinkedList;
import java.util.List;

public class ScatteringShoot implements ShootStrategy{
    public List<BaseBullet> shoot(int locationX, int locationY, int speedX, int speedY, int power, int direction) {
        List<BaseBullet> res = new LinkedList<>();
        int shootNum = 3;
        BaseBullet bullet = null;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            if(direction==-1){
                bullet = new HeroBullet(locationX + (i*2 - shootNum + 1)*10, locationY + direction*2,
                        speedX + (i*2 - shootNum + 1), speedY + direction*5, power);
            }
            else{
                bullet = new EnemyBullet(locationX + (i*2 - shootNum + 1)*10, locationY + direction*2,
                        speedX + (i*2 - shootNum + 1), speedY + direction*5, power);
            }
            res.add(bullet);
        }
        return res;
    }
}
