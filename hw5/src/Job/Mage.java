package Job;
import Monster.*;
import Weapon.*;
public class Mage extends Job
{
	public Mage(String name,String job,int lv,int exp,int money,Weapon current_weapon,String weapon)
	{
		super(name,job,lv, exp, money, 100+(lv-1)*5, 10+(lv-1)*20, 50+(lv-1)*2,10,current_weapon,weapon);//int lv,int exp,int money,int hp,int mp,int normal_attack,int mp_loss
		this.magic_attack=normal_attack+(int)(0.3*max_mp);
	}
	
	public void magicAttack(Monster monster,int magic_attack_cnt)
	{
		if(!(current_weapon instanceof Staff) || !( magic_attack_cnt%3==1 && magic_attack_cnt!=1) )
			mp-=mp_loss;	
		
		monster.setHP(magic_attack);
	}
	public void setMagicAttack()
	{
		magic_attack=normal_attack+(int)(0.3*max_mp);
	}
	public void levelUp()
	{
		int need_exp=this.lv*100;
		if(need_exp<=exp)
		{
			exp-=need_exp;
			lv+=1;
			max_hp+=5;
			max_mp+=20;
			normal_attack+=2;
			setMagicAttack();
			System.out.println("Level Up to "+lv);
		}
	}
}