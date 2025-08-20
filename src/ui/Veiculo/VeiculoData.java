package ui.Veiculo;

/**
 * DTO usado para transportar os dados da VIEW para o PRESENTER
 */
public record VeiculoData(String placa,
                          String modelo,
                          String anoFabricao,
                          String valorDiaria,
                          String quilometragem) {
}   
