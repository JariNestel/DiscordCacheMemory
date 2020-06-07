package panel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import main.Main;
import main.OSNotSupportedException;

public class StartPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1029655930022677858L;

	private ImageLoadingTask imageLoader;
	
	private JLabel hello;
	private JProgressBar imageLoadProgress;
	
	public StartPanel() {
		hello = new JLabel();
		hello.setText("Hello "+System.getProperty("user.name"));
		this.add(hello);
		
		try {
			imageLoader = ImageLoadingTask.makeImageLoadingTask();
			imageLoader.execute();
			imageLoadProgress = imageLoader.getProgressBar();
			imageLoadProgress.setStringPainted(true);
			this.add(imageLoadProgress);
		} catch (OSNotSupportedException e) {
			hello.setText("Hello "+System.getProperty("user.name")+" your Operating System ("+System.getProperty("os.name")+") is not supported");
			//Thread.currentThread().interrupt();
		}
	}
	
	public List<BufferedImage> getImages() throws ExecutionException {
		while (true) {
			try {
				return imageLoader.get();
			} catch (InterruptedException ignore) {}			
		}
	}
	
	private static class ImageLoadingTask extends SwingWorker<List<BufferedImage>, BufferedImage> {

		private final File folder;
		private final File[] files;
		private final boolean rectangularImagesOnly;
		private final JProgressBar progressBar;
		
		public static ImageLoadingTask makeImageLoadingTask() throws OSNotSupportedException {
			boolean rectangularImagesOnly = Main.config.getBoolean("rectangularImagesOnly");
			String userHome = System.getProperty("user.home");
			String os = System.getProperty("os.name");
			String cacheFolder;
			try {
				cacheFolder = Main.config.getString("discordCache_"+os.replace(' ', '_'));
				return new ImageLoadingTask(
						new File(userHome+cacheFolder),
						rectangularImagesOnly
						);
			} catch (NullPointerException e) {
				throw new OSNotSupportedException(os);
			}
		}
		
		private ImageLoadingTask(File folder, boolean rectangularImagesOnly) {
			this.folder = folder;
			this.rectangularImagesOnly = rectangularImagesOnly;
			this.files = this.folder.listFiles();
			this.progressBar = new JProgressBar(0, this.files.length);
		}
		
		public JProgressBar getProgressBar() {
			return progressBar;
		}

		@Override
		protected List<BufferedImage> doInBackground() throws Exception {
			//System.out.println("doInBackground");
			final List<BufferedImage> images = new ArrayList<BufferedImage>();
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				try {
					//System.out.println(i+": "+file.getName());
					BufferedImage image = ImageIO.read(file);
					if (image != null && (!this.rectangularImagesOnly || image.getWidth() == image.getHeight())) {
						images.add(image);
						//System.out.println("added");
					}
				} catch (IOException ignore) {
				} catch (Exception e) {
					e.printStackTrace();
				} catch (Error e) {
					e.printStackTrace();
				}
				this.progressBar.setValue(i+1);
			}
			//System.out.println("doInBackground");
			return images;
		}
		
	}
}
