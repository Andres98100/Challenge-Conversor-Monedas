package as;

import java.net.http.HttpClient;

public class ConsultarApi {

    public String realizarPeticion(Moneda moneda) {
        var direccion = "https://v6.exchangerate-api.com/v6/b18386a57fb9da28b6cac336/pair/" +
                moneda.monedaOrigen() + "/" +
                moneda.monedaDestino() + "/" +
                moneda.valor();
        HttpClient client = HttpClient.newHttpClient();
        var request = java.net.http.HttpRequest.newBuilder()
                .uri(java.net.URI.create(direccion))
                .build();
        try {
            var response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (java.io.IOException | java.lang.InterruptedException e) {
            System.out.println("Error al consultar la API" + e.getMessage());
            return null;
        }
    }

    public void consultarMoneda(Moneda moneda){
        var respuesta = realizarPeticion(moneda);
        if (respuesta != null) {
            new GeneradorArchivo().procesarRespuesta(respuesta, moneda);
        }
    }
}
