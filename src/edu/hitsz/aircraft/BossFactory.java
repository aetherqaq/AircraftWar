package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * Boss敌机工厂
 *
 * @author hitsz
 */
public class BossFactory implements EnemyFactory{
    @Override
    public AbstractEnemy createEnemy(){
        return new BossEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                5,
                0,
                200
        );
    }
}
