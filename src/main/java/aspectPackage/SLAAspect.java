package aspectPackage;

import env.EnvironmentConsumer;
import io.github.cdimascio.dotenv.Dotenv;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import static java.lang.System.currentTimeMillis;

@Aspect
public class SLAAspect {
  private static Dotenv settings = EnvironmentConsumer.getInstance("settings");
  private static long timeout = Long.parseLong(settings.get("Timeout"));

  @Around("execution(* *(..)) && @annotation(sla)")
  public Object aroundTiempos(ProceedingJoinPoint jp, SLA sla) throws Throwable {
    long dtFechaInicial = currentTimeMillis();
    Object result = jp.proceed();
    long tiempoTotal = currentTimeMillis() - dtFechaInicial;
    if (tiempoTotal > sla.tiempoEsperado() * 1000)
      throw new Exception(String.format(
        "Step timeout, method SLA of %d s exceeded, actual was %.2f s",
        sla.tiempoEsperado(), tiempoTotal / 1000.0
      ));
    if (tiempoTotal > timeout * 1000)
      throw new Exception(String.format(
        "Step timeout, global SLA of %d s exceeded, actual was %.2f s",
        timeout, tiempoTotal / 1000.0
      ));
    return result;
  }
}
