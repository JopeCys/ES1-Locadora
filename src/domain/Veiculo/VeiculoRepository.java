package domain.Veiculo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import domain.dao.VeiculoDTO;
import domain.Repository;
import domain.dao.IVeiculoDAO;

/**
 * Classe que representa um repositório de veículos.
 * A partir dessa classe os veículos são armazenados ou recuperados do BD
 */
public class VeiculoRepository implements Repository {
    private final IVeiculoDAO dao;

    public VeiculoRepository(IVeiculoDAO dao) 
    {
        this.dao = dao;
    }

    /**
     * Retorna todos os veículos
     *
     * @return Lista de veículos
     * @throws SQLException Exceção em caso de problemas no acesso ao BD
     */
    public List<Veiculo> findAll() throws SQLException  {
        // Busca todos os veículos do repositório
        var dtos = dao.findAll();

        // Converte os DTOs vindo do repositório em veículos
        var veiculos = new ArrayList<Veiculo>();

        for (var dto : dtos)
            veiculos.add(create(dto));

        return veiculos;
    }

    public List<Veiculo> findAllOrderedByPlaca() throws SQLException {
        // Busca todos os veículos do repositório ordenados
        var dtos = dao.findAllOrderedByPlaca();

        // Converte os DTOs vindo do repositório em veículos
        var veiculos = new ArrayList<Veiculo>();

        for(var dto: dtos)
            veiculos.add(create(dto));
        
        return veiculos;
    }

    public List<Veiculo> findAllOrderedByModelo() throws SQLException {
        // Busca todos os veículos do repositório ordenados
        var dtos = dao.findAllOrderedByModelo();

        // Converte os DTOs vindo do repositório em veículos
        var veiculos = new ArrayList<Veiculo>();

        for(var dto: dtos)
            veiculos.add(create(dto));
        
        return veiculos;
    }

    /**
     * Retorna um veículo com base na placa
     *
     * @param placa Placa a ser buscada
     * @return Veículo ou null, se não existir
     * @throws SQLException Exceção em caso de problemas no acesso ao BD
     */
    public Veiculo findByPlaca(String placa) throws SQLException  {
        // Busca o veículo no repositório
        var dto = dao.findByPlaca(placa);

        // Se existe, converte o DTO em veículo e retorna
        if (dto != null)
            return create(dto);

        // Se não existe, retorna nulo
        return null;
    }



    /**
     * Adiciona/atualiza um veículo no repositório
     *
     * @param veiculo Veículo a ser inserido/atualizado
     * @throws SQLException Exceção em caso de problemas no acesso ao BD
     */
    public void add(Veiculo veiculo) throws SQLException  {
        // Se o veículo NÃO tem ID, então NÃO veio do BD
        if (veiculo.getId() == null) {
            // Cria um ID artificial baseado no UUID
            veiculo.setId(UUID.randomUUID().toString());

            // Insere o veículo no BD
            dao.insert(veiculo);
        }
        else
            // Veículo já existe: atualiza no BD
            dao.update(veiculo);
    }

    /**
     * Remove um veículo do repositório
     *
     * @param veiculo Veículo a ser removido
     * @throws SQLException Exceção em caso de problemas no acesso ao BD
     */
    public void remove(Veiculo veiculo) throws SQLException  {
        // Se o veículo TEM ID, então deleta do BD
        if (veiculo.getId() != null) {
            dao.delete(veiculo);

            // Seta o ID do objeto para nulo, porque ele não está mais no BD
            veiculo.setId(null);
        }
    }

    /**
     * Cria um veículo a partir do VeiculoDTO
     *
     * @param dto Dados do veículo vindos do BD
     * @return Veículo
     */
    private Veiculo create(VeiculoDTO dto) { 
        // Usa o builder para construir o veículo com os dados vindos do BD
        var resultado = new VeiculoBuilder()
                .withPlaca(dto.placa())
                .withModelo(dto.modelo())
                .withAnoFabricacao(dto.anoFabricacao())
                .withValorDiaria(dto.valorDiaria())
                .withQuilometragem(dto.quilometragem())
                .build();

        // Assume que a criação foi bem sucedida,
        // pois os dados do BD devem estar consistentes
        var veiculo = resultado.valor;

        // Seta o ID do objeto, pois ele veio do BD
        veiculo.setId(dto.id());

        return veiculo;
    }

    /**
     * Retorna um veículo com base no ID
     *
     * @param id ID do veículo a ser buscado
     * @return Veiculo ou null, se não existir
     * @throws SQLException Exceção em caso de problemas no acesso ao BD
     */
    public Veiculo findById(String id) throws SQLException {
        // Busca o veículo no repositório
        VeiculoDTO dto = dao.findById(id);

        // Se o veículo existe, converte o DTO em veículo e retorna
        if (dto != null) {
            return create(dto);
        }

        // Se não existe, retorna nulo
        return null;
    }
}
