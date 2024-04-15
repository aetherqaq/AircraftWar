package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

public class PropBomb extends BaseProp {
    public PropBomb(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void active() {
        System.out.println("BombSupply active");
    }
}
