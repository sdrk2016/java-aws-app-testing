package com.testing.awsappintegration;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;
import software.amazon.awssdk.services.lambda.model.LambdaException;

public class StatusEnquiryMicroservice {

	 public static void statusEnquiryByCam(LambdaClient awsLambda,String functionName) {

		 InvokeResponse res = null ;
	        try {
	        	SdkBytes payload = SdkBytes.fromUtf8String("{\n" +
	                    " \"Hello \": \"world\",\n" +
	                    " \"countryCode\": \"AU\"\n" +
	                    "}" ) ;

	            //Setup an InvokeRequest
	            InvokeRequest request = InvokeRequest.builder()
	                    .functionName(functionName)
	                    .payload(payload)
	                    .build();

	            //Invoke the Lambda function
	            res = awsLambda.invoke(request);
	            String value = res.payload().asUtf8String() ;
	            System.out.println(value);

	        } catch(LambdaException e) {
	            System.err.println(e.getMessage());
	            System.exit(1);
	        }
	        // snippet-end:[lambda.java2.list.main]
	    }
}
