import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class leituraArquivo {
    
    nodoLocalizacao mapa [][];
    public resultadoLeitura leitura(String fileName) {

        int linhas = 0;
        int colunas = 0;
        int linhaAtual = 0;
        nodoLocalizacao terraFenicios = null;
        
        String currentDir = System.getProperty("user.dir");
        String dir = currentDir + "/src/main/casos/" + fileName;

        try (BufferedReader br = new BufferedReader(new FileReader(dir))) {
            String primeiraLinha[];
            String line;

            // Ler a primeira linha do arquivo, que contém o número de linhas e colunas
            primeiraLinha = br.readLine().split(" ");
            linhas = Integer.parseInt(primeiraLinha[0]);
            colunas = Integer.parseInt(primeiraLinha[1]);
            mapa = new nodoLocalizacao[linhas][colunas];
            // Ler as linhas restantes com informações do mapa
            while ((line = br.readLine()) != null) {

                int tamLinha = line.length();

                for (int colunaAtual = 0; colunaAtual < tamLinha; colunaAtual++) {
                    char c = line.charAt(colunaAtual);
                    if (c == '.') {
                        mapa[linhaAtual][colunaAtual] = new nodoLocalizacao(linhaAtual, colunaAtual);
                    } 
                    else if (c == '*') {
                        // Não faz nada aqui irmão
                    } else {
                        int numeroPorto = Character.getNumericValue(c);
                        if (numeroPorto == 1){
                            mapa[linhaAtual][colunaAtual] = new nodoLocalizacao(numeroPorto, linhaAtual, colunaAtual);
                            terraFenicios = mapa[linhaAtual][colunaAtual];
                        } else {
                            mapa[linhaAtual][colunaAtual] = new nodoLocalizacao(numeroPorto, linhaAtual, colunaAtual);
                        }
                
                    }
                }
                linhaAtual++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return new resultadoLeitura(linhas, colunas, mapa, terraFenicios);

    }
}
