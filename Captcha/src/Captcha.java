import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Arrays;


public class Captcha {

    public static void main(String avg[]) throws IOException
    {
        Captcha abc = new Captcha();
    }



    public Captcha() throws IOException {
        String test[];
        test = new String[]{"6138B", "1j93k9L", "7d6bf", "9Aj7854U", "93uA90T", "G818wef", "89Adn13c", "J8D56fE9", "74Ad65G", "H5N4we8"};
        Random gen = new Random();
        int i = gen.nextInt(10) -1;
        BufferedImage img = null;
        switch(i) {
            case 0:
                img = ImageIO.read(new File("C:\\Users\\Adian\\IdeaProjects\\Captcha\\src\\6138B.jpg"));
                break;
            case 1:
                img = ImageIO.read(new File("C:\\Users\\Adian\\IdeaProjects\\Captcha\\src\\1j93k9L.jpg"));

                break;
            case 2:
                img = ImageIO.read(new File("C:\\Users\\Adian\\IdeaProjects\\Captcha\\src\\7d6bf.jpg"));
                break;
            case 3:
                img = ImageIO.read(new File("C:\\Users\\Adian\\IdeaProjects\\Captcha\\src\\9Aj7854U.jpg"));
                break;
            case 4:
                img = ImageIO.read(new File("C:\\Users\\Adian\\IdeaProjects\\Captcha\\src\\93uA90T.jpg"));
                break;
            case 5:
                img = ImageIO.read(new File("C:\\Users\\Adian\\IdeaProjects\\Captcha\\src\\G818wef.jpg"));
                break;
            case 6:
                img = ImageIO.read(new File("C:\\Users\\Adian\\IdeaProjects\\Captcha\\src\\89Adn13c.jpg"));
                break;
            case 7:
                img = ImageIO.read(new File("C:\\Users\\Adian\\IdeaProjects\\Captcha\\src\\J8D56fE9.jpg"));
                break;
            case 8:
                img = ImageIO.read(new File("C:\\Users\\Adian\\IdeaProjects\\Captcha\\src\\74Ad65G.jpg"));
                break;
            default:
                img = ImageIO.read(new File("C:\\Users\\Adian\\IdeaProjects\\Captcha\\src\\H5N4we8.jpg"));
                break;
        }

        ImageIcon icon = new ImageIcon(img);
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(500, 300);
        JLabel lbl = new JLabel();
        JTextField user = new JTextField(20);
        user.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Enter pressed");
                frame.dispose();
                if(test[i].equals(user.getText()))
                {
                    JOptionPane.showMessageDialog(null,"Captcha correct!");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Incorrect captcha!");
                }
            }
        });
        user.setSize(100,20);
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.add(user);
        frame.pack();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}