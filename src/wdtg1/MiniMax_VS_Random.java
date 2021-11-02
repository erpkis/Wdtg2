package wdtg1;

import java.util.Random;

public class MiniMax_VS_Random {

    static int random = 1, minimax = 2;
    static int wersjaGry;

    static void graj(double ilosc, int wersjaGry, boolean czyPokazac) {
        MiniMax_VS_Random.wersjaGry = wersjaGry;
        Funkcje.czyPokazac = czyPokazac;
        int[][] tab = new int[3][3];
        Random r = new Random();
        int n, i;
        for (int gra = 0; gra < ilosc; gra++) {                                 //resetowanie planszy
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    tab[x][y] = 0;
                }
            }
            System.out.println("Gra 'MiniMax VS Random' nr " + (gra + 1) + ":");
            System.out.println("random - 1, minimax - 2");
            System.out.println();
            int ileRuchow = 0;
            do {
                ruchRandom(tab, 0);
                ileRuchow++;
                Funkcje.pokazPlansze(tab, Funkcje.czyPokazac);
                if (Funkcje.czyWygrywa(tab, MiniMax_VS_Random.random)) {
                    break;
                }
                ruchMiniMax(tab, 0);
                ileRuchow++;
                Funkcje.pokazPlansze(tab, Funkcje.czyPokazac);
                if (Funkcje.czyWygrywa(tab, MiniMax_VS_Random.minimax)) {
                    break;
                }
            } while (ileRuchow < wersjaGry * 2);
            if (Funkcje.czyWygrywa(tab, 1) == false && Funkcje.czyWygrywa(tab, 2) == false && Funkcje.brakRuchow == false) {
                do {
                    ruchRandom(tab, 1);
                    Funkcje.pokazPlansze(tab, Funkcje.czyPokazac);
                    if (Funkcje.czyWygrywa(tab, MiniMax_VS_Random.random)) {
                        break;
                    }
                    ruchMiniMax(tab, 1);
                    Funkcje.pokazPlansze(tab, Funkcje.czyPokazac);
                    if (Funkcje.czyWygrywa(tab, MiniMax_VS_Random.minimax)) {
                        break;
                    }
                } while (Funkcje.czyWygrywa(tab, 1) == false && Funkcje.czyWygrywa(tab, 2) == false && Funkcje.brakRuchow == false);
            }
        }
    }

    static void ruchRandom(int tab[][], int faza) {
        if (faza == 0) {
            Random r = new Random();
            int n = 0;
            int i = 0;
            do {
                n = r.nextInt(3);
                i = r.nextInt(3);
            } while (tab[n][i] != 0);
            tab[n][i] = MiniMax_VS_Random.random;
        } else {
            Zlozonosc.wykonajPrzesuniecie(tab, MiniMax_VS_Random.random, MiniMax_VS_Random.wersjaGry);
        }
    }

    static void ruchMiniMax(int tab[][], int faza) {
        MiniMax.gracz = 2;
        MiniMax.przeciwnik = 1;
        MiniMax.wykonajNajlepszyRuch(tab, faza);
    }
}
