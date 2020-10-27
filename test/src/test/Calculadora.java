package test;

public class Calculadora {

    private int answer;
    private int acumulado;

    //Constructor
    public Calculadora(){
        answer = 0;
        acumulado = 0;
    }

    //creamos 2 metodos uno para sumar y otro para restar
    //en JUnit5 ya no es necesario usar public en los metodos
    public int Sumar(int a, int b) {
        answer = a + b;
        acumulado += answer;
        return answer;
    }

    public int Restar(int a, int b) {
        answer = a - b;
        return answer;
    }

    /*creamos sobrecarga para los metodos Sumar() y Restar() para un solo parametro
     *  es decir que al pasarle un parametro, este se sume o se reste al valor actual 
     *  que tenga el atributo: answer*/
    public int Sumar(int valor) {
        answer += valor;
        return answer;
    }

    public int Restar(int valor) {
        answer -= valor;
        return answer;
    }

    //metodo para limpiar o reiniciar el valor de: answer
    public void Clear() {
        answer = 0;
    }

    //creamos un getter para obtener el valor del atributo answer
    public int getAnswer() {
        return answer;
    }

    public int getAcumulado() {
        return acumulado;
    }
}
