import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AbilityQueue {// higher the priority the better
	private String buildType;//strength, dex, tech,
	private Node abils;
	private Node healthBar[], manaBar[];//special nodes for the health and mana bar
	
	public static void main(String args[]) {
		AbilityQueue aQ = new AbilityQueue("Strength");
		aQ.readIn("Builds\\Tact_Merc_Str_Build\\tactical_mercenary_build.txt");
		aQ.printAbils();
	}
	
	public AbilityQueue(String buildType){
		this.buildType = buildType;	
		healthBar = new Node[4];//max 4 players in one duel(4v4)
		manaBar = new Node[4];//max 4 players in one duel(4v4);
	}

	public void readIn(String buildPath){//read in all usable abilities
		//Each build file should be .txt and have this format:
		//name, imagePath, priority, x, y, level, type, damage, cooldown
		String line = "";
		Scanner sc;
		try {
			sc = new Scanner(new File(buildPath));
			
			String[] sLine; //line is split at every ","
			while(sc.hasNextLine()) {
				line = sc.nextLine();
				sLine = line.split(",");
				for(int i=0; i<sLine.length; i++) {
					if(sLine[i].charAt(0) == ' ')
						sLine[i] = sLine[i].replaceFirst("\\s", "");//remove the first spaces so we don't encouter error when parsing from one time to another
				}
				add(sLine[0], sLine[1], Integer.parseInt(sLine[2]), Integer.parseInt(sLine[3]), 
					Integer.parseInt(sLine[4]), Integer.parseInt(sLine[5]), sLine[6],
					Boolean.parseBoolean(sLine[7]), Boolean.parseBoolean(sLine[8]));//add the ability
			}
			
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void add(String name, String imagePath, int priority, int x, int y, int level, String type, boolean damage, boolean cooldown) {//add to q
		//special case for health and mana bar
		if(name.equals("Health Bar")) {
			int i=0;
			for(; i<4; i++) {
				if(healthBar[i]!=null)
					i++;
			}
			healthBar[i] = new Node();
			healthBar[i].setLocation(x, y);
		}
		else if(name.equals("Mana Bar")) {
			int i=0;
			for(; i<4; i++) {
				if(healthBar[i]!=null)
					i++;
			}
			manaBar[i] = new Node();
			manaBar[i].setLocation(x, y);
		}
		//end special case
		else if(abils == null) {
			abils = new Node();
			abils.name = name; abils.imagePath = imagePath; abils.priority = priority; abils.x = x; abils.y = y; abils.level = level; abils.type = type; 
			abils.damage = damage; abils.cooldown = cooldown;
			abils.loadImage();
			return;
		}
		else {
			Node ability = new Node(name, imagePath, priority, x, y, level, type, damage, cooldown);
			ability.loadImage();
			insert(ability);//insert ability to queue
		}
	}
	private void insert(Node ability) {
		if(ability == null)
			return;
		if(abils == null)
			abils = ability;
		Node ref = abils;
		while(ref != null) {
			if(ref.priority < ability.priority)//found where we want to insert
				break;
			else if(ref.next == null) {//in which case insert at end
				ref.next = ability;
				ability.previous = ref;
				return;
			}
			ref = ref.next;
		}
		
		//add ability to right position in q
		ability.next = ref;
		ability.previous = ref.previous;
		if(ref.previous != null) {
			ref.previous.next = ability;
			ref.previous = ability;
		}
		else {//inserted at head
			ref.previous = ability;
			abils = ability;
		}
		//add ability to right position in q
	}
	public void reorder() {//goes through q and order it.
		Node temp = abils;
		abils = null;
		
		while(temp != null) {
			add(temp.name, temp.imagePath, temp.priority, temp.x, temp.y, temp.level, temp.type, temp.damage, temp.cooldown);
			temp = temp.next;
		}
		
	}
	public void clearPriority() {
		Node ref = abils;
		while(ref != null) {
			ref.priority = 0;
			ref = ref.next;
		}
	}	
	public void printAbils() {
		if(abils == null)
			System.out.println("Empty");
		Node ref = abils;
		while(ref != null) {
			System.out.println(ref.name);
			ref = ref.next;
		}
	}
	
	public Node removeFirst() {//get and remove first node in list, // there should'nt be any need to use this
		Node temp = abils;
		abils = abils.next;
		return temp;
	}
	//get and set methods
	public Node getFirst() {//get first ability in list but do not remove it from it
		return abils;
	}	
	//get and set methods
	
	/*public class Node{//ability node
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
		private Node next, previous;
		public Node() {
			
		}
		public Node(String name, String imagePath, int priority, int x, int y, int level, String type, boolean damage, boolean cooldown) {
			this.name = name; this.imagePath = imagePath; this.priority = priority; this.x = x; this.y = y; this.level = level; this.type = type; 
			this.damage = damage; this.cooldown = cooldown;
			loadImage();//get the image
		}
		private void loadImage() {
			try {
				image = ImageIO.read(new File(imagePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/
}
