package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.shoot.DirectShoot;
import edu.hitsz.shoot.ScatteringShoot;

public class PropBullet extends BaseProp {

    public PropBullet(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void active() {
        if(shootThread!=null) shootThread.interrupt();
        HeroAircraft heroAircraft = HeroAircraft.getHeroAircraft();
        Runnable r = ()->{
            heroAircraft.setShootStrategy(new ScatteringShoot());
            try {
                Thread.sleep(8*1000);
            } catch (InterruptedException e) {
                // 如果线程被中断，则停止修改射击策略
                return;
            }
            heroAircraft.setShootStrategy(new DirectShoot());
        };
        // 启动新线程并保存引用以便中断
        shootThread = new Thread(r);
        shootThread.start();
    }

}