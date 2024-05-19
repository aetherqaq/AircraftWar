package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * 普通敌机工厂
 *
 * @author hitsz
 */
public class MobFactory extends EnemyFactory{
    public MobFactory(){
        super();
    }
    public MobFactory(double multiplier){
        super();
        this.multiplier = multiplier;
    }
    @Override
    public AbstractEnemy createEnemy() {
        return new MobEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                (int)(8*multiplier),
                (int)(30*multiplier)
        );
    }
}
