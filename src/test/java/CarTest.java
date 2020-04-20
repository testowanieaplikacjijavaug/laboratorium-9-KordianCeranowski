import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CarTest {
    private Car myFerrari = mock(Car.class);

    @Test
    public void test_instance_car(){
        assertNotNull(myFerrari);
    }

    @Test
    public void test_default_behavior_needsFuel(){
        assertFalse(myFerrari.needsFuel(), "New test double should return False as boolean");
    }

    @Test
    public void test_default_behavior_temperature(){
        assertEquals(0.0, myFerrari.getEngineTemperature(), "New test double should return 0.0");
    }

    @Test
    public void test_stubbing_mock(){
        when(myFerrari.needsFuel()).thenReturn(true);
        assertTrue(myFerrari.needsFuel());
    }

    @Test
    public void test_exception(){
        when(myFerrari.needsFuel()).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> {
            myFerrari.needsFuel();
        });
    }

    @Test
    public void testVerification(){
        myFerrari.driveTo("Kartuzy");
        myFerrari.needsFuel();

        verify(myFerrari).driveTo("Kartuzy");
        verify(myFerrari).needsFuel();
        assertFalse(myFerrari.needsFuel());
    }


    // My tests

    @Test
    void testNeedsFuelTrue(){
        when(myFerrari.needsFuel()).thenReturn(true);
        assertTrue(myFerrari.needsFuel());
        verify(myFerrari).needsFuel();
    }

    @Test
    void testNeedsFuelFalse(){
        when(myFerrari.needsFuel()).thenReturn(false);
        assertFalse(myFerrari.needsFuel());
        verify(myFerrari).needsFuel();
    }

    @Test
    void testGetEngineTemperature(){
        when(myFerrari.getEngineTemperature()).thenReturn(60.0);
        assertEquals(60.f, myFerrari.getEngineTemperature());
        verify(myFerrari).getEngineTemperature();
    }

    @Test
    void testGetEngineTemperatureNotUsed(){
        when(myFerrari.getEngineTemperature()).thenReturn(60.0);
        verify(myFerrari, never()).getEngineTemperature();
    }

    @Test
    void testGetEngineTemperatureUsedMultipleTimes(){
        when(myFerrari.getEngineTemperature()).thenReturn(1000.0);

        myFerrari.getEngineTemperature();
        myFerrari.getEngineTemperature();
        myFerrari.getEngineTemperature();
        myFerrari.getEngineTemperature();

        verify(myFerrari, times(4)).getEngineTemperature();
    }

    @Test
    void needsFuelChecksTemperature(){
        when(myFerrari.getEngineTemperature()).thenReturn(90.0);
        when(myFerrari.needsFuel()).thenAnswer(invocationOnMock -> myFerrari.getEngineTemperature() > 60.0);

        assertTrue(myFerrari.needsFuel());
    }
}