package panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import common.ShuffleArrayList;
import main.Main;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8046137208488435475L;

	private final int size;
	
	private GameFieldPanel gameField;
	private GameInfoPanel gameScore;
	
	public GamePanel(int size) {
		this.size = size;
		this.setLayout(new BorderLayout());
		gameField = new GameFieldPanel(size);
		this.add(gameField); //thanks bine, would have never searched here
		//this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		//this.setPreferredSize(new Dimension(1000, 1000));
		//this.invalidate();
	}
	
	private static class GameFieldPanel extends JPanel {
		
		public GameFieldPanel(int size) {
			this.setLayout(new GridLayout(size, size));
			ShuffleArrayList<BufferedImage> mixedImages = new ShuffleArrayList<BufferedImage>(Main.images.size());
			mixedImages.addAll(Main.images);
			mixedImages.shuffle();
			
			ShuffleArrayList<GameCard> cards = new ShuffleArrayList<GameCard>(size*size);
			for (int i = size*size/2-1; i >= 0; i--) {
				cards.add(new GameCard(mixedImages.get(i)));
				cards.add(new GameCard(mixedImages.get(i)));
			}
			cards.shuffle();
			
			System.out.println(cards.size());
			
			cards.forEach(card -> this.add(card));
		}
		
	}
	
	private static class GameCard extends JPanel {
		
		private final BufferedImage image;
		public boolean taken = false;
		public boolean open = false;		
		
		public GameCard(BufferedImage image) {
			this.image = image;
			//this.setPreferredSize(new Dimension(50, 50));
			setMinimumSize(new Dimension(50, 50));
		}
		
		public boolean equals(GameCard c) {
			return this.image == c.image;
		}
		
		@Override
	    protected void paintComponent(Graphics g) {
			super.paintComponent(g);
	        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this); // see javadoc for more info on the parameters            
	    }
		
	}
	
	private static class GameCardPair extends GameCard {

		public GameCardPair(BufferedImage image) {
			super(image);
		}
		
	}
	
	private static class GameInfoPanel extends JPanel {
		
		public GameInfoPanel() {
			
		}
		
	}
}
