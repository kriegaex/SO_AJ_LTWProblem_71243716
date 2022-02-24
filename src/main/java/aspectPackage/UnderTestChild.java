package aspectPackage;

public class UnderTestChild extends UnderTestParent {
  @SLA(tiempoEsperado = 10)
  public void doSomethingElse(long spendTimeMillis, RuntimeException toThrow) {
    System.out.println("Doing something");
    try {
      Thread.sleep(spendTimeMillis);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
    if (toThrow != null)
      throw toThrow;
  }
}
