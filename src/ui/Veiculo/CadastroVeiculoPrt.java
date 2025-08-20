package ui.Veiculo;

import java.time.Year;
import java.util.List;

import domain.Erro;
import ui.Presenter;
import usecases.Veiculo.CadastroVeiculoCtrl;
import usecases.Veiculo.VeiculoRequest;

public class CadastroVeiculoPrt implements Presenter
{
    private CadastroVeiculoView view;
    private CadastroVeiculoCtrl controller;
    
    public CadastroVeiculoPrt(CadastroVeiculoCtrl controller, CadastroVeiculoView view)
    {
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void run()
    {
        Year anoFabricacao; 
        Integer quilometragem;
        Double valorDiaria;
        List<Erro> erros;
        
        do 
        {
            // Mostrar view de cadastro e armazena dados inseridos pelo usuário
            var data = view.readData();

            // Tratar dados
            try 
            {
                anoFabricacao = Year.parse(data.anoFabricao());
            }
            catch(Exception ex) 
            {
                anoFabricacao = null;
            }

            try 
            {
                quilometragem = Integer.parseInt(data.quilometragem());
            }
            catch(Exception ex) 
            {
                quilometragem = null;
            }

            try
            {
                valorDiaria = Double.parseDouble(data.valorDiaria());
            }
            catch(Exception ex) 
            {
                valorDiaria = null;
            }
            
            // Entrega os dados para o controller registrar
            erros = controller.cadastrarVeiculo(new VeiculoRequest(data.placa(),
                                                                data.modelo(),
                                                                anoFabricacao,
                                                                valorDiaria,
                                                                quilometragem));

            if(erros != null)
                view.setErros(erros);
            else
                view.setSucesso();

        } while(erros != null);
    }
}
