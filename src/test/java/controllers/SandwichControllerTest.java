package controllers;

import be.ucll.da.dentravak.Application;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.HttpHeaders;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SandwichControllerTest {
        @LocalServerPort
        private int port;
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();

        @Test
        public void testGetSandwiches() throws JSONException {

            HttpEntity<String> entity = new HttpEntity<String>(null, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    createURLWithPort("/den-travak/sandwiches"),
                    HttpMethod.GET, entity, String.class);

            String expected = "[{\"id\":\"c8a975ce-a924-4013-9104-8fdab1dbbe36\",\"name\":\"Smos Kaas\",\"ingredients\":\"Kaas, mayonaise, tomaten, sla en eieren.\",\"price\":3.25},{\"id\":\"8c6613d3-bbca-4aac-959b-35296dededa2\",\"name\":\"Smos Hesp\",\"ingredients\":\"Kaas, hesp, mayonaise, tomaten, sla en eieren.\",\"price\":3.50}]";

            JSONAssert.assertEquals(expected, response.getBody(), false);
        }

        private String createURLWithPort(String uri) {
            return "http://localhost:" + port + uri;
        }
}
