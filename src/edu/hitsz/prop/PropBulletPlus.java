package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.shoot.DirectShoot;
import edu.hitsz.shoot.RingShoot;
import edu.hitsz.shoot.ScatteringShoot;

public class PropBulletPlus extends BaseProp {
    public PropBulletPlus(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void active() {
        if(shootThread!=null) shootThread.interrupt();
        HeroAircraft heroAircraft = HeroAircraft.getHeroAircraft();
        Runnable r = ()->{
            heroAircraft.setShootStrategy(new RingShoot());
            try {
                Thread.sleep(8*1000);
            } catch (InterruptedException e) {
                // 如果线程被中断，则停止修改射击策略
                return;
            }
            heroAircraft.setShootStrategy(new DirectShoot());
        };
        shootThread = new Thread(r);
        shootThread.start();
    }
}
