package ui.Locacao;

import java.util.List;
import java.util.Scanner;

import domain.Erro;

public class CadastrarLocacaoView 
{
    // Lê informações do cadastro
    public LocacaoData readData()
    {
        var input = new Scanner(System.in);
		String placa, cpf;
		
		System.out.println("\n--------------------");
		System.out.println("Cadastro de locação");
		System.out.println("--------------------");

        System.out.print("Placa do veiculo: ");
		placa = input.nextLine();

        System.out.print("Cpf do cliente: ");
        cpf= input.nextLine();

        return new LocacaoData(placa, cpf);
    }

    // Mostra erros
    public void setErros(List<Erro> erros)
    {
        System.out.println("\nErros no cadastro: ");
        for(var erro: erros)
        {
            switch(erro)
            {
                case CLIENTE_NAO_ENCONTRADO -> System.out.println("- Cliente não encontrado!");
                case VEICULO_NAO_ENCONTRADO -> System.out.println("- Veiculo não encontrado!");
            }
        }
    }

    public void setSucesso()
    {
        System.out.println("Locação bem-sucedida!");
    }
}
