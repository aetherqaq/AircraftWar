package edu.hitsz.prop;

import edu.hitsz.basic.AbstractFlyingObject;

import java.util.ArrayList;
import java.util.List;

public class PropBomb extends BaseProp {

    private List<AbstractFlyingObject> enemyList = new ArrayList<>();

    public PropBomb(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    public void addEnemy(AbstractFlyingObject enemy){
        enemyList.add(enemy);
    }

    public void removeEnemy(AbstractFlyingObject enemy){
        enemyList.remove(enemy);
    }

    public void notifyAllEnemy(){
        for(AbstractFlyingObject enemy: enemyList){
            enemy.update();
        }
    }

    @Override
    public void active() {
        notifyAllEnemy();
        System.out.println("BombSupply active");
    }

}
