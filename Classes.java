
public class Classes {

	public static int speed;
	public static int vitality;
	public static int mana;
	public static int strength;
	public static int intelegence;
	public static int classNumber;
	
	
	public void setClass(int classOption) {
	//rouge
	if (classOption == 1) {
		speed = 10;
		vitality = 7; 
		mana = 10;
		strength = 10;
		intelegence = 9;
		classNumber = 1;
		System.out.println("number set");
		System.out.println(classNumber);
	}
	//Sorcerer
	else if (classOption == 2) {
		speed = 10;
		vitality = 8; 
		mana = 12;
		strength = 8;
		intelegence = 12;
		classNumber = 2;
		System.out.println("number set");
		System.out.println(classNumber);
	}
	//Knight
	else if (classOption == 3) {
		speed = 8;
		vitality = 13; 
		mana = 8;
		strength = 12;
		intelegence = 9;
		classNumber = 3;
		System.out.println("number set");
		System.out.println(classNumber);
}
	//Pyromancer
	else if (classOption == 4) {
		speed = 7;
		vitality = 12; 
		mana = 11;
		strength = 10;
		intelegence = 10;
		classNumber = 4;
		System.out.println("number set");
		System.out.println(classNumber);
}
	//deprived
	else if (classOption == 5) {
		speed = 7;
		vitality = 7; 
		mana = 7;
		strength = 7;
		intelegence = 7;
		classNumber = 5;
		System.out.println("number set");
		System.out.println(classNumber);}
		else {
			System.out.println("Classes need to be numbers 1-5");
		}
	
}
	

	public int getClassNumber() {
		
		if (classNumber== 1) {
			return 1;
		}
		//Sorcerer
		else if (classNumber == 2) {
			return 2;
		}
		//Knight
		else if (classNumber == 3) {
			return 3;
	}
		//Pyromancer
		else if (classNumber == 4) {
			return 4;
	}
		//deprived
		else if (classNumber == 5) {
			return 5;
	}
		else {return 0;}
}}

