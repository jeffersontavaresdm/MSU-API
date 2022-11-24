package msu_api.api;

import msu_api.entity.dto.MicrosoftSecurityUpdateDTO;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntitySetIteratorRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.domain.ClientEntitySetIterator;
import org.apache.olingo.client.core.ODataClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class MicrosoftSecurityUpdateAPI {

  private final ODataClient client = ODataClientFactory.getClient();

  @Value("${msu-api.baseUrl}")
  private String baseUrl;

  public List<MicrosoftSecurityUpdateDTO> updates() {
    URI uri = client.newURIBuilder(baseUrl + "/updates").build();

    ODataEntitySetIteratorRequest<ClientEntitySet, ClientEntity> request = client
      .getRetrieveRequestFactory()
      .getEntitySetIteratorRequest(uri);

    request.setAccept("application/json");

    ODataRetrieveResponse<ClientEntitySetIterator<ClientEntitySet, ClientEntity>> response = request.execute();
    ClientEntitySetIterator<ClientEntitySet, ClientEntity> iterator = response.getBody();

    List<MicrosoftSecurityUpdateDTO> microsoftSecurityUpdates = new ArrayList<>(Collections.emptyList());

    while (iterator.hasNext()) {
      ClientEntity clientEntity = iterator.next();

      microsoftSecurityUpdates.add(clientEntityHandler(clientEntity));
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