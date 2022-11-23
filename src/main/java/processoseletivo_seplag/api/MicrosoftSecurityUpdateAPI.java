package processoseletivo_seplag.api;

import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntitySetIteratorRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.domain.ClientEntitySetIterator;
import org.apache.olingo.client.core.ODataClientFactory;
import org.springframework.stereotype.Component;
import processoseletivo_seplag.entity.dto.MicrosoftSecurityUpdateDTO;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class MicrosoftSecurityUpdateAPI {

  private final static String baseUrl = "https://api.msrc.microsoft.com/cvrf/v2.0";

  public List<MicrosoftSecurityUpdateDTO> updates() {
    final ODataClient client = ODataClientFactory.getClient();

    URI uri = client.newURIBuilder(baseUrl + "/updates").build();

    ODataEntitySetIteratorRequest<ClientEntitySet, ClientEntity> request = client
      .getRetrieveRequestFactory()
      .getEntitySetIteratorRequest(uri);

    request.setAccept("application/json;odata.metadata=minimal");

    ODataRetrieveResponse<ClientEntitySetIterator<ClientEntitySet, ClientEntity>> response = request.execute();
    ClientEntitySetIterator<ClientEntitySet, ClientEntity> iterator = response.getBody();

    List<MicrosoftSecurityUpdateDTO> microsoftSecurityUpdates = new ArrayList<>(Collections.emptyList());

    while (iterator.hasNext()) {
      ClientEntity clientEntity = iterator.next();

      var payload = new MicrosoftSecurityUpdateDTO(
        clientEntity.getProperty("ID").getPrimitiveValue().toString(),
        clientEntity.getProperty("Alias").getPrimitiveValue().toString(),
        clientEntity.getProperty("DocumentTitle").getPrimitiveValue().toString(),
        clientEntity.getProperty("Severity").getPrimitiveValue().toString(),
        OffsetDateTime.parse(clientEntity.getProperty("InitialReleaseDate").getPrimitiveValue().toString()),
        OffsetDateTime.parse(clientEntity.getProperty("CurrentReleaseDate").getPrimitiveValue().toString()),
        clientEntity.getProperty("CvrfUrl").getPrimitiveValue().toString()
      );

      microsoftSecurityUpdates.add(payload);
    }

    return microsoftSecurityUpdates;
  }
}