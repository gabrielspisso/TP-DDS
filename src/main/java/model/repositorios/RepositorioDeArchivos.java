package model.repositorios;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;

public class RepositorioDeArchivos implements RepositorioDeArchivosInterfaz {

	private static RepositorioDeArchivos instance = new RepositorioDeArchivos();

	private String accessKey = "AKIAIW3AT7SDIZFIPOIA";

	private String secretKey = "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";

	private String bucket = "bucket name";

	BasicAWSCredentials creds = new BasicAWSCredentials(accessKey, secretKey); 

	private AmazonS3 s3Client = AmazonS3ClientBuilder.standard()

													 .withCredentials(new AWSStaticCredentialsProvider(creds))

													 .withRegion("sa-east-1")

													 .build();


	public RepositorioDeArchivos() {}


	public static RepositorioDeArchivos getInstance() {

		return instance;

	}


	public List<File> obtenerTodos() {

		ListObjectsRequest request = new ListObjectsRequest().withBucketName(this.bucket);


		ObjectListing objectListing = this.s3Client.listObjects(request);


		return objectListing.getObjectSummaries()

						 .stream()

						 .map(unSumary -> this.descargarArchivo(unSumary.getKey()))

							.collect(Collectors.toList());


	}


	public File descargarArchivo(String nombreDelArchivo) {

		File localFile = new File(nombreDelArchivo);

		ObjectMetadata object = this.s3Client.getObject(new GetObjectRequest(this.bucket, nombreDelArchivo), localFile);
		
		
		return localFile;

	}
	public Date ultimaModificacionDelArchivo(String nombreDelArchivo) {

		File localFile = new File(nombreDelArchivo);

		ObjectMetadata object = this.s3Client.getObject(new GetObjectRequest(this.bucket, nombreDelArchivo), localFile);
		
		
		return object.getLastModified();

	}


	public void testBucket() {

		this.bucket = "cuentastest";

	}


	public void defaultBucket() {

		this.bucket = "trabajopracticodds";

	}

}