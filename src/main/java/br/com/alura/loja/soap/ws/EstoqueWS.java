package br.com.alura.loja.soap.ws;

import br.com.alura.loja.soap.Item.Item;
import br.com.alura.loja.soap.Item.ItemDao;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;

/**
 * Created by apq on 07/03/17.
 */
@WebService
public class EstoqueWS {

    private ItemDao dao = new ItemDao();


//    @WebMethod(operationName="todosOsItens")
//    @WebResult(name="itens")
//    @ResponseWrapper(localName = "itens")
//    public ListaItens getItens(@WebParam(name="filtros") Filtros filtros) { //cuidado, plural
//        System.out.println("Chamando getItens()");
//        List<Filtro> lista = filtros.getLista();
//        List<Item> itensResultado = dao.todosItens(lista);
//        return new ListaItens(itensResultado);
//    }

    @WebMethod(operationName="todosOsItens")
    @ResponseWrapper(localName="itens")
    @WebResult(name="item")
    @RequestWrapper(localName="listaItens")
    public List<Item> getItens() {

        System.out.println("Chamando getItens()");
        return dao.todosItens();

    }

}
