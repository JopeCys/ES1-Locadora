package ui.Cliente;

import java.sql.SQLException;

import ui.Presenter;

import usecases.Cliente.ExcluirClienteCtrl;

/*
 * Presenter responsável por relacionar view e controller em prol de excluir um cliente
 */
public class ExcluirClientePrt implements Presenter
{
    private ExcluirClienteView view;
    private ExcluirClienteCtrl controller; 

    public ExcluirClientePrt(ExcluirClienteView view, ExcluirClienteCtrl controller)
    {
        super();
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void run()
    {   
        boolean clienteDeletado = false;
        long cpfTratado;
        do 
        {
            // Chama view e pega o cpf desejado pelo usuário
            var cpfCliente = view.lerCPF();

            try 
            {
                // Trata o CPF do cliente
                cpfTratado = Long.parseLong(cpfCliente);

                // Chama o controller para executar a deleção do cliente
                clienteDeletado = controller.excluirCliente(cpfTratado);

                if(clienteDeletado)
                {
                    view.mensagemSucesso();   
                }
                else
                {
                    view.mensagemErro();
                }
            } 
            catch (NumberFormatException ex)
            {
                System.out.println("CPF inválido! Por favor, insira apenas números.");
            }
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
        while(!clienteDeletado); 
    }
}
