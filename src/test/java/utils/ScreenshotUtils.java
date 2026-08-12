package utils;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ScreenshotUtils {

	private ScreenshotUtils() {
	}

	public static String captureFailureScreenshot(String testName, String failureMessage) {

		try {

			String directory = "test-output/screenshots/";

			File folder = new File(directory);

			if (!folder.exists()) {
				folder.mkdirs();
			}

			String fileName = testName + "_" + System.currentTimeMillis() + ".png";

			String filePath = directory + fileName;

			BufferedImage image = new BufferedImage(1200, 600, BufferedImage.TYPE_INT_RGB);

			Graphics2D graphics = image.createGraphics();

			graphics.setFont(new Font("Arial", Font.BOLD, 24));

			graphics.drawString("API TEST FAILURE", 50, 60);

			graphics.setFont(new Font("Arial", Font.PLAIN, 20));

			graphics.drawString("Test: " + testName, 50, 110);

			graphics.drawString("Failure:", 50, 160);

			/*
			 * Prevent very long failure messages from going outside the image.
			 */
			String message = failureMessage == null ? "Unknown failure" : failureMessage;

			int y = 200;

			String[] words = message.split(" ");

			StringBuilder line = new StringBuilder();

			for (String word : words) {

				if (line.length() + word.length() > 90) {

					graphics.drawString(line.toString(), 50, y);

					y += 30;

					line.setLength(0);
				}

				line.append(word).append(" ");
			}

			if (line.length() > 0) {

				graphics.drawString(line.toString(), 50, y);
			}

			graphics.dispose();

			ImageIO.write(image, "png", new File(filePath));

			return filePath;

		} catch (Exception e) {

			return null;
		}
	}
}