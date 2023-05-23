package io.aleksandr.labs.snapshot;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class WireMockInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
		final WireMockConfiguration wireMockConfiguration = options()
				.port(8080)
				.usingFilesUnderClasspath("wiremock/");
		WireMockServer wireMockServer = new WireMockServer(wireMockConfiguration);
		wireMockServer.start();

		configurableApplicationContext.getBeanFactory().registerSingleton("wireMockServer", wireMockServer);

		configurableApplicationContext.addApplicationListener(applicationEvent -> {
			if (applicationEvent instanceof ContextClosedEvent) {
				wireMockServer.stop();
			}
		});
	}
}