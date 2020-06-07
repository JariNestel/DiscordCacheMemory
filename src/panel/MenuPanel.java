package panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import main.Main;

public class MenuPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6317274582351823142L;

	private JLabel sizeLabel;
	private JSlider sizeSlider;
	private JButton start;
	private JButton close;
	
	public MenuPanel() {
		sizeLabel = new JLabel("Size");
		this.add(sizeLabel);
		sizeSlider = new JSlider(2, (int)Math.sqrt(Main.images.size()));
		sizeSlider.setMinorTickSpacing(1);
		sizeSlider.setMajorTickSpacing(2);
		sizeSlider.setPaintTicks(true);
		sizeSlider.setPaintLabels(true);
		//sizeSlider.setPaintTrack(true);
		sizeSlider.setSnapToTicks(true);
		this.add(sizeSlider);
		start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.changeScreen(new GamePanel(sizeSlider.getValue()));
			}	
		});
		this.add(start);
		close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}});
		this.add(close);
	}
}
