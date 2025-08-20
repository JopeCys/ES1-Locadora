package ui.Locacao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

import domain.Locacao.Locacao;

public class ListarLocacoesView {

    public void mostrarLocacoes(List<Locacao> locacoes) {
        if (locacoes.isEmpty()) {
            System.out.println("\nNão há locações cadastradas.");
        } else {
            System.out.println("\n----------------------------------------------------------------------------------------------------");
            System.out.println("CPF            Nome                           Placa     Modelo                           Data/Hora   ");
            //                  999.999.999-99 xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx AAA-9999  xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx   99/99/9999 99:99
            System.out.println("----------------------------------------------------------------------------------------------------");

            for (var locacao : locacoes) {
                System.out.printf("%s %-30s %-8s %-30s %s\n",
                        formataCPF(locacao.getCliente().getCpf().getValor()),
                        locacao.getCliente().getNome(),
                        locacao.getVeiculo().getPlaca(),
                        locacao.getVeiculo().getModelo(),
                        formataDataHora(locacao.gethoraDataLocacao()));
            }

            System.out.println("----------------------------------------------------------------------------------------------------");
        }
    }

    public void mostrarErro() {
        System.out.println("Erro no acesso aos dados. Tente novamente ou procure o suporte!");
    }

    private String formataCPF(Long cpf) {
        return Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})")
                .matcher(cpf.toString())
                .replaceAll("$1.$2.$3-$4");
    }

    private String formataDataHora(LocalDateTime dataHora) {
        var df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return df.format(dataHora);
    }
}
