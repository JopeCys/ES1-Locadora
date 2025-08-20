package ui.Locacao;

import java.util.ArrayList;
import java.util.List;

import domain.Erro;

import ui.Presenter;

import usecases.Locacao.CadastroLocacaoCtrl;
import usecases.Locacao.LocacaoRequest;

public class CadastroLocacaoPrt implements Presenter{

    private CadastrarLocacaoView view;
    private CadastroLocacaoCtrl controller;

    public CadastroLocacaoPrt(CadastrarLocacaoView view, CadastroLocacaoCtrl controller)
    {
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void run()
    {
        List<Erro> erros = new ArrayList<>();
        Long cpf;
        String placa;
        
        do
        {   
            // Mostrar view para ler dados
            var data = view.readData();
            
            // Tratar dados
            try
            {
                cpf = Long.parseLong(data.cpf());
            } 
            catch(Exception e)
            {
                cpf = null;
            }

            placa = data.placa().toUpperCase();

            // Registra dados no controller e verifica resultado e erros
            erros = controller.cadastrarLocacao(new LocacaoRequest(cpf, placa));

            if(erros != null)
                view.setErros(erros);
            else
                view.setSucesso();
        
        } while(erros != null);
    
    }
}
