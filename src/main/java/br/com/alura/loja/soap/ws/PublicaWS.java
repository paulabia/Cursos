package br.com.alura.loja.soap.ws;

import javax.xml.ws.Endpoint;

/** Serviços Web são utilizados para integrar sistemas
     SOAP é XML que trafega em cima do protocolo HTTP
     o JRE já vem com o JAX-WS (Metro) para usar SOAP
     o contrato do serviço é o WSDL que também é um XML
     uma mensagem SOAP possui um Envelope e um Body,
     na mensagem SOAP o Header é opcional
 *  Created by apq on 07/03/17.
 */
public class PublicaWS {

    public static void main(String [] args ){
        EstoqueWS implementacaoWS = new EstoqueWS();
        String URL = "http://localhost:8080/estoquews";

        //associando URL com implementacao
        Endpoint.publish(URL, implementacaoWS);

        System.out.println("EstoqueWS rodando: " + URL);
    }
}
