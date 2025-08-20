package ui.Locacao;

import ui.Presenter;

import usecases.Locacao.ListarLocacoesCtrl;

public class ListarLocacoesPrt implements Presenter {

    private ListarLocacoesCtrl controller;
    private  ListarLocacoesView view;

    public ListarLocacoesPrt(ListarLocacoesView view, ListarLocacoesCtrl controller) {
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void run() {
        var resultado = controller.recuperarTodasLocacoes();

        if (resultado.sucesso()) {
            view.mostrarLocacoes(resultado.valor);
        } else {
            view.mostrarErro();
        }
    }

}