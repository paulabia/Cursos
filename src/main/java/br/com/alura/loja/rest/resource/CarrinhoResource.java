package br.com.alura.loja.rest.resource;

import br.com.alura.loja.rest.dao.CarrinhoDAO;
import br.com.alura.loja.rest.modelo.Carrinho;
import br.com.alura.loja.rest.modelo.Produto;
import com.thoughtworks.xstream.XStream;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * Created by apq on 03/03/17.
 *
 *
 * Essa é a interface uniforme. A interface uniforme no HTTP é composta por diversas características.
 * No REST, a importância da interface uniforme é essa, são duas: para o desenvolvedor é entender onde estão essas informações,
 * e para a aplicação é que vários tipos de aplicação conseguem entender o que a requisição e o que a resposta está dizendo.
 *
 */
@Path("carrinhos")
public class CarrinhoResource {

    /**
     *  GET para trazer informações, -> curl -v http://localhost:8080/carrinhos/10
     *  POST para criar um recurso,
     *  DELETE para apagar, vou fazer uma alteração, trocar,
     *  PUT. PUT, troca o recurso por um outro recurso.
     *
     */

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Carrinho busca(@PathParam("id") long id){
        Carrinho carrinho = new CarrinhoDAO().busca(id);

        return carrinho;
    }

    /**
     * curl -v -d "CONTEUDO XML/JSON" http://localhost:8080/carrinhos

     * @param carrinho
     * @return
     */

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response adiciona(Carrinho carrinho) {
        new CarrinhoDAO().adiciona(carrinho);
        URI uri = URI.create("/carrinhos/" + carrinho.getId());
        return Response.created(uri).build();
    }

    /**
     * curl -v -X DELETE http://localhost:8080/carrinhos/1/produto/6237
     * @param id
     * @param produtoId
     * @return
     */

    @Path("{id}/produtos/{produtoId}")
    @DELETE
    public Response removeProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId) {
        Carrinho carrinho = new CarrinhoDAO().busca(id);
        carrinho.remove(produtoId);
        return Response.ok().build();
    }

    /**
     * curl -v -X PUT -H "Content-Type: application/xml" -d "CONTEUDO XML/JSON" http://localhost:8080/carrinhos/1/produtos/3467
     * @param id
     * @param produtoId
     * @param conteudo
     * @return
     */

    @Path("{id}/produtos/{produtoId}")
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response alteraProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId, String conteudo) {
        Carrinho carrinho = new CarrinhoDAO().busca(id);
        Produto produto = (Produto) new XStream().fromXML(conteudo);
        carrinho.troca(produto);
        return Response.ok().build();
    }

    /**
     * O verbo PUT é idempotente. Toda vez que executado, o resultado é o mesmo:
     * a alteração do recurso (seja ele qual for) anterior pelo recurso atual, através da representação passada pelo cliente.
     * @param id
     * @param produtoId
     * @param conteudo
     * @return
     */
    @Path("{id}/produtos/{produtoId}/quantidade")
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response alteraQuantidadeDoProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId, String conteudo) {
        Carrinho carrinho = new CarrinhoDAO().busca(id);
        Produto produto = (Produto) new XStream().fromXML(conteudo);
        carrinho.trocaQuantidade(produto);
        return Response.ok().build();
    }
}
