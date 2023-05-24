import java.io.*;
import java.util.*;
import map.Map;
import Job.*;
import Monster.*;
import Weapon.*;
public class hw5
{
	static char[][] map_1=new char[7][7];
	static char[][] map_2=new char[7][7];
	static boolean show_ch,show_weapon,show_instruction;
	static Job current_job=null;
	static Map current_map=null;
	static Weapon current_weapon=null;
	public static void main(String[] args) /*throws IOException*/
	{
		String start=null,cont;//instruction
		String weapon_str="";//ch's weapon list
		String[] s={};//ch's information
		Map map1=null,map2=null;
		Weapon weapon;
		System.out.println("Welcome!!");
		boolean correct=false,save=false,win=false;//correct:enter correct new or continue
		while(true)
		{
			if(!save)
			{
				System.out.print("Enter new ");
				if(saveFolderHasFile())
					System.out.println("or continue");
				System.out.println();
				start=ConsoleIn.readLine();
			}
				
			if(start.equals("new") || start.equals("continue") )
			{	
				if(start.equals("new") && !save)
				{
					correct=true;
					readMap("map/map.txt");
					map1=new Map(map_1);
					
					readMap("map/map2.txt");
					map2=new Map(map_2);
					
					map1.setDoor(map2);
					map2.setDoor(map1);
					current_map=map1;
					boolean correct_job=false;//enter correct job
					while(!correct_job)//enter name and job
					{
						System.out.println("Choose job");
						System.out.println("Warrior Bowman Mage Thief");
						String job=ConsoleIn.readLine();
						System.out.println("Enter Name");
						String name=ConsoleIn.readLine();
						//current_job=new Warrior(name,job,1,0,0);//test
						
						switch(job)
						{
							case "Warrior":
							{
								current_job=new Warrior(name,"Warrior",1,0,0,current_weapon,"");
								correct_job=true;
								break;
							}
							case "Bowman":
							{
								current_job=new Bowman(name,"Bowman",1,0,0,current_weapon,"");
								correct_job=true;
								break;
							}
							case "Mage":
							{
								current_job=new Mage(name,"Mage",1,0,0,current_weapon,"");
								correct_job=true;
								break;
							}
							case "Thief":
							{
								current_job=new Thief(name,"Thief",1,0,0,current_weapon,"");
								correct_job=true;
								break;
							}
							
							default:
								System.out.println("Please choose correct job!");
						}
					}
				}
				else if(start.equals("continue") || save)
				{
					//set ch
					s=new String[11];
					int index=0;
					try
					{
						Scanner scan=new Scanner(new FileInputStream("save/role_save.txt"));
						if(scan.hasNextLine())
						{
							Scanner str=new Scanner(scan.nextLine());
							str.useDelimiter("-");
							while(str.hasNext())
								s[index++]=str.next();
						}	
						if(scan.hasNextLine())
						{
							weapon_str=scan.nextLine();
						}
						correct=true;
					}
					catch(FileNotFoundException e)
					{
						System.out.println("File not found,U must enter new to start");
					}
					//for(int i=0;i<11;i++) System.out.println(s[i]);//test
					if(correct)
					{
						switch(s[7].split("_")[0])
						{
							case "Sword":
							{
								current_weapon=new Sword(Integer.valueOf(s[7].split("_")[1]) );
								break;
							}
							case "Bow":
							{
								current_weapon=new Bow(Integer.valueOf(s[7].split("_")[1]) );
								break;
							}
							case "Staff":
							{
								current_weapon=new Staff(Integer.valueOf(s[7].split("_")[1]) );
								break;
							}
							case "Dagger":
							{
								current_weapon=new Dagger(Integer.valueOf(s[7].split("_")[1]) );
								break;
							}
						}
						if(s[0].equals("Warrior"))//Warrior
							current_job=new Warrior(s[1],"Warrior", Integer.valueOf(s[2]), Integer.valueOf(s[3]), Integer.valueOf(s[4]), current_weapon, weapon_str);
						else if(s[0].equals("Bowman"))//Bowman
							current_job=new Bowman(s[1],"Bowman", Integer.valueOf(s[2]), Integer.valueOf(s[3]), Integer.valueOf(s[4]), current_weapon, weapon_str );
						else if(s[0].equals("Mage"))//Mage
							current_job=new Mage(s[1],"Mage", Integer.valueOf(s[2]), Integer.valueOf(s[3]), Integer.valueOf(s[4]), current_weapon, weapon_str );
						else if(s[0].equals("Thief"))//Thief
							current_job=new Thief(s[1],"Thief", Integer.valueOf(s[2]), Integer.valueOf(s[3]), Integer.valueOf(s[4]), current_weapon, weapon_str );
						if(current_weapon!=null) current_weapon.give(current_job);//add arms ability
						//set map
						readMap("save/map1_save.txt");
						if(s[8].equals("map1"))//ch in map1
							map1=new Map(map_1,Integer.valueOf(s[9]),Integer.valueOf(s[10]));
						else map1=new Map(map_1);
						readMap("save/map2_save.txt");
						if(s[8].equals("map2"))//ch in map2
							map2=new Map(map_2,Integer.valueOf(s[9]),Integer.valueOf(s[10]));
						else map2=new Map(map_2);
						
						map1.setDoor(map2);
						map2.setDoor(map1);
						if(s[8].equals("map1"))
							current_map=map1;
						else//map2
							current_map=map2;
					}
				}
			}
		
			Arena arena1=new Arena();
			while(correct)
			{
				//current_map.show();	//map show
				printScreen();//print screen
				//instruction
				char c='0';//initial
				boolean had_fight=false;
				show_ch=false;
				show_weapon=false;
				show_instruction=false;
				System.out.println("Enter help to see the details of instruction");
				cont=ConsoleIn.readLine();//enter instruction
				String[] instruction=cont.split("-");
				if(cont.equals("w")|| cont.equals("a") || cont.equals("s") || cont.equals("d") )//move up left down right
				{
					current_map.setPlayerPosition(cont);	
					c=current_map.getPlayerPosition();
					switch(c)
					{
						case '*'://door
						{
							current_map=current_map.getMap();
							break;
						}
						case 'S'://Store
						{
							clearConsole();
							Store store1=new Store();
							while(true)
							{
								//show player information
								store1.show(current_job);
								cont=ConsoleIn.readLine();
								String[] store_str=cont.split("-");
								if(store_str[0].equals("buy"))
								{
									clearConsole();
									store1.buyAndSell(current_job,store_str[1],store_str[2],true);//store_str[1]:staff store_str[2]:lv
								}
								else if(store_str[0].equals("sell"))
								{
									clearConsole();
									store1.buyAndSell(current_job,store_str[1],store_str[2],false);//store_str[1]:staff store_str[2]:lv
								}
								else if(store_str[0].equals("exit")) break;
							}
							break;
						}
						case 'G'://Goblin
						{
							had_fight=true;
							Goblin goblin=new Goblin();
							arena1.fight(current_job,goblin);
							break;
						}
						case 'M'://Mermaid
						{
							had_fight=true;
							Mermaid mermaid=new Mermaid();
							arena1.fight(current_job,mermaid);
							break;
						}
						case 'O'://Orc 
						{
							had_fight=true;
							Orc orc=new Orc();
							arena1.fight(current_job,orc);
							break;
						}
						case 'D'://Fire Dragon
						{
							had_fight=true;
							Fire_Dragon fire_dragon=new Fire_Dragon();
							arena1.fight(current_job,fire_dragon);
							break;
						}
						case 'C'://Mimic Chest 
						{
							had_fight=true;
							Mimic_Chest chest=new Mimic_Chest();
							arena1.fight(current_job,chest);
							break;
						}
						case 'H'://Witch Hunter
						{
							had_fight=true;
							Witch_Hunter hunter=new Witch_Hunter();
							arena1.fight(current_job,hunter);
							break;
						}
	
					}
				}
				else if(instruction[0].equals("show"))//show
					show_ch=true;
	
				else if(instruction[0].equals("save"))//save map and ch
				{
					try
					{
						//save map
						PrintWriter writer1,writer2;
						writer1=new PrintWriter(new FileOutputStream("save/map1_save.txt"));
						writer2=new PrintWriter(new FileOutputStream("save/map2_save.txt"));
						for(int i=0;i<7;i++)
						{
							for(int j=0;j<7;j++)
							{
								writer1.print(map1.getMap(i,j));
								writer2.print(map2.getMap(i,j));
							}
							writer1.println();
							writer2.println();
						}
						writer1.flush();
						writer2.flush();
						
						//save ch
						writer1=new PrintWriter(new FileOutputStream("save/role_save.txt"));
							writer1.print(current_job.generateChStr());
						if(current_map==map1) //save ch in which map
							writer1.print("-map1");
						else writer1.print("-map2");
						writer1.print("-");
						writer1.print(current_map.getPlayerPosition_x());//save ch pos_x
						writer1.print("-");
						writer1.print(current_map.getPlayerPosition_y());//save ch pos_y
						writer1.println();
						writer1.print(current_job.getWeaponStr());//save ch's weapon
						writer1.flush();
						writer1.close();
						writer2.close();
						
						save=true;
					}
					catch(FileNotFoundException e)
					{
						System.out.println("Cant Created");
					}
				}
				else if(instruction[0].equals("equip"))//equip
				{
					Equip equip1=new Equip();
					switch(instruction[1])//create arms object
					{
						case "Sword":
						{
							weapon=new Sword(Integer.valueOf(instruction[2]) );
							equip1.equip(current_job,weapon);//wear arms
							break;
						}
						case "Bow":
						{
							weapon=new Bow(Integer.valueOf(instruction[2]) );
							equip1.equip(current_job,weapon);//wear arms
							break;
						}
						case "Staff":
						{
							weapon=new Staff(Integer.valueOf(instruction[2]) );
							equip1.equip(current_job,weapon);//wear arms
							break;
						}
						case "Dagger":
						{
							weapon=new Dagger(Integer.valueOf(instruction[2]) );
							equip1.equip(current_job,weapon);//wear arms
							break;
						}
						default:
							System.out.println("沒有該裝備");
					}
				}
				else if(instruction[0].equals("unequip"))//unequip
				{
					current_job.addWeapon();//add to weapon list
					current_job.getCurrentWeapon().unGive(current_job);//resst ch 
					current_job.setCurWeapon(null);//rest current_weapon
				}
				else if(instruction[0].equals("weapon"))//weapon
				{
					show_weapon=true;
				}
				else if(cont.equals("help"))//help
				{
					show_instruction=true;
				}
				else
				{
					System.out.println("請輸入正確的指令");
				}
					
				if(current_job.getHP()<=0)//player die
					break;
				else if(current_job.getHP()>0 && had_fight)//monster die
				{
					if(c=='D')//fire_dragon
					{
						win=true;
						break;
					}
					current_map.setMap();//remove monster from map
				}
				clearConsole();	
			}
			if(win) break;	
		}
	}
	public static void pressCToContinue()
	{
		System.out.println("Press c to comtinue");
		String c;
		while(true)
		{
			c=ConsoleIn.readLine();
			if(c.equals("c"))break;
		}
	}
	public static void printScreen()
	{
		current_map.show();	//map show
		if(show_ch)
			System.out.println(current_job);//show player information
		if(show_weapon)//show weapon information
			current_job.showWeapon();
		if(show_instruction)
		{
			System.out.println("Instruction       Description");
			System.out.println("help              get instruction description");
			System.out.println("show              get character information");
			System.out.println("save              save map and character information");
			System.out.println("weapon            show character's weapon");
			System.out.println("equip             equip with weapon");
			System.out.println("unequip           unequip with weapon");
			System.out.println("w                 Go Up");
			System.out.println("s                 Go Down");
			System.out.println("a              	  Go Left");
			System.out.println("d                 Go Right");
		}
	}
	public final static void clearConsole()
	{
		try{
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();  
		}catch(Exception e){
			System.out.println("cant clear");
		}
	}
	public static boolean saveFolderHasFile()
	{
		try
		{
			Scanner scan=new Scanner(new FileInputStream("save/role_save.txt"));
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
	public static void readMap(String str)
	{
		FileReader fr;
		try
		{
			fr = new FileReader(str);
			BufferedReader br = new BufferedReader(fr);
			int row=0;
			try
			{
				br.ready();
				while (br.ready()) 
				{
					try
					{
						String s=br.readLine();
						for(int j=0;j<7;j++)
							if(str.equals("map/map.txt") || str.equals("save/map1_save.txt"))
								map_1[row][j]=s.charAt(j);
							else if(str.equals("map/map2.txt") || str.equals("save/map2_save.txt"))
								map_2[row][j]=s.charAt(j);
						row+=1;
						try
						{
							fr.close();
						}
						catch(IOException e)
						{
							System.out.println("cant close"+str);
						}
					}
					catch(IOException e)
					{
						System.out.println("cant read"+str);
					}	
				}
			}
			catch(IOException e)
			{
				System.out.println("br not ready"+str);
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("file not found"+str);
		}	
	}
}