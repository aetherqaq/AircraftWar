package edu.hitsz.aircraft;

/**
 * 敌机工厂抽象类：
 * MobFactory，EliteFactory
 *
 * @author hitsz
 */
public abstract class EnemyFactory {
    protected double multiplier = 1;
    /**
     * 敌机工厂方法
     */
    public abstract  AbstractEnemy createEnemy();
}
