package blackjackgui;

public class Sum {

    public int sum;
    public int dSum;
    public int playerAceCount, dealerAceCount;

    //sum constructor
    public Sum() {
        this.sum = 0;
        this.dSum = 0;
        this.playerAceCount = 0;
        this.dealerAceCount = 0;

    }

    //getter for sum
    public int getSum() {
        return sum;
    }

    //getter for dSum
    public int getdSum() {
        return dSum;
    }
//    public void setSum(int sum)
//    {
//        this.sum = sum;
//    }
    //method for calcultaing dealers sum

    public int DealerSum(int drawnNum) {
        dSum += drawnNum;
        checkForAceValueChange(false);
        return dSum;
    }

    //method for calculating PlayerSum
    public int PlayerSum(int drawnNum) {
        sum += drawnNum;
        checkForAceValueChange(true);//true for players turn
        return sum;
    }

    public void checkForAceValueChange(boolean playersTurn) {
        if (playersTurn) {
            if (sum > 21 && playerAceCount > 0) {
                sum -= 10;
                playerAceCount--;
            }
        } else {
            while (dSum > 21 && dealerAceCount > 0) {
                dSum -= 10;
                dealerAceCount--;
            }
        }
    }
    //method to take in a string and decide whether its an ace or another picture card
    //to then assign a value
    public void Checks(String value, boolean playersTurn) {
        if (value.equals("Ace")) {
            if (playersTurn) {
                playerAceCount++;
                PlayerSum(11);
            } else {
                dealerAceCount++;
                DealerSum(11);
            }
        } else if (value.equals("King") || value.equals("Jack") || value.equals("Queen")) {
            if (playersTurn) {
                PlayerSum(10);
            } else {
                DealerSum(10);
            }
        } else {
            try {
                int cardValue = Integer.parseInt(value);
                if (playersTurn) {
                    PlayerSum(cardValue);
                } else {
                    DealerSum(cardValue);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

}
