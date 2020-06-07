package main;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import common.Config;
import panel.MenuPanel;
import panel.StartPanel;

public class Main {
	
	public static final Config config = new Config("config");

	public static JFrame frame;
	public static JPanel currentScreen;
	
	public static List<BufferedImage> images;
	
	public static void main(String[] args) throws ExecutionException {
		// TODO Auto-generated method stub

        frame = new JFrame();
        frame.setTitle("Discord Cache Memory Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        StartPanel startScreen = new StartPanel();
        currentScreen = startScreen;
        frame.add(currentScreen);
        
        frame.setVisible(true);
        
        images = startScreen.getImages();
        
        changeScreen(new MenuPanel());
	}

	public static void changeScreen(JPanel newScreen) {
		frame.remove(currentScreen);
		currentScreen = newScreen;
		frame.add(newScreen);
	}
}
