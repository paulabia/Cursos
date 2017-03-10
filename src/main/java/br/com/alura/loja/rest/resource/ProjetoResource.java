package br.com.alura.loja.rest.resource;

import br.com.alura.loja.rest.dao.ProjetoDAO;
import br.com.alura.loja.rest.modelo.Projeto;
import com.thoughtworks.xstream.XStream;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * Created by apq on 03/03/17.
 */
@Path("projeto")
public class ProjetoResource {

    /**
     * O método GET é idempotente, podemos realizar várias consulta no servidor que não terá nenhuma alteração.
     * @param id
     * @return
     */
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Projeto busca(@PathParam("id") long id){
        Projeto projeto = new ProjetoDAO().busca(id);

        return projeto;
    }

    /**
     * O método POST não é idempotente, pois realizar modificações no servidor
     * e mais de uma vez chamada a msm requisição terá alterações indesejadas no servidos.
     * @param conteudo
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response adiciona(String conteudo){
        Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
        new ProjetoDAO().adiciona(projeto);

        URI uri = URI.create("/projeto/" + projeto.getId());
        return Response.created(uri).build();
    }

    /**
     * O método DELETE é idempotente, Se eu executar 1 ou mais vezes a mesma requisição, não haverá um efeito colateral fora a remoção
     * @param id
     * @return
     */

    @Path("{id}")
    @DELETE
    public Response delete(@PathParam("id") long id){
        new ProjetoDAO().remove(id);
        return Response.ok().build();
    }

}
