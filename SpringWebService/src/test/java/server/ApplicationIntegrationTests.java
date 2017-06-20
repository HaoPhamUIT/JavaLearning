/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package server;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = "/spring-context.xml" )
public class ApplicationIntegrationTests {

    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockClient;

    @Before
    public void createClient() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
        GenericApplicationContext ctx = (GenericApplicationContext) applicationContext;
        final XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(ctx);
        definitionReader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_NONE);
        definitionReader.setNamespaceAware(true);
    }

    @Test
    public void testCountryEndpoint() throws Exception {
        Source requestPayload = new StringSource(
                "<getTestCountryRequest xmlns='http://com/phh/gs-producing-web-service'>"+
                        "<name>Poland</name>"+
                        "</getTestCountryRequest>");
        Source responsePayload = new StringSource(
                "<getTestCountryResponse xmlns='http://com/phh/gs-producing-web-service'>" +
                        "<country>" +
                        "<name>Poland</name>"+
                        "<population>38186860</population>"+
                        "<capital>Warsaw</capital>"+
                        "<currency>PLN</currency>"+
                        "<gdp>136800000</gdp>"+
                        "</country>"+
                        "</getTestCountryResponse>");

        mockClient.sendRequest(withPayload(requestPayload)).andExpect(payload(responsePayload));
    }
}