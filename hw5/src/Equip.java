import Job.*;
import Weapon.*;
public class Equip
{
	public void equip(Job job,Weapon weapon)
	{
		if(job.searchWeapon(weapon))//has the weapon
		{	
			if(job.getCurrentWeapon() !=null)
			{
				job.getCurrentWeapon().unGive(job);
				job.addWeapon();
			}
			weapon.give(job);
			job.removeWeapon(weapon);
			job.setCurWeapon(weapon);
		}
		else 
		{
			if( job.getCurrentWeapon() !=null && weapon.getName().equals(job.getCurrentWeapon().getName()) && weapon.getLV()==job.getCurrentWeapon().getLV() ) System.out.println("已穿上該裝備");
			else System.out.println("沒有該裝備");
			hw5.pressCToContinue();
		}
	}
}