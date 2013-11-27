import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import utils.HttpRequestHelper;

public class PostRequestTest {
	private static final String SYSTEM_PATH = "C:/Users/Egor/AppData/Local/Temp";
	private static final String FILE_NAME = "UploadTest.mp3";
	
	@Test
	public void postRequestShouldContainsConfirmationMessage()
			throws IllegalStateException, IOException {
		HttpRequestHelper helper = new HttpRequestHelper();
		String body = helper
				.getPostRequestBodyWithConfirmationMessage("http://localhost:8081/uploads/3405a/file");

		Assert.assertFalse("Request body is emty!", body.isEmpty());
		Assert.assertTrue("There is no confirmation message!",
				body.contains("done"));

	}

	@Test
	public void postRequestShouldContainSystemPathAndTitle()
			throws IOException {
		String systemPath = System.getProperty("java.io.tmpdir").toString();
		systemPath.replace("\\", "/");
		
		HttpRequestHelper helper = new HttpRequestHelper();

		String body = helper
				.getPostRequestBodyTitleAndPath("http://localhost:8081/uploads/3405a/title");

		Assert.assertFalse("Request body is emty!", body.isEmpty());
		//here should be a variable systemPAth, but replace function dosen't work. Don't know why.
		Assert.assertTrue("There is no system path!", body.contains(SYSTEM_PATH + "/upload")); 
		Assert.assertTrue("There is no title!", body.contains(FILE_NAME));

	}

}
