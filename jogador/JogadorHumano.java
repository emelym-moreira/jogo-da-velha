package jogador;

import jogo.Marca;
import jogo.Tabuleiro;
import java.io.*;

public class JogadorHumano implements Jogador
{
    private Marca          marca     = null;
    private Tabuleiro      tabDoJogo = null;
    private BufferedReader teclado   = new BufferedReader
                                        (new InputStreamReader
                                        (System.in));

    public JogadorHumano (Tabuleiro tab) throws Exception
    {
        // atribuir tab para tabDoJogo
        tabDoJogo = tab;
        // pedir para o jogador escolher
        // sua marca e permitir digitacao
        char opcao;

        do{
            System.out.print("Escolha sua marca (X ou O): ");
            String linha = this.teclado.readLine().trim().toUpperCase();

            if (!linha.isEmpty()) {
                opcao = linha.charAt(0);
                if (opcao == 'X' || opcao == 'O'){
                    this.marca = new Marca(opcao);
                    break;
                }
            }
            System.out.println("Opcao invalida! Escolha apenas X ou O.");
        } while(true);
    }

    public JogadorHumano (Tabuleiro tab, Marca mrc) throws Exception
    {
        // atribuir tab para tabDoJogo
        tabDoJogo = tab;
        // e mrc para marca
        marca = mrc;
    }

    public Marca getMarca(){
        return this.marca;
    }

    public void facaSuaJogada () throws Exception
    {
        // pergunta para o jogador onde ele quer
        // jogar, permitindo digitacao e chamando
        // os métodos apropriados de tabDoJogo
        do{
            System.out.println("Sua vez. Onde voce quer jogar? (Ex: 01 para linha 0 e coluna 1)");
            System.out.println(this.tabDoJogo.toString());

            String jogada = this.teclado.readLine().replaceAll("\\s+", ""); // para remover espaços

            if(jogada != null && jogada.length() >= 2){
                int linha = Character.getNumericValue(jogada.charAt(0));
                int coluna = Character.getNumericValue(jogada.charAt(1));

                if(linha >= 0 && linha <= 2 && coluna >= 0 && coluna <= 2){
                    if(!this.tabDoJogo.haMarcaNaPosicao(linha,coluna)){
                        this.tabDoJogo.setMarcaNaPosicao(this.marca, linha, coluna);
                        break;
                    } else {
                        System.out.println("Essa posicao ja esta ocupada!");
                    }
                } else {
                    System.out.println("Posicao invalida!");
                }
            } else {
                System.out.println("Entrada invalida! Digite 2 numeros, um para linha e um para coluna.");
            }
        }while(true);
    }

    public void vcGanhou ()
    {
        // mostra mensagem indicando vitoria
        System.out.println("Parabens! Voce ganhou essa partida!");
    }

    public void vcPerdeu ()
    {
        // mostra mensagem indicando derrota
        System.out.println("A vitoria nao rolou dessa vez! Quem sabe na proxima...");
    }

    public void deuVelha ()
    {
        // mostra mensagem indicando empate
        System.out.println("Que disputa acirrada! Deu velha!");
    }

    public boolean querMaisUmaPartida () throws Exception
    {
        // perguntar se deseja jogar de novo
        // permitindo digitacao e retornando
        // true ou false, conforme a resposta
        do{
            System.out.println("Quer jogar de novo (SIM ou NAO)?");
            String decisao = this.teclado.readLine().trim().toUpperCase();

            if(decisao != null){
                    if(decisao.equals("SIM")){
                        return true;
                    }
                    else if(decisao.equals("NAO")){
                        return false;
                    }
                    else {
                        System.out.println("Opcao invalida! Digite SIM ou NAO");
                    }
            }
        } while(true);
    }
}