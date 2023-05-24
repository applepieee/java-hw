package Monster;
import Job.Job;
public class Fire_Dragon extends Monster
{
	public Fire_Dragon()
	{
		super("Fire Dragon",500,50,30,400);
	}
	public boolean specialTime(int round)
	{
		if(hp<60) return true;
		return false;
	}

	public void specialAttack(Job job)
	{
		
		job.setHP(10000);
		System.out.println("10000 Damage");
	}
}