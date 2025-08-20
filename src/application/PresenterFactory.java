package application;

import domain.Cliente.ClienteRepository;
import domain.Locacao.LocacaoRepository;
import domain.Veiculo.VeiculoRepository;
import persistence.ClienteDAO;
import persistence.LocacaoDAO;
import persistence.VeiculoDAO;
import ui.*;
import ui.Cliente.CadastroClientePrt;
import ui.Cliente.CadastroClienteView;
import ui.Cliente.ExcluirClientePrt;
import ui.Cliente.ExcluirClienteView;
import ui.Cliente.ListarClientesPrt;
import ui.Cliente.ListarClientesView;
import ui.Locacao.CadastrarLocacaoView;
import ui.Locacao.CadastroLocacaoPrt;
import ui.Locacao.ListarLocacoesPrt;
import ui.Locacao.ListarLocacoesView;
import ui.Veiculo.CadastroVeiculoPrt;
import ui.Veiculo.CadastroVeiculoView;
import ui.Veiculo.ExcluirVeiculoPrt;
import ui.Veiculo.ExcluirVeiculoView;
import ui.Veiculo.ListarVeiculosPrt;
import ui.Veiculo.ListarVeiculosView;
import usecases.Cliente.CadastroClienteCtrl;
import usecases.Cliente.ExcluirClienteCtrl;
import usecases.Cliente.ListarClientesCtrl;
import usecases.Locacao.CadastroLocacaoCtrl;
import usecases.Locacao.ListarLocacoesCtrl;
import usecases.Veiculo.CadastroVeiculoCtrl;
import usecases.Veiculo.ExcluirVeiculoCtrl;
import usecases.Veiculo.ListarVeiculosCtrl;

/**
 * Classe responsável por criar os presenters e sua estrutura
 */
public class PresenterFactory {

	/**
	 * Tipo do presenter
	 */
	public enum Type { MENU, 
		               CADASTRAR_CLIENTE, 
		               EXCLUIR_CLIENTE, 
		               LISTAR_CLIENTE,
					   CADASTRAR_VEICULO,
					   EXCLUIR_VEICULO,
					   LISTAR_VEICULO,
					   LOCAR_VEICULO,
					   LISTAR_LOCACOES};
	
    /**
     * Cria um presenter de acordo com o tipo solicitado
     * 
     * @param type Tipo do presenter
     * @return Presenter
     */
	public static Presenter get(Type type) {
		switch(type) {
			case MENU -> {
				var view = new MenuView(); 
				
				return new MenuPresenter(view);
			}
		
			case CADASTRAR_CLIENTE -> {
				var repository = new ClienteRepository(new ClienteDAO());
				var view = new CadastroClienteView();
				var controller = new CadastroClienteCtrl(repository);

				return new CadastroClientePrt(view, controller);

			}
		
			case EXCLUIR_CLIENTE -> {
				var repository = new ClienteRepository(new ClienteDAO());
				var view = new ExcluirClienteView();
				var controller = new ExcluirClienteCtrl(repository);

				return new ExcluirClientePrt(view, controller);
			}
		
			case LISTAR_CLIENTE -> {
				var repository = new ClienteRepository(new ClienteDAO());
				var view = new ListarClientesView();
				var controller = new ListarClientesCtrl(repository);

				return new ListarClientesPrt(view, controller);
			}

			case CADASTRAR_VEICULO -> {
				var repository = new VeiculoRepository(new VeiculoDAO());
				var view = new CadastroVeiculoView();
				var controller = new CadastroVeiculoCtrl(repository);

				return new CadastroVeiculoPrt(controller, view);
			} 

			case EXCLUIR_VEICULO -> {
				var repository = new VeiculoRepository(new VeiculoDAO());
				var view = new ExcluirVeiculoView();
				var controller = new ExcluirVeiculoCtrl(repository);

				return new ExcluirVeiculoPrt(view, controller);
			}

			case LISTAR_VEICULO -> {
				var repository = new VeiculoRepository(new VeiculoDAO());
				var view = new ListarVeiculosView();
				var controller = new ListarVeiculosCtrl(repository);

				return new ListarVeiculosPrt(view, controller);
			}

			case LOCAR_VEICULO -> {
				var repoVeiculo = new VeiculoRepository(new VeiculoDAO());
				var repoCliente = new ClienteRepository(new ClienteDAO());
				var repoLocacao = new LocacaoRepository(new LocacaoDAO(), repoCliente, repoVeiculo);
				var view = new CadastrarLocacaoView();
				var controller = new CadastroLocacaoCtrl(repoVeiculo, repoCliente, repoLocacao);

				return new CadastroLocacaoPrt(view, controller);
			}
			case LISTAR_LOCACOES -> {
				var repoVeiculo = new VeiculoRepository(new VeiculoDAO());
				var repoCliente = new ClienteRepository(new ClienteDAO());
				var repoLocacao = new LocacaoRepository(new LocacaoDAO(), repoCliente, repoVeiculo);
				var view = new ListarLocacoesView();
				var controller = new ListarLocacoesCtrl(repoLocacao);

				return new ListarLocacoesPrt(view, controller);
			}
		};
		return null;
	}
}
