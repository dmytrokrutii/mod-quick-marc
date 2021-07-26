package org.folio.qm.config;

import feign.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignLoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import org.folio.qm.client.FeignInfoLogger;

@Configuration
public class FeignConfig {

  @Bean
  @ConditionalOnProperty(prefix = "folio.logging.feign", name = "enabled")
  public FeignLoggerFactory feignLoggerFactory() {
    return FeignInfoLogger::new;
  }

  @Bean
  public Logger.Level feignLoggerLevel(@Value("${folio.logging.feign.level: BASIC}") Logger.Level level) {
    return level;
  }

  @Bean
  public CommonsRequestLoggingFilter logFilter() {
    CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
    filter.setIncludeQueryString(true);
    filter.setIncludePayload(true);
    filter.setIncludeHeaders(false);
    filter.setAfterMessagePrefix("REQUEST DATA: ");
    return filter;
  }
}
