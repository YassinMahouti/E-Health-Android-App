package be.ehb.proj.basicbfpapplication.model;

import org.junit.Test;

import be.ehb.proj.basicbfpapplication.controller.Controller;
import be.ehb.proj.basicbfpapplication.view.MainActivity;

import static org.junit.Assert.*;

public class ProfileTest {
    private Profile profil = new Profile(67,165,35,0);
    private float BFP = 32.2f;
    private String message =" Your BFP is " + profil.getValueBFP() + "\n"+"You are in the category 'Obese' you have a BFP  ABOVE"+ 32 +"%.";
    @Test
    public void getValueBFP() throws Exception {
    assertEquals(BFP, profil.getValueBFP(), (float)0.1);
    }

    @Test
    public void getMessage() {
    assertEquals(message, profil.getMessage());
    }
}
