package Weapon;
import Job.*;
public class Bow extends Weapon
{
	public Bow(int lv) 
	{
		super("Bow",lv,30+20*lv);
	}
	public void give(Job job)
	{
		if(lv==1)
		{
			job.setNormalAttack(15);
			job.setMaxMP(10);
		}
		else if(lv==2)
		{
			job.setNormalAttack(20);
			job.setMaxMP(80);
		}
		else if(lv==3)
		{
			job.setNormalAttack(25);
			job.setMaxMP(20);
		}
	}
	public void unGive(Job job)
	{
		if(lv==1)
		{
			job.setNormalAttack(-15);
			job.setMaxMP(-10);
		}
		else if(lv==2)
		{
			job.setNormalAttack(-20);
			job.setMaxMP(-80);
		}
		else if(lv==3)
		{
			job.setNormalAttack(-25);
			job.setMaxMP(-20);
		}
	}
}