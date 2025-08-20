package ui.Veiculo;

import ui.Presenter;
import usecases.Veiculo.ListarVeiculosCtrl;

public class ListarVeiculosPrt implements Presenter {

    private ListarVeiculosView view;
    private ListarVeiculosCtrl controller;

    public ListarVeiculosPrt(ListarVeiculosView view, ListarVeiculosCtrl controller) 
    {
        this.view = view;
        this.controller = controller;
    }


    @Override
    public void run() 
    {
        var tipoOrdenacao = view.requisitarTipoOrdenacao();
        var resultado = controller.recuperarTodosVeiculosPorOrdenacao(tipoOrdenacao);

        if (resultado.sucesso()) 
            view.mostrarVeiculos(resultado.valor);
        else 
            view.mostrarErro();
    }
}
