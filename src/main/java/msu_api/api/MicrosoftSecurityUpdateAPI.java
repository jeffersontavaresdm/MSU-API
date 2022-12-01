package msu_api.api;

import msu_api.entity.dto.MicrosoftSecurityUpdateDTO;
import msu_api.utils.LoggerUtils;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.ODataClientErrorException;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntitySetIteratorRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.domain.ClientEntitySetIterator;
import org.apache.olingo.client.core.ODataClientFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class MicrosoftSecurityUpdateAPI {

  private final Logger logger = LoggerUtils.loggerFor(this);

  private final ODataClient client = ODataClientFactory.getClient();

  @Value("${msu-api.baseUrl}")
  private String baseUrl;

  public List<MicrosoftSecurityUpdateDTO> getSecurityUpdates() {
    URI uri = client.newURIBuilder(baseUrl + "/updates").build();

    ODataEntitySetIteratorRequest<ClientEntitySet, ClientEntity> request = client
      .getRetrieveRequestFactory()
      .getEntitySetIteratorRequest(uri);

    request.setAccept("application/json");

    ODataRetrieveResponse<ClientEntitySetIterator<ClientEntitySet, ClientEntity>> response = null;
    try {
      response = request.execute();
    } catch (ODataClientErrorException exception) {
      var error = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
      error.setTitle(exception.getStatusLine().getReasonPhrase());
      error.setDetail(exception.getODataError().toString());

      logger.error("Error executing request. Error: {}", error);
    }

    List<MicrosoftSecurityUpdateDTO> microsoftSecurityUpdates = new ArrayList<>();

    if (response != null) {
      ClientEntitySetIterator<ClientEntitySet, ClientEntity> iterator = response.getBody();

      while (iterator.hasNext()) {
        ClientEntity clientEntity = iterator.next();
        microsoftSecurityUpdates.add(clientEntityHandler(clientEntity));
      }
    }

    return microsoftSecurityUpdates;
  }

  private static MicrosoftSecurityUpdateDTO clientEntityHandler(ClientEntity clientEntity) {
    return new MicrosoftSecurityUpdateDTO(
      clientEntity.getProperty("ID").getPrimitiveValue().toString(),
      clientEntity.getProperty("Alias").getPrimitiveValue().toString(),
      clientEntity.getProperty("DocumentTitle").getPrimitiveValue().toString(),
      clientEntity.getProperty("Severity").getPrimitiveValue().toString(),
      OffsetDateTime.parse(clientEntity.getProperty("InitialReleaseDate").getPrimitiveValue().toString()),
      OffsetDateTime.parse(clientEntity.getProperty("CurrentReleaseDate").getPrimitiveValue().toString()),
      clientEntity.getProperty("CvrfUrl").getPrimitiveValue().toString()
    );
  }
}