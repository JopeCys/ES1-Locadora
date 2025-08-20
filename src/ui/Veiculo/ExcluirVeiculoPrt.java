package ui.Veiculo;

import java.sql.SQLException;

import ui.Presenter;
import usecases.Veiculo.ExcluirVeiculoCtrl;

/*
 * Presenter responsável por relacionar view e controller em prol de excluir um veículo
 */
public class ExcluirVeiculoPrt implements Presenter {
    private ExcluirVeiculoView view;
    private ExcluirVeiculoCtrl controller;

    public ExcluirVeiculoPrt(ExcluirVeiculoView view, ExcluirVeiculoCtrl controller) {
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void run() {
        boolean veiculoDeletado = false;
        String placaTratada;
        do {
            // Chama view e pega a placa desejada pelo usuário
            var placaVeiculo = view.lerPlaca();

            try {
                // Trata a placa do veículo (converte para maiúsculas)
                placaTratada = placaVeiculo.toUpperCase();

                // Chama o controller para executar a deleção do veículo
                veiculoDeletado = controller.excluirVeiculo(placaTratada);

                if (veiculoDeletado) {
                    view.mensagemSucesso();
                } else {
                    view.mensagemErro();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        } while (!veiculoDeletado);
    }
}

