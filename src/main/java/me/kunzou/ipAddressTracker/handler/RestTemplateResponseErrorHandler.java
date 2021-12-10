package me.kunzou.ipAddressTracker.handler;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import static org.springframework.http.HttpStatus.Series.*;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
  private static final Logger logger = LoggerFactory.getLogger(RestTemplateResponseErrorHandler.class);

  @Override
  public boolean hasError(ClientHttpResponse httpResponse) throws IOException {

    return (
      httpResponse.getStatusCode().series() == CLIENT_ERROR
        || httpResponse.getStatusCode().series() == SERVER_ERROR);
  }

  @Override
  public void handleError(ClientHttpResponse httpResponse) {
    //todo do something about it
    logger.error("I handled an error", httpResponse);
  }
}

