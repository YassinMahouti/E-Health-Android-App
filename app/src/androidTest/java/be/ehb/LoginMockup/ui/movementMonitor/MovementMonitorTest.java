package be.ehb.LoginMockup.ui.movementMonitor;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovementMonitorTest {

    @Test
    public void calcCalorie() {
        MovementMonitor.setCalories(100);
        float expected = 50; // 50 verbranden calories
        float output = MovementMonitor.calcCalorie(300000, 600000);

        assertTrue(expected ==  output);
    }

    @Test
    public void calcDuration() {
        float exptected = 5; //5 minuten gespendeeerd
        float output = MovementMonitor.calcDuration(300000, 600000);

        assertTrue(exptected == output);
    }
}