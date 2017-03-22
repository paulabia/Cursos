package br.com.alura.loja.soap.ws;
import br.com.alura.loja.soap.exception.AutorizacaoException;
import br.com.alura.loja.soap.modelo.Item.*;
import br.com.alura.loja.soap.modelo.usuario.TokenDao;
import br.com.alura.loja.soap.modelo.usuario.TokenUsuario;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;


@WebService
public class EstoqueWS {

	private ItemDao dao = new ItemDao();

	@WebMethod(operationName = "todosOsItens")
	@WebResult(name = "itens")
	public ListaItens getItens(@WebParam(name = "filtros") Filtros filtros) {
		System.out.println("Chamando todosItens()");
		List<Filtro> lista = filtros.getLista();
		List<Item> itensResultado = dao.todosItens(lista);
		return new ListaItens(itensResultado);
	}

	@WebMethod(operationName = "CadastrarItem")
	public Item cadastrarItem(
			@WebParam(name = "tokenUsuario", header = true) TokenUsuario token,
			@WebParam(name = "item") Item item) throws AutorizacaoException {

		if (!new TokenDao().ehValido(token)) {
			throw new AutorizacaoException("Autorizacao falhou");
		}

		System.out.println("Cadastrando " + item + ", " + token);

		new ItemValidador(item).validate();
		
		this.dao.cadastrar(item);

		return item;
	}
}