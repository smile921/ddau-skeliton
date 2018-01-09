/**
 * 
 */
package com.ddau;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.UiConfiguration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author frere
 * swagger-ui.html
 */
public class SwaggerConfig {
	@Bean
	public Docket petApi() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().pathMapping("/").directModelSubstitute(Locale.class, String.class)
				.genericModelSubstitutes(ResponseEntity.class)
				.alternateTypeRules(new AlternateTypeRule(
						typeResolver.resolve(DeferredResult.class,
								typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
						typeResolver.resolve(WildcardType.class)))
				.useDefaultResponseMessages(false);
		ResponseMessage rm = new ResponseMessageBuilder().code(500).message("500 message")
				.responseModel(new ModelRef("Error")).build();
		ArrayList<ResponseMessage> lst = new ArrayList<ResponseMessage>();
		lst.add(rm);

		ApiKey apikey = apiKey();
		List<SecurityScheme> lstss = new ArrayList<SecurityScheme>();
		lstss.add(apikey);
		SecurityContext context = securityContext();
		List<SecurityContext> seccontexts = new ArrayList<SecurityContext>();
		seccontexts.add(context);
		Parameter param = new ParameterBuilder().name("version")
				.description("公共入参 version , API 版本号").modelRef(new ModelRef("string"))
				.parameterType("query").required(true).defaultValue("1.0").build();
		List<Parameter> params = new ArrayList<Parameter>();
		params.add(param);
		docket.globalResponseMessage(RequestMethod.GET, lst).securitySchemes(lstss).securityContexts(seccontexts)
				.enableUrlTemplating(false).globalOperationParameters(params)
				.tags(new Tag("Service", "All apis relating to pets"));
		// .additionalModels(typeResolver.resolve(AdditionalModel.class));

		return docket;
	}

	@Autowired
	private TypeResolver typeResolver;

	private ApiKey apiKey() {
		return new ApiKey("mykey", "api_key", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/anyPath.*"))
				.build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		SecurityReference sr = new SecurityReference("mykey", authorizationScopes);
		ArrayList<SecurityReference> lst = new ArrayList<SecurityReference>();
		lst.add(sr);
		return lst;
	}

	@Bean
	SecurityConfiguration security() {
		return new SecurityConfiguration("test-app-client-id", "test-app-client-secret", "test-app-realm", "test-app",
				"apiKey", ApiKeyVehicle.HEADER, "api_key", "," /* scope separator */);
	}

	@Bean
	UiConfiguration uiConfig() {
		return new UiConfiguration("validatorUrl", // url
				"none", // docExpansion => none | list
				"alpha", // apiSorter => alpha
				"schema", // defaultModelRendering => schema
				UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, // enableJsonEditor => true | false
				true, // showRequestHeaders => true | false
				60000L); // requestTimeout => in milliseconds, defaults to null (uses jquery xh timeout)
	}
}
