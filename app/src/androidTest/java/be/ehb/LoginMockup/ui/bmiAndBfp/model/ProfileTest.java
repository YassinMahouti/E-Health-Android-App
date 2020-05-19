package be.ehb.LoginMockup.ui.bmiAndBfp.model;


import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class ProfileTest {
    // Instantie van de klasse Profile aanmaken----------------------------------------------------------------------------------
    Profile profile;

    //----------------------------------------------------------------------------------------------------------------------------
    @Before
    public void setUp(){
        //--Mbv de constructur uit de klasse Profile, ga ik waarden ingeven
        //--Als testwaarden gebruik ik: weight: 60(kg), height: 160(cm), age: 18 en gender 1. (1=man, 0=vrouw)
        profile = new Profile(1,new Date(),1,60.0f,160.0f,18,1,false,false);

       //--start de berekening van de bfp door de methode (uit de klasse Profile) op te roepen
        profile.calculateBFP();
    }
    //--Test om na te gaan of de bereking van BMI(Body Mass Index) klopt, met een foutenmarge van max 0.1
    @Test
    public void testBerekeningBMI(){
        float expected = 23.43f;// bereking met rekenmachine: weight / (height*height) met height hier in meter!
        float actual = profile.calculateBMI();
        //--Vermits 2 floats vergelijken, moet je een delta-operator gebruiken("geeft een foutenmarge"): bvb van 0.1f
        assertEquals(expected,actual,0.1f);
    }
    //--Test om na te gaan of de bereking van BFP(Body Fat Percentage) klopt, met een foutenmarge van max 0.1
    @Test
    public void testBerekeningBFP(){
        float expected = 16.065f; //Bereking met rekenmachine: (formule kan je terugvinden in de klasse Profile): (28.125 + 4.14 -10.8 -5.4)
        float actual = profile.getValueBFP();
        assertEquals(expected, actual,0.1f);
    }
}