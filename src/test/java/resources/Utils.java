package resources;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import jdk.nashorn.internal.ir.RuntimeNode;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification req = null;
    public RequestSpecification givenReq() throws FileNotFoundException {
        if(req==null) {
            PrintStream pos = new PrintStream(new FileOutputStream("logging.txt"));
            req =  new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).setContentType(ContentType.JSON)
                    .addFilter(RequestLoggingFilter.logRequestTo(pos))
                    .addFilter(ResponseLoggingFilter.logResponseTo(pos))
                    .addQueryParam("key", "qaclick123").build();
        }
        return req;
    }

    public String getGlobalValue(String key) {
        Properties prop = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("C:\\Users\\hp pc\\IdeaProjects\\ApiRestAssuredFramework\\src\\test\\java\\resources\\global.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop.getProperty(key);
    }

    public String getJsonValue(Response response, String key){
        JsonPath jsPath = new JsonPath(response.asString());
        return jsPath.get(key).toString() ;
    }
}
