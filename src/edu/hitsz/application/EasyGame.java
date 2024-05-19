package edu.hitsz.application;

import edu.hitsz.aircraft.*;

public class EasyGame extends Game{
    @Override
    protected void increaseDifficulty() {
    }

    @Override
    protected final void GenerateEnemy(){
        EnemyFactory enemyFactory;
        AbstractEnemy enemy;
        if (enemyAircrafts.size() < enemyMaxNumber) {
            double rand = Math.random();
            if(rand<0.6){
                enemyFactory = new MobFactory();
                enemy = enemyFactory.createEnemy();
                enemyAircrafts.add(enemy);
            }
            else if(rand<0.8){
                enemyFactory = new EliteFactory();
                enemy = enemyFactory.createEnemy();
                enemyAircrafts.add(enemy);
            }
            else{
                enemyFactory = new ElitePlusFactory();
                enemy = enemyFactory.createEnemy();
                enemyAircrafts.add(enemy);
            }

        }
    }

    @Override
    protected final void GenerateBoss() {
    }
}
