package comcast_rmgYantra_framework_genericUtility;

import io.restassured.response.Response;

public class JsonUtility {
	public String getJsonData(Response response, String jsonPath) {
		return response.jsonPath().get(jsonPath);

	}

}
