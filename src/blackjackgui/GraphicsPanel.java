package blackjackgui;
// this.cardImages = new ArrayList<>();
//            
//            for (int i = 1; i <= 52; i++) {
//                cardImages.add(new ImageIcon("./resources/" + i + ".png").getImage());
//            }

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GraphicsPanel extends JPanel {

    public boolean rectvisi;
    public static final Color VERY_LIGHT_BLUE = new Color(51, 204, 255);
    public static final Color DARK_GREEN = new Color(0, 153, 0);
    private String message4;
    private String message3;
    private String message2;
    private Deck deck;
    private String message;
    private int Psum;
    public Sum sum;
    public int space = 0;
    public int index = -1;
    public int index2 = -1;
    private Image bg2;
    public ArrayList<Image> cardImages;
    public ArrayList<Image> printedImages;
    public ArrayList<Image> printedImages2;

    public GraphicsPanel(ArrayList<String> cards) {
        this.rectvisi = false;
        printedImages2 = new ArrayList<>();
        printedImages = new ArrayList<>();
        this.message2 = "";
        deck = new Deck();
        this.message = "";
        this.message3 = "";
        this.message4 = "";
        this.Psum = 0;
        sum = new Sum();
        this.cardImages = new ArrayList<>();
        this.bg2 = new ImageIcon("./resources/bg2.jpg").getImage();
        try {
            for (String card : cards) {
                this.cardImages.add(new ImageIcon("./resources/" + card + ".png").getImage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        g.drawImage(bg2, 0, 0, width, height, null);
        g.setColor(DARK_GREEN);

        if (rectvisi != false) {
            g.fillRect(width/2-100, height/2+200, 250, 50);
            g.fillRect(width/2-100, height/2, 250, 50);
            g.fillRect(50, 100, 230, 230);
            
        }

        for (int i = 0; i < printedImages.size(); i++) {
            if (index != -1) {
                g.drawImage(printedImages.get(i), width / 2 - 100 + space + (i * 30), height / 2 + 100, 60, 80, this);
            }
        }
            for (int j = 0; j < printedImages2.size(); j++) {
            if (index2 != -1) {
                g.drawImage(printedImages2.get(j), width / 2 - 100 + space + (j * 30), height / 2 - 100, 60, 80, this);
            }
        }
        g.setColor(VERY_LIGHT_BLUE);
        g.drawString(message, width/2-90, height/2+215);
        g.drawString(message2, width/2-90, height/2+240);
        
        g.drawString(message3, width/2-90, height/2+15);
        g.drawString(message4, width/2-90, height/2+40);

    }

    public ArrayList<Image> getcardImages() {
        return cardImages;
    }
    public void dHit(int drawNum, int dsum, String value, String suit)
    {
        setMessage3("Dealer sum is " + dsum);
        setMessage4("Dealer has drawn a "+ value + " of "+ suit);
        index2 = drawNum;
        printedImages2.add(cardImages.get(index2));
        cardImages.remove(index2);
        
        repaint();
                
    }
    public void hit(int drawNum, int psum, String value, String suit) {
        
        setMessage("Your sum is " + psum);
        setMessage2("You have drawn a " + value + " of " + suit);
        index = drawNum;
        printedImages.add(cardImages.get(index));
        cardImages.remove(index);

        repaint();

    }

    public void stand() {
        setMessage2("You have stood!");
    }

    public void setMessage4(String message4) {
        this.message4 = message4;
        repaint();
    }

    public void setMessage3(String message3) {
        this.message3 = message3;
        repaint();
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
        repaint();
    }

    public void setMessage(String message) {
        this.message = message;
        repaint();
    }

}

//    public void actionPerformed(ActionEvent event)
//    {
//        
//        
//    }

