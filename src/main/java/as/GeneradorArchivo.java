package as;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class GeneradorArchivo {

        public void procesarRespuesta(String respuesta, Moneda moneda){
                Gson gson = new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                        .setPrettyPrinting()
                        .create();

                JsonObject jsonObject = gson.fromJson(respuesta, JsonObject.class);
                double conversionRate = jsonObject.get("conversion_result").getAsDouble();

                System.out.println("El valor de "
                        + moneda.valorIngresado() + " "
                        + "[" +  moneda.monedaOrigen() + "]"
                        + " correspone a ==>> "
                        +  conversionRate
                        +"[" + moneda.monedaDestino() + "]" + "\n");
        }
}
