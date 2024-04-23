import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Menu menu = new Menu();
        Conversion conversion = new Conversion();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al sistema tipo de cambio." + "\n");
        while (menu.opcion != 7) {
            System.out.println("Selecione la moneda a convertir en dolares " + menu.mensaje);
            menu.opcion = scanner.nextInt();

            if (menu.opcion == 1) {
                System.out.println("Ingrese el monto en ARS que desea convertir a Dolares");
                menu.monto = scanner.nextDouble();
                menu.target = "ARS";
            }else if (menu.opcion == 2) {
                System.out.println("Ingrese el monto en BOB que desea convertir a Dolares");
                menu.monto = scanner.nextDouble();
                menu.target = "BOB";
            } else if (menu.opcion == 3) {
                System.out.println("Ingrese el monto en BRL que desea convertir a Dolares");
                menu.monto = scanner.nextDouble();
                menu.target = "BRL";
            }else if (menu.opcion == 4){
                System.out.println("Ingrese el monto en CLP que desea convertir a Dolares");
                menu.monto = scanner.nextDouble();
                menu.target = "CLP";
            }else if (menu.opcion == 5){
                System.out.println("Ingrese el monto en COP que desea convertir a Dolares");
                menu.monto = scanner.nextDouble();
                menu.target = "COP";
            } else if (menu.opcion == 6) {
                System.out.println("Ingrese el monto en CRC que desea convertir a Dolares");
                menu.monto = scanner.nextDouble();
                menu.target = "CRC";
            }else{
                System.out.println("El programa se ha cerrado");
                break;
            }

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/6d9a96de45e50f1a452ed022/pair/" + menu.target + "/USD"))
                    .GET()
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            Gson gson = new Gson();
            conversion = gson.fromJson(json, Conversion.class);
            System.out.println("1 " + menu.target + " es igual a " + conversion.conversion_rate + " dolares" + "\n" +
                    menu.monto + " " + menu.target + ", es igual a: " + (menu.monto * conversion.conversion_rate) + " dolares");
            System.out.println("*************************");

        }

    }
}
