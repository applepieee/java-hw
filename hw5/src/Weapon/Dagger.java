package Weapon;
import Job.*;
public class Dagger extends Weapon
{
	public Dagger(int lv) 
	{
		super("Dagger",lv,30+20*lv);
	}
	public void give(Job job)
	{
		if(lv==1)
			job.setNormalAttack(20);
		else if(lv==2)
			job.setNormalAttack(25);
		else if(lv==3)
			job.setNormalAttack(30);
	}
	public void unGive(Job job)
	{
		if(lv==1)
			job.setNormalAttack(-20);
		else if(lv==2)
			job.setNormalAttack(-25);
		else if(lv==3)
			job.setNormalAttack(-30);
		
	}
}