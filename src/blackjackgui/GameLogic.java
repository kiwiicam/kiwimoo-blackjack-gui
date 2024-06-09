package blackjackgui;

public class GameLogic {

Deck deck = new Deck();
public Sum sum;

    public int GameEndLogic(int psum, int dsum) {
        if (psum>dsum) {
            return 0;
        } else if (dsum>psum) {
            return 1;
        }
        else if(psum==dsum)
        {
            return 2;
        }
        return 4;
    }

    public boolean BustLogic(int sum) {
         
        if (sum > 21) {
            return true;
        }
        return false;
        
    }

    

    public void DealerWin(boolean win) {

    }

}
