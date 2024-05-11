package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.basic.AbstractFlyingObject;

import java.util.ArrayList;
import java.util.List;

public class PropBomb extends BaseProp {

    private List<AbstractFlyingObject> enemylist = new ArrayList<>();

    public PropBomb(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    public void addEnemy(AbstractFlyingObject enemy){
        enemylist.add(enemy);
    }

    public void removeEnemy(AbstractFlyingObject enemy){
        enemylist.remove(enemy);
    }

    public void notifyAllEnemy(){
        for(AbstractFlyingObject enemy: enemylist){
            enemy.update();
        }
    }

    @Override
    public void active() {
        notifyAllEnemy();
        System.out.println("BombSupply active");
    }

}
