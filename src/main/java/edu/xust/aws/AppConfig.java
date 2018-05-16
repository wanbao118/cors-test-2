package edu.xust.aws;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.ebaysf.web.cors.CORSFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class AppConfig {
	
	@Autowired
	private WebApplicationContext wac;
	
	@Value("${app.cors.origins}")
	private String origins;
	
	@Bean("restTemplate")
	public RestTemplate restTemplate() {
		CloseableHttpClient httpClient = HttpClientBuilder.create().useSystemProperties().build();
		return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
	}

	@Bean
	public FilterRegistrationBean<CORSFilter> corsFilter(){
		FilterRegistrationBean<CORSFilter> filterBean = new FilterRegistrationBean<>();
		CORSFilter filter = new CORSFilter();
		filterBean.setFilter(filter);
		filterBean.addInitParameter(CORSFilter.PARAM_CORS_ALLOWED_ORIGINS, origins);
		filterBean.addInitParameter(CORSFilter.PARAM_CORS_ALLOWED_METHODS, CORSFilter.DEFAULT_ALLOWED_HTTP_METHODS);
		filterBean.addInitParameter(CORSFilter.PARAM_CORS_ALLOWED_HEADERS, CORSFilter.DEFAULT_ALLOWED_HTTP_HEADERS);
		filterBean.addInitParameter(CORSFilter.PARAM_CORS_EXPOSED_HEADERS, CORSFilter.DEFAULT_EXPOSED_HEADERS);
		filterBean.addInitParameter(CORSFilter.PARAM_CORS_LOGGING_ENABLED, CORSFilter.DEFAULT_LOGGING_ENABLED);
		filterBean.addInitParameter(CORSFilter.PARAM_CORS_PREFLIGHT_MAXAGE, CORSFilter.DEFAULT_PREFLIGHT_MAXAGE);
		filterBean.addInitParameter(CORSFilter.PARAM_CORS_REQUEST_DECORATE, CORSFilter.DEFAULT_DECORATE_REQUEST);
		filterBean.addInitParameter(CORSFilter.PARAM_CORS_SUPPORT_CREDENTIALS, CORSFilter.DEFAULT_SUPPORTS_CREDENTIALS);
		filterBean.setOrder(1);
		return filterBean;
		
	}
	
}
