package a310openeuler.raspicar;

import org.junit.Test;

import static org.junit.Assert.*;

import a310openeuler.raspicar.service.PiCommutationService;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void piConnectTest(){
        PiCommutationService.connect("ws://192.168.111.162:617");
        try {
            Thread.sleep(1000);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}