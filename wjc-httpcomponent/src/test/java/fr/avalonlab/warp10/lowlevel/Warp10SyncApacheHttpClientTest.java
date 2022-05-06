package fr.avalonlab.warp10.lowlevel;

import fr.avalonlab.warp10.model.WSelector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class Warp10SyncApacheHttpClientTest {

    @Test
    void insertSomeData() {
        Warp10SyncApacheHttpClient client = new Warp10SyncApacheHttpClient("localhost:8080", "readTokenCI", "writeTokenCI", Duration.ofMinutes(1));
        List<String> elems = Arrays.asList((
                "1382441207762000/51.501988:0.005953/ some.sensor.model.humidity{xbeeId=XBee_40670F0D,moteId=53,area=1} 79.16\n" +
                "1382441237727000/51.501988:0.005953/ some.sensor.model.humidity{xbeeId=XBee_40670F0D,moteId=53,area=1} 75.87\n" +
                "1382441267504000/51.501988:0.005953/ some.sensor.model.humidity{xbeeId=XBee_40670F0D,moteId=53,area=1} 74.46\n" +
                "1382441267504000/51.501988:0.005953/ some.sensor.model.humidity{xbeeId=XBee_40670F0D,moteId=53,area=1} 73.55\n" +
                "1382441297664000/51.501988:0.005953/ some.sensor.model.humidity{xbeeId=XBee_40670F0D,moteId=53,area=1} 72.30\n" +
                "1382441327765000/51.501988:0.005953/ some.sensor.model.humidity{xbeeId=XBee_40670F0D,moteId=53,area=1} 70.73\n" +
                "1382441327765000/51.501988:0.005953/ some.sensor.model.humidity{xbeeId=XBee_40670F0D,moteId=53,area=1} 69.50\n" +
                "1382441357724000/51.501988:0.005953/ some.sensor.model.humidity{xbeeId=XBee_40670F0D,moteId=53,area=1} 68.24\n" +
                "1382441387792000/51.501988:0.005953/ some.sensor.model.humidity{xbeeId=XBee_40670F0D,moteId=53,area=1} 66.66\n" +
                "1382441387792000/51.501988:0.005953/ some.sensor.model.humidity{xbeeId=XBee_40670F0D,moteId=53,area=1} 65.73").split("\n"));
        client.ingress(elems);

        Iterator<String> stringIterator = client.fetchData(new WSelector("some.sensor.model.humidity", null, null), false, FETCH_FORMAT.FULLTSV, Instant.EPOCH, Instant.now());
        List<String> res = new ArrayList<>();
        stringIterator.forEachRemaining(res::add);
        Assertions.assertEquals(elems, res);

    }

    @Test
    void execute_warpscript() {
        Warp10SyncApacheHttpClient client = new Warp10SyncApacheHttpClient("localhost:8080", "readTokenCI", "writeTokenCI", Duration.ofMinutes(1));
        String exec = client.exec("'é' DUP SIZE");
        Assertions.assertEquals("[1,\"\\u00E9\"]", exec);
    }
}