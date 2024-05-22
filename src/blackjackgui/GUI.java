package blackjackgui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

public class GUI extends JPanel implements ActionListener {

    public static final Color VERY_LIGHT_BLUE = new Color(51, 204, 255);
    private GraphicsPanel graphicsPanel;
    private Deck deck;
    public Card card;
    private Sum sum;
    public int Dsum;
    JButton b;
    JButton s;
    JButton play;
    JButton quit;
    JButton exit;
    JFrame j;
    int width;
    int height;
    JPanel mainPanel;

    public GUI() {
        this.Dsum = 0;
        sum = new Sum();
        deck = new Deck(1);
        card = new Card(deck.getCards());
        graphicsPanel = new GraphicsPanel(card.getImageCards());

        j = new JFrame();
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setExtendedState(JFrame.MAXIMIZED_BOTH);

        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Paint background image
                Image bg2 = new ImageIcon("./resources/bg2.jpg").getImage();
                g.drawImage(bg2, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(null);

        play = new JButton("Play");
        play.setBounds(535, 300, 100, 50);
        play.addActionListener(this);
        play.setBackground(VERY_LIGHT_BLUE);
        mainPanel.add(play);

        quit = new JButton("Quit");
        quit.setBounds(645, 300, 100, 50);
        quit.addActionListener(this);
        quit.setBackground(VERY_LIGHT_BLUE);
        mainPanel.add(quit);

        b = new JButton("Hit");
        b.setBounds(450, 580, 100, 50);
        b.addActionListener(this);
        b.setBackground(VERY_LIGHT_BLUE);
        b.setVisible(false);
        mainPanel.add(b);

        s = new JButton("Stand");
        s.setBounds(570, 580, 100, 50);
        s.setBackground(VERY_LIGHT_BLUE);
        s.addActionListener(this);
        s.setVisible(false);
        mainPanel.add(s);

        exit = new JButton("Quit");
        exit.setBounds(690, 580, 100, 50);
        exit.setBackground(VERY_LIGHT_BLUE);
        exit.addActionListener(this);
        exit.setVisible(false);
        mainPanel.add(exit);

        
        j.add(mainPanel);
        j.setVisible(true);
    }

    public void game() {
        graphicsPanel.setBounds(250, 60, 800, 500);
        mainPanel.add(graphicsPanel);
        graphicsPanel.rectvisi = true;
        play.setVisible(false);
        quit.setVisible(false);
        b.setVisible(true);
        s.setVisible(true);
        exit.setVisible(true);
        gameStart();

    }

    
    public void gameStart() {
        int index = deck.DrawCard(deck.cards, true);
        graphicsPanel.hit(index, deck.p_sum, deck.getValue(), deck.getSuit());
        card.getImageCards().remove(index);
        int index2 = deck.DrawCard(deck.cards, true);
        graphicsPanel.hit(index2, deck.p_sum, deck.getValue(), deck.getSuit());
        card.getImageCards().remove(index2);
        int index3 = deck.DrawCard(deck.cards, false);
        graphicsPanel.dHit(index3, deck.d_sum, deck.getValue(), deck.getSuit());
        card.getImageCards().remove(index3);

    }
    
    //this will have a slider to input a betting amount
    //this will also have a combo box or another form of input
    //that will ask how many decks to be used in this game.
    public void bet() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b) {

            for (int i = 0; i < deck.getCards().size(); i++) {

                System.out.println(i + " " + deck.getCards().get(i));
            }

            for (int i = 0; i < card.getImageCards().size(); i++) {

                System.out.println(i + " " + card.getImageCards().get(i));
            }
            int index = deck.DrawCard(deck.cards, true);
            graphicsPanel.hit(index, deck.p_sum, deck.getValue(), deck.getSuit());

            System.out.println("Removing card at index " + index + " it is a " + card.getImageCards().get(index));

            card.getImageCards().remove(index);

        } else if (e.getSource() == s) {
            graphicsPanel.stand();
            s.setVisible(false);
            b.setVisible(false);
            new Thread(() -> {
                while (deck.d_sum < 16) {
                    int index = deck.DrawCard(deck.cards, false);
                    graphicsPanel.dHit(index, deck.d_sum, deck.getValue(), deck.getSuit());
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                //graphicsPanel.setMessage4("The Dealer has stood!");
            }).start();

        } else if (e.getSource() == play) {
            game();
        } else if (e.getSource() == quit || e.getSource() == exit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                GUI g = new GUI();
            }
        });
    }
}
