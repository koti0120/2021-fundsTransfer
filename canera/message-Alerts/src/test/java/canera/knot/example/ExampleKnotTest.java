package canera.knot.example;

import io.knotx.dataobjects.ClientRequest;
import io.knotx.dataobjects.KnotContext;
import io.knotx.junit.rule.KnotxConfiguration;
import io.knotx.junit.rule.TestVertxDeployer;
import io.knotx.reactivex.proxy.KnotProxy;
import io.reactivex.functions.Consumer;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.reactivex.core.Vertx;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;


@RunWith(VertxUnitRunner.class)
public class ExampleKnotTest {

  private static final String ADAPTER_ADDRESS = "knotx.knot.example";

  //Test Runner Rule of Verts
  private RunTestOnContext vertx = new RunTestOnContext();

  //Test Runner Rule of Knotx
  private TestVertxDeployer knotx = new TestVertxDeployer(vertx);

  //Junit Rule, sets up logger, prepares verts, starts verticles according to the config (supplied in annotation of test method)
  @Rule
  public RuleChain chain = RuleChain.outerRule(vertx).around(knotx);


  @Test
  @KnotxConfiguration("test-config.json")
  public void integrationTestToBeWrittenHere(TestContext context) {
    callKnotWithAssertions(context, payload(),
        adapterResponse -> {
          // assertions here
        },
        error -> context.fail(error.getMessage()));
  }

  private void callKnotWithAssertions(TestContext context, KnotContext knotContext,
      Consumer<KnotContext> onSuccess,
      Consumer<Throwable> onError) {
    Async async = context.async();

    KnotProxy proxy = KnotProxy.createProxy(new Vertx(vertx.vertx()), ADAPTER_ADDRESS);

    proxy.rxProcess(knotContext)
        .doOnSuccess(onSuccess)
        .subscribe(
            success -> async.complete(),
            onError
        );
  }

  private KnotContext payload() {
    return new KnotContext().setClientRequest(new ClientRequest());
  }

}