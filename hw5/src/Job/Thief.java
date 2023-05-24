package Job;
import Monster.*;
import Weapon.*;
public class Thief extends Job
{
	public Thief(String name,String job,int lv,int exp,int money,Weapon current_weapon,String weapon)
	{
		super(name,job,lv, exp, money, 100+(lv-1)*10, 10+(lv-1)*10, 60+(lv-1)*10,5,current_weapon,weapon);//int lv,int exp,int money,int hp,int mp,int normal_attack,int mp_loss
		this.magic_attack=this.normal_attack+(int)(0.5*money);
	}
	
	public void magicAttack(Monster monster,int magic_attack_cnt)
	{
		mp-=mp_loss;
		monster.setHP(magic_attack);
	}
	public void setMagicAttack()
	{
		magic_attack=normal_attack+(int)(0.5*money);
	}
	public void levelUp()
	{
		int need_exp=this.lv*100;
		if(need_exp<=exp)
		{
			exp-=need_exp;
			lv+=1;
			max_hp+=10;
			max_mp+=10;
			normal_attack+=10;
			setMagicAttack();
			System.out.println("Level Up to "+lv);
		}
	}
}