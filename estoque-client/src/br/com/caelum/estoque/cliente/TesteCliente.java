package br.com.caelum.estoque.cliente;

/**
 * Created by apq on 21/03/17.
 */
public class TesteCliente {

    public static void main(String [] args){
        EstoqueWS cliente = new EstoqueWS_Service().getEstoqueWSPort();

        Filtro filtro = new Filtro();
        filtro.setNome("IPhone");
        filtro.setTipo(TipoItem.CELULAR);;

        Filtros filtros = new Filtros();
        filtros.getFiltro().add(filtro);

        ListaItens lista = cliente.todosOsItens(filtros);

        System.out.println(cliente);
    }
}
