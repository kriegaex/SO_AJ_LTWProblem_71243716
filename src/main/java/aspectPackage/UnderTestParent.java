package aspectPackage;

public class UnderTestParent {
  @SLA(tiempoEsperado = 2)
  public void doSomething(long spendTimeMillis, RuntimeException toThrow) {
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
