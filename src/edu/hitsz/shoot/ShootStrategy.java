package edu.hitsz.shoot;

import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 * 射击策略接口：
 * DirectShoot，ScatteringShoot，RingShoot
 *
 * @author hitsz
 */
public interface ShootStrategy {
    /**
     * 射击方法
     */
    public abstract List<BaseBullet> shoot(int locationX, int locationY, int speedX, int speedY, int power, int direction);
}
