package aspectPackage;

import org.testng.annotations.Test;

public class MyTest {
  private static final UnderTestParent parent = new UnderTestParent();
  private static final UnderTestChild child = new UnderTestChild();

  @Test
  public void testNoExceptionsNoTimeouts() {
    parent.doSomething(0, null);
    child.doSomething(0, null);
    child.doSomethingElse(0, null);
  }

  @Test(expectedExceptions = { Exception.class }, expectedExceptionsMessageRegExp = "Step timeout, method SLA of [0-9]+ s exceeded, .*")
  public void testMethodTimeout() {
    parent.doSomething(2500, null);
  }

  @Test(expectedExceptions = { Exception.class }, expectedExceptionsMessageRegExp = "Step timeout, global SLA of [0-9]+ s exceeded..*")
  public void testGlobalTimeout() {
    child.doSomethingElse(5500, null);
  }

  @Test(expectedExceptions = { RuntimeException.class }, expectedExceptionsMessageRegExp = "oops")
  public void testMethodExceptionTakesPrecedenceOverTimeout() {
    parent.doSomething(2500, new RuntimeException("oops"));
  }
}
