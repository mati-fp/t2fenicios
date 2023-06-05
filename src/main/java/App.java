
public class App 
{
    public static void main( String[] args )
    {

        String arquivos [] = {
            "caso01.txt", 
            "caso02.txt", "caso04.txt", "caso06.txt", "caso08.txt", "caso10.txt",
            "caso15.txt", "caso20.txt"
        };

        for (String arquivo : arquivos) {
            long tempoInicial = System.currentTimeMillis();
            leituraArquivo leitura = new leituraArquivo();

            resultadoLeitura resultado = leitura.leitura(arquivo);

            fenicios barco = new fenicios(resultado);

            barco.iniciaNegocios();
            
            long tempoFinal = System.currentTimeMillis();

            long tempoTotal = tempoFinal - tempoInicial;

            System.out.println();
            System.out.print(arquivo);
            System.out.print(" - Combustível Necessário: " + barco.getCombustivelNecessario());
            System.out.println("  || Tempo total: " + tempoTotal/1000 + " segundos");
            System.out.print("Portos Negociados: ");
            barco.getPortosNegociados();
            System.out.print("Distância entre os portos: ");
            barco.getDistanciaPortos();
            System.out.println();
        }


    }
}
