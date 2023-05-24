package Monster;
import Job.*;
public class Mimic_Chest extends Monster
{
	public Mimic_Chest()
	{
		super("Mimic Chest",500,0,20,180);
	}
	public boolean specialTime(int round)
	{
		if(round % 5==0) return true;
		return false;
	}

	public void specialAttack(Job job)
	{
		job.setHP(5000);
		System.out.println("Damage 5000");
	}
	public int getMoney(Job job)
	{
		if(job instanceof Thief)
			return 2*get_money;
		return get_money;
	}
}