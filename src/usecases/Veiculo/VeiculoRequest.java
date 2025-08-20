package usecases.Veiculo;

import java.time.Year;

/**
 * DTO usado para transportar os dados do PRESENTER para o CONTROLLER
 */
public record VeiculoRequest(String placa,
                             String modelo,
                             Year anoFabricao,
                             Double valorDiaria,
                             Integer quilometragem) {
}
