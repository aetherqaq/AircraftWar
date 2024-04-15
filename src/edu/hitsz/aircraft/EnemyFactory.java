package edu.hitsz.aircraft;

/**
 * 敌机工厂接口：
 * MobFactory，EliteFactory
 *
 * @author hitsz
 */
public interface EnemyFactory {
    /**
     * 敌机工厂方法
     */
    public abstract  AbstractEnemy createEnemy();
}
