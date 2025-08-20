package usecases.Cliente;

import java.sql.SQLException;

import domain.Cliente.ClienteRepository;

/*
 * Controller responsável por deletar cliente
 */
public class ExcluirClienteCtrl 
{   
    private ClienteRepository repo;

    public ExcluirClienteCtrl(ClienteRepository repo)
    {
        this.repo = repo;
    }
    
    /*
     * Função para excluir cliente 
     */
    public boolean excluirCliente(Long cpf) throws SQLException
    {
        var clienteADeletar = repo.findByCPF(cpf);

        if(clienteADeletar != null)
        {
            repo.remove(clienteADeletar);
            return true;
        }
        return false;
    }
}
