package Weapon;
import Job.*;
public class Sword extends Weapon
{
	public Sword(int lv)
	{
		super("Sword",lv,30+20*lv);
	}
	public void give(Job job)
	{
		if(lv==1)
		{
			job.setNormalAttack(10);
			job.setMaxHP(50);
		}
		else if(lv==2)
		{
			job.setNormalAttack(10);
			job.setMaxHP(80);
		}
		else if(lv==3)
		{
			job.setNormalAttack(15);
			job.setMaxHP(100);
		}
	}
	public void unGive(Job job)
	{
		if(lv==1)
		{
			job.setNormalAttack(-10);
			job.setMaxHP(-50);
		}
		else if(lv==2)
		{
			job.setNormalAttack(-10);
			job.setMaxHP(-80);
		}
		else if(lv==3)
		{
			job.setNormalAttack(-15);
			job.setMaxHP(-100);
		}
	}
} 