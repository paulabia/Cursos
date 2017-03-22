package br.com.caelum.estoque.ws;

import javax.jws.WebService;

/**
 * Created by apq on 21/03/17.
 */
@WebService(endpointInterface="br.com.caelum.estoque.ws.EstoqueWS",serviceName="EstoqueWS", portName="EstoqueWSPort")
public class EstoqueWSImpl implements EstoqueWS{

    @Override
    public ListaItens todosOsItens(Filtros filtros) {
        System.out.println("Chamando todos os Itens");
        return new ListaItens();
    }

    @Override
    public CadastrarItemResponse cadastrarItem(CadastrarItem parameters, TokenUsuario tokenUsuario) throws AutorizacaoFault {
        System.out.println("Chamando cadastarItem");
        return new CadastrarItemResponse();
    }
}
