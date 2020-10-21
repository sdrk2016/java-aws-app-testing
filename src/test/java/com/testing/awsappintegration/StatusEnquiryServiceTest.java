package com.testing.awsappintegration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StatusEnquiryServiceTest {

	private static LambdaClient awsLambda;
    private static String functionName="";
    private static String completeFunctionName="";
    private static String role="";
    private static String handler="";

    @BeforeAll
    public static void setUp() throws IOException, URISyntaxException {

        Region region = Region.US_EAST_1;
        awsLambda = LambdaClient.builder()
                .region(region)
                .build();

        try (InputStream input = StatusEnquiryServiceTest.class.getClassLoader().getResourceAsStream("application.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            // Populate the data members required for all tests
            functionName = prop.getProperty("functionName");
            completeFunctionName = prop.getProperty("completeFunctionName");
            role = prop.getProperty("role");
            handler = prop.getProperty("handler");
            completeFunctionName = prop.getProperty("completeFunctionName");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    @Order(1)
    public void whenInitializingAWSService_thenNotNull() {
        assertNotNull(awsLambda);
        System.out.println("Test 1 passed");
    }
    @Test
    @Order(2)
    public void ListLambdaFunctions() {
        StatusEnquiryMicroservice.statusEnquiryByCam(awsLambda,functionName);
        System.out.println("Test 2 passed");
    }

}
