package ui.Cliente;

import java.util.Scanner;

public class ExcluirClienteView 
{
    public String lerCPF() 
    {
        var input = new Scanner(System.in);
        String cpf;
        
        System.out.println("\n--------------------");
		System.out.println("Exclusão de Cliente");
		System.out.println("--------------------");

        System.out.println("Digite o CPF do cliente a ser deletado: ");
        cpf = input.nextLine();

        return cpf;
    }
    
    public void mensagemErro()
    {
        System.out.println("Cliente não encontrado!");
    }

    public void mensagemSucesso()
    {
        System.out.println("Exclusão bem-sucedida");
    }
}
