package com.example.recognitionimage;

import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.rekognition.model.S3Object;

public class LabelsImage {

	public static void main(String[] args) {
		AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();
		//rekognitionClient.setRegion(Regions.US_EAST_2);
		
		String imagen1 = "foto1.jpg";
		String s3Bucket = "testrekognition01";
		
		S3Object s3Obj = new S3Object();
		s3Obj.withBucket(s3Bucket);
		s3Obj.withName(imagen1);

		
		Image img = new Image();
		img.withS3Object(s3Obj);
		
		DetectLabelsRequest request = new DetectLabelsRequest();
		request.withImage(img);
		request.withMaxLabels(10);
		request.withMinConfidence(90F);
		
		try {
			DetectLabelsResult result = rekognitionClient.detectLabels(request);
			List<Label> labels = result.getLabels();
			
			for(Label label: labels) {
				System.out.println("Label-------->" + label.getName());
				System.out.println("Confidence -->" + label.getConfidence());				
				
			}
		} catch(AmazonRekognitionException e) {
			e.printStackTrace();
		}
		
		
	}

}
