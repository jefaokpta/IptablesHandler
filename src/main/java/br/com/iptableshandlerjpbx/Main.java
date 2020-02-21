/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iptableshandlerjpbx;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jefferson Alves Reis (jefaokpta) < jefaokpta@hotmail.com >
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            var uri = new URI("http://pbx.jpbx.com.br/jpbx/IptablesEndpoint");
            //var uri = new URI("https://sip.jpbx.com.br:8443/");
            
            var client = HttpClient.newHttpClient();
            
            var req = HttpRequest.newBuilder(uri).GET().build();
            
            var resp = client.send(req, HttpResponse.BodyHandlers.ofString());
            
            Type listType = new TypeToken<ArrayList<Invasion>>(){}.getType();
            List<Invasion> invasions = new Gson().fromJson(resp.body(), listType);
            
            if(invasions != null)
                System.out.println("TAMANHO: "+invasions.size());
            
            
        } catch (URISyntaxException ex) {
            System.out.println("ERRO URL: "+ ex.getMessage());
        } catch (IOException | InterruptedException ex) {
            System.out.println("ERRO SEND: "+ ex.getMessage());
        }
    }
    
}
