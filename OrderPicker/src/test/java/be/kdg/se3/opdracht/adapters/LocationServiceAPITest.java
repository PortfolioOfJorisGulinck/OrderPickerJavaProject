package be.kdg.se3.opdracht.adapters;

import be.kdg.se3.opdracht.application.domain.Location;
import be.kdg.se3.opdracht.application.exceptions.AdapterException;
import be.kdg.se3.services.orderpicking.LocationServiceProxy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceAPITest {

    @InjectMocks
    private LocationServiceAPI locationServiceAPI;
    @Mock
    private LocationServiceProxy proxy;

    @Before
    public void setUp() throws IOException {
        when(proxy.get(anyString())).thenCallRealMethod();
    }

    /**
     * productID 1111111 wil generate a JSON error result ('Unknown productID')
     */
    @Test
    public void testProxyJSONException() throws AdapterException {
        Location location = locationServiceAPI.analyse("1111111");

        assertNull(location); //Returns null in case of JsonException for unknown product ID
    }

    /**
     * productID 2222222 will force an IOException to simulate a communication error
     */
    @Test
    public void testProxyIOException() {
        try {
            locationServiceAPI.analyse("2222222");
            fail("Should throw exception with message: ID 2222222 forced a communication error for testing purposes");
        } catch (Exception ex) {
            assertEquals(ex.getCause().getMessage(), "ID 2222222 forced a communication error for testing purposes");
        }
    }
}
