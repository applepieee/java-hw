package Monster;
import Job.Job;
public class Mermaid extends Monster
{
	public Mermaid()
	{
		super("Mermaid",200,20,10,90);
	}
	public void Attack(Job job)
	{
		job.setHP(normal_attack);
		hp+=20;
	}
	public boolean specialTime(int round)
	{
		if(round % 3==0) return true;
		return false;
	}

	public void specialAttack(Job job)
	{
		job.setFreeze(2);
		System.out.println("freeze two round");
	}

}