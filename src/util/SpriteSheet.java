package util;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private BufferedImage sheet;
	private BufferedImage[] sprites;
	private int spriteWidth;
	private int spriteHeight;

	public SpriteSheet(String name, int sw, int sh) {
		this.spriteWidth = sw;
		this.spriteHeight = sh;

		try {
			sheet = ImageIO.read(SpriteSheet.class.getResource("/" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		sprites = new BufferedImage[(sheet.getWidth() / spriteWidth) * (sheet.getHeight() / spriteHeight)];

		for (int x = 0; x < sheet.getWidth() / spriteWidth; x++) {
			for (int y = 0; y < sheet.getHeight() / spriteHeight; y++) {
				sprites[(y * sheet.getWidth() / spriteWidth) + x] = sheet.getSubimage(x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight);
			}
		}
	}
	
	public BufferedImage getSpriteFlipped(int i) {
		BufferedImage image = getSprite(i);
		AffineTransform at = new AffineTransform();
		at.concatenate(AffineTransform.getScaleInstance(-1, 1));
		at.concatenate(AffineTransform.getTranslateInstance(-image.getWidth(), 0));
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = newImage.createGraphics();
		g.transform(at);
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return newImage;
	}

	public BufferedImage getSpriteFlippedVertical(int i) {
		BufferedImage image = getSprite(i);
		AffineTransform at = new AffineTransform();
		at.concatenate(AffineTransform.getScaleInstance(1, -1));
		at.concatenate(AffineTransform.getTranslateInstance(0, -image.getHeight()));
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = newImage.createGraphics();
		g.transform(at);
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return newImage;
	}

	public BufferedImage rotate(int i, double angle)
	{
		double sin = Math.abs(Math.sin(Math.toRadians(angle))),
				cos = Math.abs(Math.cos(Math.toRadians(angle)));

		int w = getSpriteWidth(), h = getSpriteHeight();

		int newWidth = (int) Math.floor(w*cos + h*sin),
				newHeight = (int) Math.floor(h*cos + w*sin);

		BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g =  newImage.createGraphics();

		g.translate((newWidth-w)/2, (newHeight-h)/2);
		g.rotate(Math.toRadians(angle), w/2, h/2);
		g.drawRenderedImage(getSprite(i), null);
		g.dispose();

		return  newImage;
	}

	public BufferedImage getSprite(int x) {
		int y = 0;

		if (x > (sheet.getWidth() / spriteWidth * x)) {
			y++;
		}

		return sprites[(y * sheet.getWidth() / spriteWidth) + x];
	}

	public int getWidth() {
		return sheet.getWidth();
	}

	public int getHeight() {
		return sheet.getHeight();
	}

	public int getSpriteWidth() {
		return spriteWidth;
	}

	public void setSpriteWidth(int spriteWidth) {
		this.spriteWidth = spriteWidth;
	}

	public int getSpriteHeight() {
		return spriteHeight;
	}

	public void setSpriteHeight(int spriteHeight) {
		this.spriteHeight = spriteHeight;
	}

	public int getCount() {
		return (sheet.getWidth() / spriteWidth * sheet.getHeight() / spriteHeight);
	}

}