package canera.knot.example;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true, publicConverter = false)
class ExampleKnotOptions {

  private String address;

  private String secret;

  public ExampleKnotOptions(JsonObject config) {
    address = config.getString("address");
    secret = config.getString("secret");
  }

  String getAddress() {
    return address;
  }

  public String getSecret() {
    return secret;
  }
}