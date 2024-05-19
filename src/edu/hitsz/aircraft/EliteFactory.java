package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * 精英敌机工厂
 *
 * @author hitsz
 */
public class EliteFactory extends EnemyFactory{
    public EliteFactory(){
        super();
    }
    public EliteFactory(double multiplier){
        super();
        this.multiplier = multiplier;
    }
    @Override
    public AbstractEnemy createEnemy(){
        return new EliteEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                (int)(3*multiplier),
                (int)(6*multiplier),
                (int)(30*multiplier)
        );
    }
}
