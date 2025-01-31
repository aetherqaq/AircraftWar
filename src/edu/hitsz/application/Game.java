package edu.hitsz.application;

import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.music.*;
import edu.hitsz.prop.BaseProp;
import edu.hitsz.prop.PropBomb;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public abstract class Game extends JPanel {

    private int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    private int gameLevel = 0;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private int timeInterval = 40;

    private final HeroAircraft heroAircraft;
    protected final List<AbstractEnemy> enemyAircrafts;
    private final List<BaseBullet> heroBullets;
    private final List<BaseBullet> enemyBullets;
    private final List<BaseProp> props;
    private final List<MusicThread> musicThreads;

    /**
     * 屏幕中出现的敌机最大数量
     */
    protected int enemyMaxNumber = 5;

    /**
     * 当前得分
     */
    private int score = 0;
    /**
     * 当前时刻
     */
    private int time = 0;

    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    protected int cycleDuration = 600;
    private int cycleTime = 0;

    protected double shootFrequency = 6;

    /**
     * 产生boss敌机分数
     */
    protected int bossScore = 600;

    /**
     * 游戏结束标志
     */
    private boolean gameOverFlag = false;

    protected boolean musicFlag = false;
    
    MusicThread bossBgmThread;
    private int shootcycleTime = 0;

    public Game() {
        heroAircraft = HeroAircraft.getHeroAircraft();
        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        props = new LinkedList<>();
        musicThreads = new LinkedList<>();

        /**
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

    }

    public void setGameLevel(int gameLevel){
        this.gameLevel = gameLevel;
    }

    public void setMusicFlag(boolean musicFlag){
        this.musicFlag = musicFlag;
    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public final void action() {

        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;


            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                System.out.println(time);
                // 新敌机产生
                GenerateEnemy();
                if(score >= bossScore){
                    GenerateBoss();
                }
            }

            //难度提升
            if(time%2000==0){
                increaseDifficulty();
            }

            // 飞机射出子弹
            shootAction();
            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            // 道具移动
            propsMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查英雄机是否存活
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                executorService.shutdown();
                gameOverFlag = true;
                System.out.println("Game Over!");
                if(musicFlag){
                    for(MusicThread musicThread: musicThreads){
                        musicThread.interruptPlay();
                        if(bossBgmThread!=null) bossBgmThread.interruptPlay();
                    }
                    new MusicThread("src/videos/game_over.wav",false).start();
                }
                Main.cardPanel.add(new RankingTable(score,gameLevel).getMainPanel());
                Main.cardLayout.last(Main.cardPanel);
            }

        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);
        if(musicFlag){
            MusicThread musicThread = new MusicThread("src/videos/bgm.wav",true);
            musicThread.start();
            musicThreads.add(musicThread);
        }

    }

    //***********************
    //      Action 各部分
    //***********************

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private boolean shootNewCycleJudge() {
        int shootCycle = (int)(10000/shootFrequency);
        shootcycleTime += timeInterval;
        if (shootcycleTime >= shootCycle) {
            // 跨越到新的周期
            shootcycleTime %= shootCycle;
            return true;
        }
        else return false;
    }

    private void shootAction() {
        // 敌机射击
        boolean shootFlag = shootNewCycleJudge();
        for(AbstractEnemy enemyAircraft : enemyAircrafts) {
            if(enemyAircraft instanceof BossEnemy){
                if(shootFlag){
                    enemyBullets.addAll(enemyAircraft.shoot());
                }
                continue;
            }
            else if(enemyAircraft instanceof EliteEnemy){
                if(shootFlag){
                    enemyBullets.addAll(enemyAircraft.shoot());
                }
                continue;
            }
            else if(enemyAircraft instanceof ElitePlusEnemy){
                if(shootFlag){
                    enemyBullets.addAll(enemyAircraft.shoot());
                }
                continue;
            }
            else{
                if(shootFlag){
                    enemyBullets.addAll(enemyAircraft.shoot());
                }
            }
        }

        // 英雄射击
        if(time%600==0){
            heroBullets.addAll(heroAircraft.shoot());
        }
    }

    protected abstract void increaseDifficulty();

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractEnemy enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }

    private void propsMoveAction() {
        for (BaseProp prop : props) {
            prop.forward();
        }
    }

    protected abstract void GenerateEnemy();

    protected abstract void GenerateBoss();


    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        List<AbstractEnemy> enemys = new LinkedList<>();
        for(AbstractEnemy enemyAircraft : enemyAircrafts){
            if(!enemyAircraft.notValid()) enemys.add(enemyAircraft);
        }

        // 敌机子弹攻击英雄
        for (BaseBullet bullet : enemyBullets) {
            if (bullet.notValid()) {
                continue;
            }
            if (heroAircraft.crash(bullet)) {
                // 英雄机撞击到敌机子弹
                // 英雄机损失一定生命值
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
            }
        }
        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractEnemy enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    if(musicFlag){
                        MusicThread musicThread = new MusicThread("src/videos/bullet_hit.wav",false);
                        musicThread.start();
                        musicThreads.add(musicThread);
                    }
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        // 产生道具补给，boss_bgm中止
                        props.addAll(enemyAircraft.generateProp());
                        if(enemyAircraft instanceof BossEnemy){
                            if(musicFlag) bossBgmThread.interruptPlay();
                        }
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        // 我方获得道具，道具生效
        for (BaseProp prop : props) {
            if (heroAircraft.crash(prop) || prop.crash(heroAircraft)) {
                if(musicFlag){
                    if(prop instanceof PropBomb){
                        MusicThread musicThread = new MusicThread("src/videos/bomb_explosion.wav",false);
                        musicThread.start();
                        musicThreads.add(musicThread);
                    }
                    else{
                        MusicThread musicThread = new MusicThread("src/videos/get_supply.wav",false);
                        musicThread.start();
                        musicThreads.add(musicThread);
                    }
                }
                if(prop instanceof PropBomb){
                    for(AbstractEnemy enemyAircraft : enemyAircrafts){
                        ((PropBomb) prop).addEnemy(enemyAircraft);
                    }
                    for(BaseBullet bullet : enemyBullets){
                        ((PropBomb) prop).addEnemy(bullet);
                    }
                }
                prop.active();
                prop.vanish();
            }
        }

        //获得分数
        for(AbstractEnemy enemyAircraft:enemys){
            if(enemyAircraft.notValid()){
                if(enemyAircraft instanceof MobEnemy){
                    score += 10;
                }
                else if(enemyAircraft instanceof EliteEnemy){
                    score += 20;
                }
                else if(enemyAircraft instanceof ElitePlusEnemy){
                    score += 30;
                }
                else if(enemyAircraft instanceof BossEnemy){
                    score += 50;
                }
            }
        }

    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 删除无效的道具
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        props.removeIf(AbstractFlyingObject::notValid);
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param  g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);
        paintImageWithPositionRevised(g, props);
        paintImageWithPositionRevised(g, enemyAircrafts);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }


}
