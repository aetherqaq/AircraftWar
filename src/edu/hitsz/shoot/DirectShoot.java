package edu.hitsz.shoot;

import edu.hitsz.bullet.*;

import java.util.LinkedList;
import java.util.List;

public class DirectShoot implements ShootStrategy{
    public List<BaseBullet> shoot(int locationX, int locationY, int speedX, int speedY, int power, int direction){
        List<BaseBullet> res = new LinkedList<>();
        BaseBullet bullet;
        // 子弹发射位置相对飞机位置向前偏移
        // 一个子弹
        if(direction==-1){
            bullet = new HeroBullet(locationX, locationY + direction*2, speedX, speedY+ direction*5, power);
        }
        else{
            bullet = new EnemyBullet(locationX, locationY + direction*2, speedX, speedY+ direction*5, power);
        }
        res.add(bullet);
        return res;
    }
}