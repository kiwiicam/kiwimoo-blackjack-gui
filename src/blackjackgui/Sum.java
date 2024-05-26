
package blackjackgui;

public class Sum {
    public int sum;
    public int dSum;
    //sum constructor
    public Sum()
    {
        this.sum = 0;
        this.dSum = 0;
        
    }
    //getter for sum
    public int getSum()
    {
        return sum;
    }
    //getter for dSum
    public int getdSum()
    {
        return dSum;
    }
//    public void setSum(int sum)
//    {
//        this.sum = sum;
//    }
    //method for calcultaing dealers sum
    public int DealerSum(int drawnNum)
    {
        dSum+=drawnNum;
        return dSum;        
    }
    //method for calculating PlayerSum
    public int PlayerSum(int drawnNum)
    {
        sum+=drawnNum;
        return sum;
    }
    //method to take in a string and decide whether its an ace or another picture card
    //to then assign a value
    public void Checks(String value, boolean num) {
        if (value.equals("Ace")) {
            if(sum+11>21)
            {
                if(num)
                {
                PlayerSum(1);
                }
                else
                {
                    DealerSum(1);
                }
            }
            else{
                if(num){
                PlayerSum(11);
                }
                else{
                    DealerSum(1);
                }
            }
        } else if (value.equals("King") || value.equals("Jack") || value.equals("Queen")) {
            if(num){
            PlayerSum(10); 
            }
            else
            {
                DealerSum(10);
            }
        } else {
            try {
                if(num){
                PlayerSum(Integer.parseInt(value));
                }
                else{
                    DealerSum(Integer.parseInt(value));
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    //method for checking if its an ace
    public void Ace(String value)
    {
        
    }
    //method for checking if the drawn card is a picture card;
    public void Picture(String value)
    {
        
    }
    public void Checksum()
    {
        
    }
    
    
}
