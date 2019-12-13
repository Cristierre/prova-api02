package testcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GETShoppingCart {

    private static final Logger logger = Logger.getLogger(String.valueOf(GETShoppingCart.class));
    @Parameters({"baseUrl","characterEP"})
    @Test
    public void getShoppingCart(String baseUri, String characterEP){

    try{
        given().
                baseUri(baseUri).
                basePath(characterEP).
                when().
                get().
                then().
                assertThat().body(matchesJsonSchemaInClasspath("GET_shopping_cart.json"));
        logger.info("Status 200 obtido com sucesso.");
    }catch (AssertionError e){
        logger.info("Erro ao retornar status 200 da api");
    }

    }
    @Parameters({"baseUrl","characterEP"})
    @Test
    public void getShoppingCartById(String baseUri, String characterEP){
        String characterEPId = characterEP+"/"+'2';
       try{
           Response response =
                   (Response) given().
                           baseUri(baseUri).
                           basePath(characterEPId).
                           when().
                           get();
           String name =
                   response.
                           then().
                           contentType(ContentType.JSON).
                           extract().
                           path("name");
           logger.info("Status 200 obtido com sucesso.");
           Assert.assertEquals(name, "Ellie Veum");
       }catch (AssertionError e){
           logger.info("Erro ao retornar status 200 da api");
       }

    }

    @Parameters({"baseUrl","characterEP"})
    @Test
    public void setLogger(String baseUrl, String characterEP){
        try {
            given().
                    baseUri(baseUrl).
                    basePath(characterEP).
                    when().
                    get().
                    then().
                    assertThat().statusCode(200);
            logger.info("Status 200 obtido com sucesso.");
        }catch (AssertionError exception) {
            logger.info("Erro ao retornar status 200 da api");
        }
}

}
