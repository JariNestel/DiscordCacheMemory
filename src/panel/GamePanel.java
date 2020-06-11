package panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import common.ShuffleArrayList;
import common.SquareJPanel;
import main.Main;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8046137208488435475L;

	private final int size;
	
	private GameFieldPanel gameField;
	private GameInfoPanel gameScore;
	
	public GamePanel(int size, int playerCount) {
		this.size = size;
		this.setLayout(new FlowLayout());
		gameField = new GameFieldPanel(size);
		this.add(gameField); //thanks bine, would have never searched here
		//this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		//this.setPreferredSize(new Dimension(1000, 1000));
		//this.invalidate();
	}
	
	private static class GameFieldPanel extends SquareJPanel {
		
		public GameFieldPanel(int size) {
			this.setLayout(new GridLayout(size, size));
			//this.setLayout(new GridBagLayout());
			//this.setBorder(BorderFactory.createLineBorder(Color.GREEN));
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
			/*GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = gbc.BOTH;
			gbc.weightx = 1.0d;
			gbc.weighty = 2.0d;
			for (int i = 0; i < size; i++) {
				gbc.gridx = i;
				for (int j = 0; j < size; j++) {
					gbc.gridy = j;
					this.add(cards.get(i*size+j), gbc);
				}
			}*/
		}
		
	}
	
	private static class GameCard extends SquareJPanel {
		
		private final BufferedImage image;
		public boolean taken = false;
		public boolean open = false;		
		
		public GameCard(BufferedImage image) {
			this.image = image;
			//this.setPreferredSize(new Dimension(50, 50));
			//setMinimumSize(new Dimension(50, 50));
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
	
	private static class GamePlayer extends JPanel {
		
		List<GameCardPair> pairs = new ArrayList<GameCardPair>();
		
		private final int id;
		
		private JLabel name;
		private JLabel pairCount;
		
		public GamePlayer(int id) {
			this.id = id;
			name = new JLabel("Player #"+id);
			this.add(name);
			pairCount = new JLabel("#pairs: "+0);
			this.add(pairCount);
		}
		
		public void addPair(GameCardPair pair) {
			pairs.add(pair);
			pairCount.setText("#pairs: "+pairs.size());
		}
		
	}
}
