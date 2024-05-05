package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.shoot.RingShoot;

public class PropBulletPlus extends BaseProp {
    public PropBulletPlus(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void active() {
        HeroAircraft heroAircraft = HeroAircraft.getHeroAircraft();
        heroAircraft.setShootStrategy(new RingShoot());
    }
}
