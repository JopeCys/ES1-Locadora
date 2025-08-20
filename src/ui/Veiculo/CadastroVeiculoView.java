package ui.Veiculo;

import java.util.Scanner;

import domain.Erro;

import java.util.List;

public class CadastroVeiculoView 
{
    // Lê informações do cadastro
    public VeiculoData readData()
    {
        var input = new Scanner(System.in);
		String placa, modelo, anoFabricao, valorDiaria, quilometragem;
		
		System.out.println("\n--------------------");
		System.out.println("Cadastro de Veiculo");
		System.out.println("--------------------");

        System.out.print("Placa: ");
		placa = input.nextLine();

        System.out.print("Modelo: ");
		modelo = input.nextLine();

        System.out.print("Ano de fabricação: ");
		anoFabricao = input.nextLine();

        System.out.print("Valor diária: ");
		valorDiaria = input.nextLine();

        System.out.print("Quilometragem: ");
		quilometragem = input.nextLine();

        return new VeiculoData(placa, modelo, anoFabricao, valorDiaria, quilometragem);
    }

    // Mostra erros
    public void setErros(List<Erro> erros)
    {
        System.out.println("\nErros no cadastro: ");
        for(var erro: erros)
        {
            switch(erro)
            {
                case PLACA_INVALIDA -> System.out.println("- Placa inválida!");
                case PLACA_JA_EXISTENTE -> System.out.println("- Placa já existe!");
                case MODELO_INVALIDO -> System.out.println("- Modelo inválido!");
                case ANO_FABRICACAO_INVALIDO -> System.out.println("- Ano de fabricação inválido!");
                case VALOR_DIARIA_INVALIDO -> System.out.println("- Valor diária inválido!");
                case QUILOMETRAGEM_INVALIDA -> System.out.println("- Quilometragem inválida!");
            }
        }
    }

    public void setSucesso()
    {
        System.out.println("Veiculo cadastrado com sucesso!");
    }
}
