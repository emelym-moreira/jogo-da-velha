package jogador;

import jogo.Marca;
import jogo.Tabuleiro;

public interface Jogador
{
    // Metodos presentes em JogadorAutomatico e JogadorHumano 
    public Marca getMarca();
    public void facaSuaJogada() throws Exception;
}