package Job;
import Monster.*;
import Weapon.*;
public class Warrior extends Job
{
	public Warrior(String name,String job,int lv,int exp,int money,Weapon current_weapon,String weapon)
	{
		super(name,job,lv, exp, money, 100+(lv-1)*20, 10+(lv-1)*5, 70+(lv-1)*10,5,current_weapon,weapon);//int lv,int exp,int money,int hp,int mp,int normal_attack,int mp_loss
		this.magic_attack=(int)(0.5*this.normal_attack);
	}
	
	public void magicAttack(Monster monster,int magic_attack_cnt)
	{
		this.hp+=(int)(0.5*normal_attack);
		mp-=mp_loss;
		monster.setHP(magic_attack);
	}
	public void setMagicAttack()
	{
		magic_attack=(int)(0.5*normal_attack);
	}
	public void levelUp()
	{
		int need_exp=this.lv*100;
		if(need_exp<=exp)
		{
			exp-=need_exp;
			lv+=1;
			max_hp+=20;
			max_mp+=5;
			normal_attack+=10;
			setMagicAttack();
			System.out.println("Level Up to "+lv);
		}
	}
}