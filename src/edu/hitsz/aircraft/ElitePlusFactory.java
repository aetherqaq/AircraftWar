package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * 超级精英敌机工厂
 *
 * @author hitsz
 */
public class ElitePlusFactory implements EnemyFactory{
    @Override
    public AbstractEnemy createEnemy(){
        return new ElitePlusEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                5,
                8,
                30
        );
    }
}
