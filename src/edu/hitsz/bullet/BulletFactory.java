package edu.hitsz.bullet;

/**
 * 道具工厂抽象父类:
 * 加血道具工厂， 火力道具工厂， 炸弹道具工厂
 *
 * @author hitsz
 */
public abstract class BulletFactory {
    /**
     * x 轴坐标
     */
    protected int locationX;

    /**
     * y 轴坐标
     */
    protected int locationY;

    public BulletFactory(int locationX, int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
    }
    /**
     * 道具工厂方法
     */
    public abstract BaseBullet createBullet();
}
