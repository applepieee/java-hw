package Weapon;
import Job.*;
public class Staff extends Weapon
{
	public Staff(int lv) 
	{
		super("Staff",lv,30+20*lv);
	}
	public void give(Job job)
	{
		if(lv==1)
			job.setMaxMP(20);
		else if(lv==2)
			job.setMaxMP(30);
		else if(lv==3)
			job.setMaxMP(40);
	}
	public void unGive(Job job)
	{
		if(lv==1)
			job.setMaxMP(-20);
		else if(lv==2)
			job.setMaxMP(-30);
		else if(lv==3)
			job.setMaxMP(-40);
		
	}
}