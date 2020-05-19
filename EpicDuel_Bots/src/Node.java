import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Node{//ability node
		String name;
		String imagePath;// path to image of ability.
		BufferedImage image;
		int priority; 
		int x;//x location of ability
		int y; // y location of ability. 
		int level;
		String type; // i.e passive, active, defensive, or support skill, also includes info --> ranged or not ranged
		boolean damage;//damage ability or not
		boolean cooldown;// is ability on cooldown or not
		Node next, previous;
		public Node() {
			
		}
		public Node(String name, String imagePath, int priority, int x, int y, int level, String type, boolean damage, boolean cooldown) {
			this.name = name; this.imagePath = imagePath; this.priority = priority; this.x = x; this.y = y; this.level = level; this.type = type; 
			this.damage = damage; this.cooldown = cooldown;
			loadImage();//get the image
		}
		public void loadImage() {
			try {
				image = ImageIO.read(new File(imagePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//get and set methods
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getImagePath() {
			return imagePath;
		}
		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}
		public BufferedImage getImage() {
			return image;
		}
		public void setImage(BufferedImage image) {
			this.image = image;
		}
		public int getPriority() {
			return priority;
		}
		public void setPriority(int prio) {
			this.priority = prio;
		}
		public Dimension getLocation() {
			return new Dimension(x,y);
		}
		public void setLocation(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int getLevel() {
			return level;
		}
		public void setLevel(int level) {
			this.level = level;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public boolean getDamage() {
			return damage;
		}
		public void setDamage(boolean damage) {
			this.damage = damage;
		}
		public boolean getCooldown() {
			return cooldown;
		}
		public void setCooldown(boolean cooldown) {
			this.cooldown = cooldown;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		public Node getPrevious() {
			return previous;
		}
		public void setPrevious(Node previous) {
			this.previous = previous;
		}
		//end get and set methods
	}