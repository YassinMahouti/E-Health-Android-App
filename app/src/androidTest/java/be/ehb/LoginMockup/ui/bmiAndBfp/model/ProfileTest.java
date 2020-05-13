package be.ehb.LoginMockup.ui.bmiAndBfp.model;


import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class ProfileTest {
    // Instantie van de klasse Profile aanmaken
    Profile profile;
    //----------------------------------------------------------------------------------------------------------------------------
    @Before
    public void setUp(){
        //-- mbv de constructur uit de klasse Profile, ga ik waarden ingeven
        // Als testwaarden gebruik ik: weight: 60(kg), height: 160(cm), age: 18 en gender 1. (1=man, 0=vrouw)
        profile = new Profile(1,new Date(),1,60.0f,160.0f,18,1,false,false);
       //start de berekening van de bfp door de methode (uit de klasse Profile) op te roepen
        profile.calculateBFP();
    }
    @Test
    public void testBerekeningBMI(){
        float expected = 23.43f;
        float actual = profile.calculateBMI(); // weight / (height*height) met height hier in meter!
        //--Vermits 2 floats gaat vergelijke, moet je een delta-operator gebruiken("geeft een foutenmarge")
        assertEquals(expected,actual,0.1f);

    }
    @Test
    public void testBerekeningBFP(){
        float expected = 16.065f; //formule kan je terugvinden in de klasse Profile, hier kort de berekening: 28.125 + 4.14 -10.8 -5.4
        float actual = profile.getValueBFP();
        assertEquals(expected, actual,0.1f);
    }

}