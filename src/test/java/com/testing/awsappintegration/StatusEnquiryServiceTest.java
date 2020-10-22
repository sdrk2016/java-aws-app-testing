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
    
    @BeforeAll
    public static void setUp() throws IOException, URISyntaxException {

        Region region = Region.AP_SOUTHEAST_2;
        awsLambda = LambdaClient.builder()
                .region(region)
                .build(); 
       
        functionName = "serverlessrepo-HelloWorldForme-south-1-MyFunction-8BXZSVPGGLPP";
    }

    @Test
    @Order(1)
    public void whenInitializingAWSService_thenNotNull() {
        assertNotNull(awsLambda);
        System.out.println("Test 1 passed");
    }
    @Test
    @Order(2)
    public void helloWorld() {
        StatusEnquiryMicroservice.statusEnquiryByCam(awsLambda,functionName);
        System.out.println("Test 2 passed");
    }
    

}
