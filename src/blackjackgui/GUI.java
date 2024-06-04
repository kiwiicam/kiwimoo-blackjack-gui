package blackjackgui;

import static blackjackgui.GraphicsPanel.DARK_GREEN;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class GUI extends JPanel implements ActionListener {

    public static final Color VERY_LIGHT_BLUE = new Color(51, 204, 255);
    //objects
    private GraphicsPanel graphicsPanel;
    private GameLogic gameLogic;
    private Deck deck;
    public Card card;
    private Sum sum;
    public int Dsum;

    //gui buttons/ frames/ images
    JButton play2;
    JButton b;
    JButton s;
    JButton play;
    JButton quit;
    JButton instruct;
    JButton back;
    JButton playg;
    JFrame j;
    int width;
    int height;
    JPanel mainPanel;
    JPanel instructions;
    JLabel chip20;
    JLabel chip50;
    JLabel chip100;
    ImageIcon img20;
    ImageIcon img50;
    ImageIcon img100;

    boolean n;
    boolean c;

    //betting vars
    int tableamt;
    int bet;
    int lose;
    int win;
    int gamesplayed;
    String str;

    public GUI() {
        str = "";
        tableamt = 0;
        bet = 0;
        lose = 0;
        win = 0;
        gamesplayed = 0;

        gameLogic = new GameLogic();
        c = false;
        n = false;
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
                Image bg2 = new ImageIcon("./resources/bg2.jpg").getImage();
                g.drawImage(bg2, 0, 0, getWidth(), getHeight(), this);
                g.setColor(VERY_LIGHT_BLUE);
                g.setFont(new Font("Arial", Font.PLAIN, 20));

                if (n) {
                    g.setColor(DARK_GREEN);
                    g.fillRect(50, 50, 1180, 540);
                    g.setColor(VERY_LIGHT_BLUE);
                    g.setFont(new Font("Arial", Font.PLAIN, 20));
                    g.drawString("1. Place a bet:", 55, 70);
                    g.drawString("Before the cards are dealt, players", 55, 120);
                    g.drawString("place a bet, usually within a posted", 55, 150);
                    g.drawString("minimum and maximum range.", 55, 180);

                    g.drawString("2. Receive cards:", 55, 230);
                    g.drawString("The dealer deals two cards face up to each player,", 55, 280);
                    g.drawString("and one card face up and one face down to themselves.", 55, 310);

                    g.drawString("3. Play your hand:", 55, 360);
                    g.drawString("Add the values of your cards together to get a total", 55, 410);
                    g.drawString("between 4 and 21. Aces can be played as 1 or 11,", 55, 440);
                    g.drawString("and all other cards are played at face value. Face", 55, 470);
                    g.drawString("cards (Jack, Queen, King) are worth 10 points, and", 55, 500);
                    g.drawString("all number cards (2–10) are worth their face value.", 55, 530);

                    g.drawString("4. Hit or stand:", 650, 70);
                    g.drawString("After receiving your cards, you can choose to \"hit\"", 650, 120);
                    g.drawString("and receive more cards, or \"stand\" and keep your current hand.", 650, 150);

                    g.drawString("5. Win or lose:", 650, 200);
                    g.drawString("The goal is to get closer to 21 than the dealer without going over.", 650, 250);
                    g.drawString("If your hand goes over 21, it's called a \"bust\" and you lose.", 650, 280);
                    g.drawString("If the dealer busts, all remaining players win. If neither the player", 650, 310);
                    g.drawString("nor the dealer busts, the player with the highest hand value wins.", 650, 340);
                    g.drawString("6. Betting:", 650, 390);
                    g.drawString("Users will be given 1000 chips to start,", 650, 440);
                    g.drawString("the maximum amount allowed to be bet is 500,", 650, 470);
                    g.drawString("and the minimum is 50,", 650, 500);
                    g.drawString("the user can increase betting amount by clicking", 650, 530);
                    g.drawString("on different chip amounts in the betting menu.", 650, 560);

                }
                if (c) {
                    g.setColor(VERY_LIGHT_BLUE);
                    g.setFont(new Font("Arial", Font.PLAIN, 20));
                    g.drawString(str, mainPanel.getWidth() / 2 - 80, mainPanel.getHeight() - 20);

                }
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

        play2 = new JButton("Play!");
        play2.addActionListener(this);
        play2.setBackground(VERY_LIGHT_BLUE);
        play2.setVisible(false);
        mainPanel.add(play2);

        s = new JButton("Stand");
        s.setBounds(570, 580, 100, 50);
        s.setBackground(VERY_LIGHT_BLUE);
        s.addActionListener(this);
        s.setVisible(false);
        mainPanel.add(s);

        instruct = new JButton("Instructions");
        instruct.setBounds(535, 370, 210, 50);
        instruct.setBackground(VERY_LIGHT_BLUE);
        instruct.addActionListener(this);
        mainPanel.add(instruct);

        back = new JButton("Back");
        back.setBackground(VERY_LIGHT_BLUE);
        back.setVisible(false);
        back.addActionListener(this);
        mainPanel.add(back);

        playg = new JButton("Play Again");
        playg.setBounds(200, 580, 100, 50);
        playg.setBackground(VERY_LIGHT_BLUE);
        playg.setVisible(false);
        playg.addActionListener(this);
        mainPanel.add(playg);

        j.add(mainPanel);
        j.setVisible(true);
    }

    public void game() {
        graphicsPanel.setBounds(mainPanel.getWidth()/2-400, 60, 800, 500);
        mainPanel.add(graphicsPanel);
        graphicsPanel.rectvisi = true;
        play.setVisible(false);
        instruct.setVisible(false);
        quit.setVisible(true);
        quit.setBounds(690, 580, 100, 50);
        b.setVisible(true);
        s.setVisible(true);
//        exit.setVisible(true);
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
        c = true;
        setMessage("Bet is: ");
        System.out.println("TESTING!@!!!@!#!@#");
        img20 = new ImageIcon("./resources/PokerChips20.png");
        img50 = new ImageIcon("./resources/PokerChips50.png");
        img100 = new ImageIcon("./resources/PokerChips100.png");
        play.setVisible(false);
        quit.setVisible(false);
        back.setBounds(mainPanel.getWidth() / 2 - 100, mainPanel.getHeight() - 100, 100, 50);
        play2.setBounds(mainPanel.getWidth() / 2 + 100, mainPanel.getHeight() - 100, 100, 50);
        play2.setVisible(true);
        back.setVisible(true);
        instruct.setVisible(false);
        chip20 = new JLabel(img20);
        chip50 = new JLabel(img50);
        chip100 = new JLabel(img100);

        chip50.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bet += 50;
                System.out.println(bet);
                setMessage("Bet is: " + bet);

            }
        });
        chip100.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bet += 100;
                System.out.println(bet);
                setMessage("Bet is: " + bet);

            }
        });
        chip20.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bet += 20;
                System.out.println(bet);
                setMessage("Bet is: " + bet);

            }
        });
        mainPanel.add(chip50);
        mainPanel.add(chip100);
        mainPanel.add(chip20);
        System.out.println(mainPanel.getWidth());
        chip20.setBounds(0, 100, img20.getIconWidth()-200,  img20.getIconHeight());
        chip50.setBounds(425,100, img50.getIconWidth()-200, img50.getIconHeight());
        chip100.setBounds(850, 100, img100.getIconWidth()-200, img100.getIconHeight());

    }

    public void setMessage(String str) {
        this.str = str;
        mainPanel.repaint();
    }

    public void instructions() {
        play.setVisible(false);
        quit.setVisible(false);
        back.setBounds(mainPanel.getWidth() / 2 - 100, mainPanel.getHeight() - 300, 100, 50);
        back.setVisible(true);
        instruct.setVisible(false);
        n = true;
        mainPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b) {

            int index = deck.DrawCard(deck.cards, true);
            graphicsPanel.hit(index, deck.p_sum, deck.getValue(), deck.getSuit());

            System.out.println("Removing card at index " + index + " it is a " + card.getImageCards().get(index));

            card.getImageCards().remove(index);
            boolean isBust = gameLogic.BustLogic(deck.p_sum);
            if (isBust) {
                PlayerWin(false);
            } else if (deck.p_sum == 21) {
                graphicsPanel.stand();
                Stand();
            }

        } else if (e.getSource() == s) {
            graphicsPanel.stand();
            Stand();

        } else if (e.getSource() == play2) {
            if (bet != 0) {
                game();
                c = false;
                setMessage("");
                play2.setVisible(false);
                chip20.setVisible(false);
                chip50.setVisible(false);
                chip100.setVisible(false);
                back.setVisible(false);
            }
            else
            {
                setMessage("Enter a valid betting amount");                
            }
        } else if (e.getSource() == play) {
            bet();
            //game();
        } else if (e.getSource() == quit) {
            System.exit(0);
        } else if (e.getSource() == instruct) {
            instructions();
        } else if (e.getSource() == back) {
            bet = 0;
            play2.setVisible(false);
            c = false;
            n = false;
            chip20.setVisible(false);
            chip50.setVisible(false);
            chip100.setVisible(false);
            setMessage("");
            mainPanel.repaint();
            back.setVisible(false);
            instruct.setVisible(true);
            play.setVisible(true);
            quit.setVisible(true);
        } else if (e.getSource() == playg) {
            resetGame();
            game();

        }
    }

    public Deck getDeck() {
        return deck;
    }

    public GraphicsPanel getGp() {
        return graphicsPanel;
    }

    public void Logic() {
        if (deck.p_sum == deck.d_sum) {
            graphicsPanel.setMessage2("ITS A TIE!!!!");
            graphicsPanel.setMessage4("ITS A TIE!!!!");
        } else if (deck.d_sum > 21) {
            graphicsPanel.setMessage2("DEALER BUSTED, YOU WIN!");
        } else if (deck.p_sum > deck.d_sum && deck.p_sum <= 21) {
            graphicsPanel.setMessage2("YOU HAVE WON!");
        } else {
            graphicsPanel.setMessage2("YOU HAVE LOST!!!!");
        }
        b.setVisible(false);
        s.setVisible(false);
        quit.setBounds(580, 580, 100, 50);
        playg.setVisible(true);
    }

    public void PlayerWin(boolean win) {
        if (win != true) {
            graphicsPanel.setMessage2("YOU LOOOOSE LLLLL!!!!!!!!");
            b.setVisible(false);
            s.setVisible(false);
            quit.setBounds(580, 580, 100, 50);
            playg.setVisible(true);
        }
    }

    public void DealerWin(boolean win) {
        if (win != true) {
            graphicsPanel.setMessage4("DEALER LOOOOSE LLLLL!!!!!!!!");
            b.setVisible(false);
            s.setVisible(false);
            quit.setBounds(580, 580, 100, 50);
            playg.setVisible(true);
        }

    }

    public void resetGame() {
        bet = 0;
        mainPanel.remove(graphicsPanel);
        graphicsPanel.Reset();
        deck = new Deck(1);
        card = new Card(deck.getCards());
        graphicsPanel = new GraphicsPanel(card.getImageCards());
        deck.p_sum = 0;
        deck.d_sum = 0;

        play.setVisible(false);
        instruct.setVisible(false);
        quit.setBounds(690, 580, 100, 50);
        b.setVisible(true);
        s.setVisible(true);
        back.setVisible(false);
        playg.setVisible(false);
        graphicsPanel.setBounds(250, 60, 800, 500);
        mainPanel.add(graphicsPanel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void Stand() {
        s.setVisible(false);
        b.setVisible(false);
        quit.setBounds(580, 580, 100, 50);

        new Thread(() -> {
            while (deck.d_sum <= 16) {
                int index = deck.DrawCard(deck.cards, false);
                graphicsPanel.dHit(index, deck.d_sum, deck.getValue(), deck.getSuit());
                card.getImageCards().remove(index);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                boolean isBust = gameLogic.BustLogic(deck.d_sum);
                System.out.println(isBust);
                if (isBust) {
                    DealerWin(false);
                } else {

                }
            }
            //graphicsPanel.setMessage4("The Dealer has stood!");
            Logic();
        }).start();
        graphicsPanel.setMessage4("Dealer has stood!!!");

    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//
//                GUI g = new GUI();
//            }
//        });
//    }
}
