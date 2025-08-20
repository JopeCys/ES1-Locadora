package ui.Veiculo;

import java.util.List;
import java.util.Scanner;

import domain.Veiculo.Veiculo;

public class ListarVeiculosView {

    public char requisitarTipoOrdenacao() 
    {
        Scanner input = new Scanner(System.in);
        char tipoOrdenacao = '0';
        boolean ordenacaoValida = false;

        do 
        {
            System.out.println("\n--------------------");
            System.out.println("Listagem de Veículos");
            System.out.println("--------------------");

            System.out.println("Qual o tipo de ordenação desejada? (P-Placa ou M-Modelo)");
            tipoOrdenacao = input.next().charAt(0);

            if(tipoOrdenacao == 'P' || tipoOrdenacao == 'M')
                ordenacaoValida = true;
            else 
                System.out.println("Ordenação não é válida. Tente apenas 'P' ou 'M'");
            
        } while (!ordenacaoValida);

        return tipoOrdenacao;
    }

    public void mostrarVeiculos(List<Veiculo> veiculos) {
        if (veiculos.isEmpty()) {
            System.out.println("\nNão há veículos cadastrados");
        } else {
            System.out.println("\n-----------------------------------------------------------");
            System.out.println("Placa    Modelo                          Ano   Diária   Km");
            //                  AAA-9999 xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  9999  9999,99  999999
            System.out.println("-----------------------------------------------------------");

            for (var v : veiculos) {
                System.out.printf("%-8s %-30s %-4d %8.2f %6d\n",
                        v.getPlaca(),
                        v.getModelo(),
                        v.getAnoFabricacao().getValue(),
                        v.getValorDiaria(),
                        v.getQuilometragem());
            }

            System.out.println("-----------------------------------------------------------");
        }
    }

    public void mostrarErro() {
        System.out.println("Erro no acesso aos dados. Tente novamente ou procure o suporte!");
    }
}
