package Monster;
import Job.Job;
public class Orc extends Monster
{
	public Orc()
	{
		super("Orc",300,30,15,150);
	}
	public boolean specialTime(int round)
	{
		if(round % 3==0) return true;
		return false;
	}

	public void specialAttack(Job job)
	{
		hp-=(int)(hp*0.5);
		job.setHP((int)(normal_attack*1.5));
		System.out.println("Damage *1.5");
	}
}