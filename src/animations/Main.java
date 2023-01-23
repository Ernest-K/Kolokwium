package animations;

import animations.gui.AnimFrame;

import javax.swing.*;

// Uruchomienie aplikacji
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            try{
                AnimFrame animFrame = new AnimFrame("Animacja");
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}