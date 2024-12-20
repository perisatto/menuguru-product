package com.perisatto.fiapprj.menuguru_product.bdd;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.perisatto.fiapprj.menuguru_product.infra.controllers.dtos.CreateProductRequestDTO;
import com.perisatto.fiapprj.menuguru_product.infra.controllers.dtos.CreateProductResponseDTO;
import com.perisatto.fiapprj.menuguru_product.infra.controllers.dtos.GetProductResponseDTO;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class StepDefinitions {

	private Response response;
	private List<CreateProductRequestDTO> createProductRequests = new ArrayList<>();
	private CreateProductResponseDTO createProductResponse;
	private final String ENDPOINT_CUSTOMER_API = "http://localhost:8080/menuguru-products/v1/products";
	private Double newPrice;
	
    @DataTableType
    public CreateProductRequestDTO customerEntry(Map<String, String> entry) {
    	CreateProductRequestDTO productEntry = new CreateProductRequestDTO();
    	productEntry.setName(entry.get("name"));
    	productEntry.setProductType(entry.get("productType"));
    	productEntry.setDescription(entry.get("description"));
    	productEntry.setPrice(Double.parseDouble(entry.get("price")));
    	productEntry.setImage(entry.get("image"));
        return productEntry;
    }
    
    @Given("product has the following attributes")
    public void product_has_the_following_attributes(List<CreateProductRequestDTO> productDataTable) {
        createProductRequests = productDataTable;
    }

    @When("register a new product")
    public CreateProductResponseDTO register_a_new_product() {
		var createProductRequest = createProductRequests.get(0);
		
		response = given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(createProductRequest)
				.when()
				.post(ENDPOINT_CUSTOMER_API);
		return response.then().extract().as(CreateProductResponseDTO.class);
    }

    @Then("the product is successfully registered")
    public void the_product_is_successfully_registered() {
	    response.then()
	    .statusCode(HttpStatus.CREATED.value());
    }

    @Then("should be showed")
    public void should_be_showed() {
		response.then()
		.body(matchesJsonSchemaInClasspath("./schemas/CreateProductResponse.json"));
    }

    @Given("the product is already registered with following attributes")
    public void the_product_is_already_registered_with_following_attributes(List<CreateProductRequestDTO> productDataTable) {
    	createProductRequests = productDataTable;
    	createProductResponse = register_a_new_product();
    }

    @When("ask for product information")
    public void ask_for_product_information() {
	    response = given()
	    		.contentType(MediaType.APPLICATION_JSON_VALUE)
	    		.when()
	    		.get(ENDPOINT_CUSTOMER_API + "/{productId}", createProductResponse.getId().toString());
    }

    @Then("the product information is retrieved")
    public void the_product_information_is_retrieved() {
	    response.then()
	    .statusCode(HttpStatus.OK.value())
	    .body(matchesJsonSchemaInClasspath("./schemas/CreateProductResponse.json"));
    }

    @When("gives a new price")
    public GetProductResponseDTO gives_a_new_price() {
		newPrice = 10.0;
		
		var updateProductRequest = createProductRequests.get(0);
		
		updateProductRequest.setPrice(newPrice);
		
		response = given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(updateProductRequest)
				.when()
				.patch(ENDPOINT_CUSTOMER_API + "/{productId}", createProductResponse.getId().toString());
		return response.then().extract().as(GetProductResponseDTO.class);
    }

    @Then("updates the product information with new price")
    public void updates_the_product_information_with_new_price() {
	    response.then()
	    .statusCode(HttpStatus.OK.value())
	    .body(matchesJsonSchemaInClasspath("./schemas/CreateProductResponse.json"))
	    .body("price", equalTo(Float.parseFloat(newPrice.toString())));
    }
}
