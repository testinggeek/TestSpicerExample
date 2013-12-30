import com.jayway.restassured.response.Header;

import java.io.IOException;
import java.util.Date;

import static com.jayway.restassured.RestAssured.given;

public class TSWrapper {

    private static String BASE_URL = "http://testspicer.com/restapi/v0.1/";
    private static String STRING_ENDPOINT = "string/en/";
    private static String UNICODE_STRING_ENDPOINT = "string/unicode/";
    private static String AGE_ENDPOINT = "age?above=18&below=90";
    private static String DATE_ENDPOINT = "date/";
    private static String CC_ENDPOINT = "creditcard/";
    private static String START_ENDPOINT = "starttest";
    private static String FINISH_ENDPOINT = "finishtest";

    private static String API_KEY="YOUR_API_KEY";
    private static String CONTENT_TYPE = "application/json"; //application/xml
    private static Header contentTypeHeader = new Header("Content-Type",CONTENT_TYPE);
    private static Header apiKeyHeader = new Header("api_key",API_KEY);


    public static String getARandomString(String sessionId) throws IOException {

        return given().header(contentTypeHeader).and().header(apiKeyHeader).
                get(BASE_URL+STRING_ENDPOINT).path("string");

    }

    public static String getARandomUnicodeString(String sessionId) throws IOException {

        return given().header(contentTypeHeader).and().header(apiKeyHeader).
                get(BASE_URL+UNICODE_STRING_ENDPOINT+"?sessionid="+sessionId).path("string");

    }

    public static String getARandomAge(String sessionId) throws IOException {

        return given().header(contentTypeHeader).and().header(apiKeyHeader).
                get(BASE_URL+AGE_ENDPOINT).path("year");

    }

    public static String getARandomDate(String sessionId) throws IOException {

        return given().header(contentTypeHeader).and().header(apiKeyHeader).
                get(BASE_URL+DATE_ENDPOINT+"?sessionid="+sessionId).path("year");

    }

    public static String getARandomCC(String sessionId) throws IOException {

        return given().header(contentTypeHeader).and().header(apiKeyHeader).
                get(BASE_URL+CC_ENDPOINT+"?sessionid="+sessionId).path("number");

    }

    public static String startTest(String projectName, String suiteName, String testName, String description, String functionalArea, String riskCoverage) throws IOException {
        Date dt = new Date();
        dt.toString().replaceAll(" ", "");
        String parameter = "?name="+ testName + "&executiontime="
                + dt.toString().replaceAll(" ", "") +
                "&functionalarea="+ functionalArea + "&riskcoverage=" + riskCoverage +
                "&suitename=" + suiteName + "&projectname=" + projectName +"&description=" + description;
        return given().header(contentTypeHeader).and().header(apiKeyHeader).
                get(BASE_URL+START_ENDPOINT+parameter).path("sessionId");
    }

    public static void finishTest(String sessionID, String result) throws IOException {

        String parameter = "?sessionid=" + sessionID + "result=" + result;

        given().header(contentTypeHeader).and().header(apiKeyHeader).
                get(BASE_URL+FINISH_ENDPOINT+parameter);
    }

}
