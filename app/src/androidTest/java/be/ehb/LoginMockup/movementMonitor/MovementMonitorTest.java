package be.ehb.LoginMockup.movementMonitor;

import org.junit.Test;

import be.ehb.LoginMockup.ui.movementMonitor.MovementMonitor;

import static org.junit.Assert.assertTrue;

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