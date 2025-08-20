package ui.Veiculo;

import java.util.Scanner;

public class ExcluirVeiculoView {
    public String lerPlaca()
    {
        var input = new Scanner(System.in);
        String placa;

        System.out.println("\n--------------------");
        System.out.println("Exclusão de Veículo");
        System.out.println("--------------------");

        System.out.println("Digite a placa do veículo a ser deletado: ");
        placa = input.nextLine();

        return placa;
    }

    public void mensagemErro()
    {
        System.out.println("Veículo não encontrado!");
    }

    public void mensagemSucesso()
    {
        System.out.println("Exclusão bem-sucedida");
    }
}
