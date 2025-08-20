package ui.Cliente;

import ui.Presenter;

import usecases.Cliente.ListarClientesCtrl;

public class ListarClientesPrt implements Presenter {

	private ListarClientesView view;
	private ListarClientesCtrl controller;
	
	public ListarClientesPrt(ListarClientesView view, ListarClientesCtrl controller) {
		super();
		this.view = view;
		this.controller = controller;
	}

	@Override
	public void run() {
		var tipoOrdenacao = view.requisitarTipoOrdenacao();
		
		var resultado = controller.recuperarTodosClientesPorOrdenacao(tipoOrdenacao);
		
		if (resultado.sucesso())
			view.mostrarClientes(resultado.valor);
		else
			view.mostrarErro();
	}
}





