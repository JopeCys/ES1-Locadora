package domain.Veiculo;


/*
 * Classe responsável pela placa
 */
public class Placa 
{
    private String placa;

    private Placa(String placa)
    {
        this.placa = placa;
    }

    public static Placa create(String placa)
    {
        return ehPlacaValida(placa) ?  new Placa(placa) : null; 
    }

    public static boolean ehPlacaValida(String placa) {
        // Valida a placa: três letras seguidas por quatro números
        return placa.matches("[A-Z]{3}\\d{4}");
    }
}
