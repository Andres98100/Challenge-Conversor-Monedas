package as;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private final ConsultarApi api;

    public Menu(ConsultarApi api) {
        this.api = api;
    }

    public void menu () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("***************************************************");
        System.out.println("Bienvenido/a al conversor de monedas");
        var bandera = true;
        while (bandera) {
            System.out.println("Ingrese la opcion para hacer la conversion: ");
            System.out.println("1. Dolar ==> Peso Colombiano");
            System.out.println("2. Peso Colombiano ==> Dolar");
            System.out.println("3. Dolar ==> Euro");
            System.out.println("4. Euro ==> Dolar");
            System.out.println("5. Peso Colombiano ==> Euro");
            System.out.println("6. Euro ==> Peso Colombiano");
            System.out.println("7. Salir");
            System.out.println("***************************************************");
            try {
                var opcion = scanner.nextInt();
                if (opcion >= 1 && opcion <= 6) {
                    procesarOpcion(opcion, scanner);
                } else if (opcion == 7) {
                    System.out.println("Seguro/a que desea salir? (S/N)");
                    var respuesta = scanner.next();
                    if (respuesta.equalsIgnoreCase("S")) {
                        System.out.println("Saliendo...");
                        bandera = false;
                    }
                } else {
                    System.out.println("Opcion no valida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private void procesarOpcion(int opcion, Scanner scanner) {
        String monedaOrigen;
        String monedaDestino = switch (opcion) {
            case 1 -> {
                monedaOrigen = "USD";
                yield "COP";
            }
            case 2 -> {
                monedaOrigen = "COP";
                yield "USD";
            }
            case 3 -> {
                monedaOrigen = "USD";
                yield "EUR";
            }
            case 4 -> {
                monedaOrigen = "EUR";
                yield "USD";
            }
            case 5 -> {
                monedaOrigen = "COP";
                yield "EUR";
            }
            case 6 -> {
                monedaOrigen = "EUR";
                yield "COP";
            }
            default -> throw new IllegalArgumentException("Opcion no valida");
        };
        System.out.println("Ingrese el valor en " + monedaOrigen + ": ");
        var valor = scanner.nextDouble();
        Moneda moneda = new Moneda(monedaOrigen, monedaDestino, valor, valor);
        api.consultarMoneda(moneda);
    }
}