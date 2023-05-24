import Job.*;
import Monster.*;
public class Arena{
	private int round;
	public Arena()
	{
		round=1;
	}
	public void fight(Job job,Monster monster)
	{
		round=1;//initial
		int normal_attack_cnt=0;//count normal_attack times
		int magic_attack_cnt=0;//count normal_attack times
		while(!End(job,monster))
		{
			job.setTmpAttack(monster,normal_attack_cnt);
			show(job,monster);//show player and monster information
			if(job.getFreeze()>0)
				job.unFreeze();
			//monster attack
			if(monster.specialTime(round))//specialAttack
				monster.specialAttack(job);
			else monster.Attack(job);//normal attack
			//player attack
			if(job.getFreeze()==0 && job.getHP()>0)//unfreeze 
			{
				//player choose normal or magic attack
				System.out.println("a--normal attack");
				if(job.getMP()>=job.getMPLoss())
					System.out.println("m--magic attack");
				boolean right_attack=false;
				while(!right_attack)
				{
					String str=ConsoleIn.readLine();
					if(str.equals("a"))
					{
						normal_attack_cnt++;
						right_attack=true;
						job.Attack(monster);
					}
					else if(str.equals("m") && (job.getMP()>=job.getMPLoss() ))//magic attack need mp > mp_loss
					{
						magic_attack_cnt++;
						right_attack=true;
						job.magicAttack(monster,magic_attack_cnt);
					}
					else
						System.out.println("error attack,pls enter input again");
				}
			}
			else if(job.getFreeze()>0)//freeze
				System.out.println("Freezing");
			round+=1;
		}
	}
	public boolean End(Job job,Monster monster)
	{
		
		if(job.getHP()<=0)//player die
			System.out.println("U Lose,Game Over!!");
		
		else if(monster.getHP()<=0 && job.getHP()>0)//monster die
		{
			//set exp
			System.out.println("EXP: +"+monster.getExp()+"  Money: +"+monster.getMoney(job));
			job.setExp(monster.getExp());
			//set monry
			job.setMoney(monster.getMoney(job));
			//set attack and hp and mp
			job.setTmpAttack(null,0);//reset tmp_attack after fight
			job.setHP();//full hp after fight
			job.setMP();//full mp after fight
			if(monster instanceof Fire_Dragon) System.out.println("U Win,Game Over!!");
				
		}
		
		if(job.getHP()<=0 || monster.getHP()<=0)
		{
			hw5.pressCToContinue();
			return true; 
		}
		return false;
	}
	public void show(Job job,Monster monster)//show player and monster information
	{
		hw5.clearConsole();
		System.out.println("Round: "+round);
		System.out.println(monster);
		job.show();
	}
}