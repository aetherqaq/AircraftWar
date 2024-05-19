package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * 超级精英敌机工厂
 *
 * @author hitsz
 */
public class ElitePlusFactory extends EnemyFactory{
    public ElitePlusFactory(){
        super();
    }
    public ElitePlusFactory(double multiplier){
        super();
        this.multiplier = multiplier;
    }
    @Override
    public AbstractEnemy createEnemy(){
        return new ElitePlusEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                (int)(3*multiplier),
                (int)(6*multiplier),
                (int)(60*multiplier)
        );
    }
}
