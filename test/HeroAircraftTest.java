import edu.hitsz.aircraft.*;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.BaseBullet;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HeroAircraftTest {
    private HeroAircraft heroAircraft = HeroAircraft.getHeroAircraft();
    private MobEnemy mobEnemy = new MobEnemy(heroAircraft.getLocationX(),heroAircraft.getLocationY(),0,0,100);
    @BeforeAll
    static void beforeAll() {
        System.out.println("**--- Executed once before all test methods in this class ---**");
    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("Test decreaseHp method")
    @ParameterizedTest
    @CsvSource({
            "100,900","1100,0"
    })
    void decreaseHp(int decrease, int res) {
        Assumptions.assumeTrue(decrease>=0);
        Assumptions.assumeTrue(heroAircraft!=null);
        heroAircraft.decreaseHp(decrease);
        assertEquals(heroAircraft.getHp(),res);
        System.out.println("decreaseHp test pass");
    }

    @DisplayName("Test crash method")
    @org.junit.jupiter.api.Test

    void crash() {
        Assumptions.assumeTrue(mobEnemy!=null);
        Assumptions.assumeTrue(heroAircraft!=null);
        assertTrue(heroAircraft.crash(mobEnemy));
        System.out.println("crash test pass");
    }

    @DisplayName("Test shoot method")
    @org.junit.jupiter.api.Test
    void shoot() {
        Assumptions.assumeTrue(heroAircraft!=null);
        List<BaseBullet> bullets = heroAircraft.shoot();
        assertFalse(bullets.isEmpty());
        System.out.println("shoot test pass");
    }
}
