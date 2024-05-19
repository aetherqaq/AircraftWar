package edu.hitsz.application;

import edu.hitsz.aircraft.*;
import edu.hitsz.music.MusicThread;


public class HardGame extends Game{
    private int bossHp = 200;
    private double mobProbability = 0.6;
    private double eliteProbability = 0.2;
    private double multiplier = 1;
    /**
     * 计数器
     * 每计数10次增加最大敌机数量，降低boss敌机产生的得分阈值
     */
    private int count = 0;
    @Override
    protected void increaseDifficulty() {
        if(mobProbability>0){
            mobProbability -= 0.02;
        }
        if(eliteProbability<0.5){
            eliteProbability += 0.02;
        }
        multiplier += 0.02;
        shootFrequency += 0.1;
        System.out.println("提高难度 精英机概率："+(1-mobProbability)+"敌机属性提升倍率："+multiplier);
        System.out.println("敌机射击频率："+(shootFrequency)+"敌机最大数量："+enemyMaxNumber+"boss敌机产生的得分阈值"+bossScore);
        if(count<10) count++;
        else{
            enemyMaxNumber++;
            bossScore -= 50;
            cycleDuration -= 10;
            count = 0;
        }
    }
    @Override
    protected final void GenerateEnemy(){
        EnemyFactory enemyFactory;
        AbstractEnemy enemy;
        if (enemyAircrafts.size() < enemyMaxNumber) {
            double rand = Math.random();
            if(rand<mobProbability){
                enemyFactory = new MobFactory(multiplier);
                enemy = enemyFactory.createEnemy();
                enemyAircrafts.add(enemy);
            }
            else if(rand<mobProbability+eliteProbability){
                enemyFactory = new EliteFactory(multiplier);
                enemy = enemyFactory.createEnemy();
                enemyAircrafts.add(enemy);
            }
            else{
                enemyFactory = new ElitePlusFactory(multiplier);
                enemy = enemyFactory.createEnemy();
                enemyAircrafts.add(enemy);
            }

        }
    }

    @Override
    protected final void GenerateBoss() {
        EnemyFactory enemyFactory;
        AbstractEnemy enemy;
        bossScore += 600;
        enemyFactory = new BossFactory(bossHp);
        bossHp += 50;
        enemy = enemyFactory.createEnemy();
        enemyAircrafts.add(enemy);
        if(musicFlag){
            if(bossBgmThread!=null) bossBgmThread.interruptPlay();
            bossBgmThread = new MusicThread("src/videos/bgm_boss.wav",true);
            bossBgmThread.start();
        }
    }
}
