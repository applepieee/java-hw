package Monster;
import Job.*;
public abstract class Monster
{
	protected String name;
	protected int hp,max_hp,normal_attack,get_money,get_exp,round;
	public Monster(String name,int hp,int normal_attack,int get_money,int get_exp)
	{
		this.name=name;
		this.hp=hp;
		this.max_hp=hp;
		this.normal_attack=normal_attack;
		this.get_money=get_money;
		this.get_exp=get_exp;
		this.round=0;
	}
	public void setHP(int attack)
	{
		hp-=attack;
	}
	public void Attack(Job job)
	{
		job.setHP(normal_attack);
		System.out.println("Damage: "+normal_attack);
	}
	public boolean specialTime(int round)
	{
		//System.out.println("specialTime");
		return false;
	}
	public void specialAttack(Job job)
	{
		System.out.println("specialAttack");
	}
	
	public int getExp()
	{
		return get_exp;
	}
	
	public int getMoney(Job job)
	{
		return get_money;
	}
	
	public int getHP()
	{
		return hp;
	}
	public int getMaxHP()
	{
		return max_hp;
	}
	public String toString()
	{
		return "Name: "+name+" HP: "+hp;
	}
	
}
