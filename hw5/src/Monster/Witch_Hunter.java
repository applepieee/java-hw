package Monster;
import Job.*;
public class Witch_Hunter extends Monster
{
	public Witch_Hunter()
	{
		super("Witch Hunter",500,20,20,300);
	}
	public boolean specialTime(int round)
	{
		if(round % 2==0) return true;
		return false;
	}

	public void specialAttack(Job job)
	{
		if(job.getMP()<=0)
		{
			job.setHP(normal_attack);
			System.out.println("Damage "+normal_attack);
		}
		else 
		{
			if(job instanceof Mage)
			{
				job.setMP(30);
				System.out.println("Lose 30 MP");
			}	
			else
			{
				job.setMP(10);
				System.out.println("Lose 10 MP");
			}
		}
	}
}