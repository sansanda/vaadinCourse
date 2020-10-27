package mp;

import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import test.Calculadora;

//utilizaremos la libreria que nos permite parametrizar nuestros Tests
@RunWith(JUnitParamsRunner.class)
public class EjemploParametrizacionTest extends CoreMatchers {




  //Creamos la lista de grupos de parametros de entrada para las pruebas
	@Parameters
  static Iterable<Object[]> getData(){
       return Arrays.asList(new Object[][]{
          {3, 2, 5}, {2, 2, 4}, {5, 3, 8}, {5, 5, 10}
      });
  }


  /*utilizaremos las notaciones para recrear el uso de la suma en la calculadora,
   * usaremos un acumulador que sume todos los resultados durante las pruebas*/


  static int total; //aqui guardaremos el acumulado de las operaciones
  static Calculadora calc; //declaramos un objeto de tipo: calculadora 

  //Instanciamos la calculadora
  @BeforeAll
  static void InstanciarCalculadora() {
      calc = new Calculadora();
  }

  //Con este metodo limpiaremos el resultado de la calculadora antes de cada @Test
  @BeforeEach
  void LimpiarResultado() {
      calc.Clear();
  }

  //Pruebas TestSumar(), TestSumar1()
  @Test
  @Parameters(method = "getData")
  void testSumar(int a, int b, int exp) {
  	Calculadora calc = new Calculadora();
      //esta es la forma de hacer la asercion sin matchers de hamcrest
      //assertEquals("Error", exp, calc.Sumar(a, b)); 

      //forma con matchers de hamcrest (Mas entendible)
      assertThat("Error, no es el resutado esperado", calc.Sumar(a, b), equalTo(exp)); 

      //acumulado de este Test debe ser: 5+4+8+10=27
  }

  //tambien podemos pasar los parametros directamente a un Test así:
  @Test
  @Parameters({"2, 2, 4", "3, 3, 6", "4, 4, 8", "5, 5, 10"})
  void testSumar1(int a, int b, int exp) {
      assertThat("Error, no es el resultado esperado", calc.Sumar(a, b), equalTo(exp));
      //acumulado de este Test debe ser: 4+6+8+10=28
  }

  //Incrementamos el valor del resultado luego de cada @Test para llevar el acumulado
  @AfterEach
  void ContarAcumulado() {
      total += calc.getAcumulado();
      System.out.println("acumulado parcial: " + total);
  }

  //mostramos el acumulado de todas las operaciones una vez terminados todos los @Tests
  @AfterAll
  static void MostrarTotal() {
      System.out.println("El acumulado total de las pruebas es: " + total);
  }
}
