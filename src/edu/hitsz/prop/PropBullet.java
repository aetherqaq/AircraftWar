package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.shoot.ScatteringShoot;

public class PropBullet extends BaseProp {
    public PropBullet(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void active() {
        HeroAircraft heroAircraft = HeroAircraft.getHeroAircraft();
        heroAircraft.setShootStrategy(new ScatteringShoot());
    }
}
