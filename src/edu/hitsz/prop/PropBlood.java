package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

public class PropBlood extends BaseProp {

    /**
     * 回复血量
     */
    private int cure = 50;
    public PropBlood(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void active() {
        HeroAircraft heroAircraft = HeroAircraft.getHeroAircraft();
        heroAircraft.increaseHp(cure);
    }
}
