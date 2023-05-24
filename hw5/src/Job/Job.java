package Job;
import Monster.*;
import Weapon.*;
import java.util.*;
public abstract class Job
{
	protected String name,job;
	protected int lv,exp,money,hp,mp,normal_attack,magic_attack,mp_loss,freeze,max_hp,max_mp,tmp_attack;
	protected Weapon current_weapon;
	protected String weapon_str;
	protected ArrayList<String> weapon=new ArrayList<>();
	public Job(String name,String job,int lv,int exp,int money,int hp,int mp,int normal_attack,int mp_loss,Weapon current_weapon,String weapon_str)
	{
		this.name=name;
		this.job=job;
		this.lv=lv;
		this.exp=exp;
		this.money=money;
		this.hp=hp;
		this.max_hp=hp;
		this.mp=mp;
		this.max_mp=mp;
		this.normal_attack=normal_attack;
		this.mp_loss=mp_loss;
		this.freeze=0;
		this.current_weapon=current_weapon;
		this.weapon_str=weapon_str;
		if(!weapon_str.equals(""))
		{
			String[] s=weapon_str.split("-");
			for(int i=0;i<s.length;i++)
				weapon.add(s[i]);
		}
		this.tmp_attack=normal_attack;
	}
	public void setCurWeapon(Weapon weapon)
	{
		this.current_weapon=weapon;
	}
	public void addWeapon()//for unweapon
	{
		String weapon_name=current_weapon.getName();
		String weapon_lv=Integer.toString(current_weapon.getLV());
		String str=weapon_name+"_"+weapon_lv;
		this.weapon.add(str);
	}
	public void addWeapon(String weapon_name,String weapon_lv)//for buy
	{
		String str=weapon_name+"_"+weapon_lv;
		this.weapon.add(str);
	}
	public void removeWeapon(Weapon weapon)
	{
		String weapon_name=weapon.getName();
		String weapon_lv=Integer.toString(weapon.getLV());
		String str=weapon_name+"_"+weapon_lv;
		
		this.weapon.remove(str);
	}
	public void removeWeapon(String weapon_name,String weapon_lv)//for sell
	{
		String str=weapon_name+"_"+weapon_lv;
		this.weapon.remove(str);
	}
	public void setNormalAttack(int num)//for equip
	{
		normal_attack+=num;
		setTmpAttack(null,0);
		setMagicAttack();
	}
	public void setTmpAttack(Monster monster,int normal_attack_cnt)//equip and after fight
	{
		tmp_attack=normal_attack;//after fight and equip
		if(monster !=null)
		{
			if( (job.equals("Warrior")) && (normal_attack_cnt>=4) && (current_weapon instanceof Sword) )
				tmp_attack=normal_attack+5*current_weapon.getLV();
			else if( (job.equals("Bowman")) && monster.getHP()>( (int)(monster.getMaxHP()*0.7) ) && (current_weapon instanceof Bow) )
				tmp_attack=normal_attack+5*current_weapon.getLV();
			else if( (job.equals("Thief")) && monster.getHP()<( (int)(monster.getMaxHP()*0.3) ) && (current_weapon instanceof Dagger) )
				tmp_attack=normal_attack+5*current_weapon.getLV();
		}
	}
	public abstract void setMagicAttack();
	public void setHP(int num)//for monster attack
	{                           
		hp-=num;
	}
	public void setMaxHP(int num)
	{
		max_hp+=num;
		setHP();
	}
	public void setMaxMP(int num)
	{
		max_mp+=num;
		setMP();
		setMagicAttack();
	}
	public void setHP()//after fight full hp
	{
		hp=max_hp;
	}
	public void setMP()//after fight full mp
	{
		mp=max_mp;
	}
	public void setMP(int num)//for monster special attack
	{
		mp-=num;
		if(mp<0) mp=0;
	}
	public void setExp(int get_exp)
	{
		exp+=get_exp;
		levelUp();
	}
	public void setMoney(int get_money)//get_money positive when u kill monster and sell weapon; negative whem u buy something
	{
		money+=get_money;
		setMagicAttack();
	}
	public void setFreeze(int freeze)
	{
		this.freeze=freeze;
	}
	public void unFreeze()
	{
		this.freeze-=1;
	}
	public int getHP()
	{
		return hp;
	}
	public int getMP()
	{
		return mp;
	}
	public int getMPLoss()
	{
		return mp_loss;
	}
	public int getFreeze()
	{
		return freeze;
	}
	public Weapon getCurrentWeapon()
	{
		return current_weapon;
	}
	public int getMoney()
	{
		return money;
	}
	public void Attack(Monster monster)//normal attack
	{
		monster.setHP(tmp_attack);
	}
	public abstract void magicAttack(Monster monster,int magic_attack_cnt);//magic attack
	public abstract void levelUp();

	public boolean searchWeapon(Weapon weapon)
	{
		for(int i=0;i<this.weapon.size();i++)
		{
			String[] s=this.weapon.get(i).split("_");
			if( s[0].equals(weapon.getName()) && Integer.valueOf(s[1])==weapon.getLV() )//weapon's name and lv must be correct
				return true;
		}				
		return false;		
	}
	public boolean searchWeapon(String name,String lv)
	{
		for(int i=0;i<this.weapon.size();i++)
		{
			String[] s=this.weapon.get(i).split("_");
			if( s[0].equals(name) && s[1].equals(lv) )//weapon's name and lv must be correct
				return true;
		}				
		return false;
	}
	public String toString()
	{
		return "job: " + job+ " name: " + name + " lv: "+ lv + " exp: "+ exp + " money: "+ money + " hp: "+ max_hp +" mp: "+ max_mp ;

	}
	public void show()
	{
		String str;
		str=(freeze>0) ? "Yes" : "No";
		System.out.println("job: " + job+ " name: " + name + " lv: "+ lv+" hp: "+ hp +" mp: "+ mp +" normal_attack: "+tmp_attack+" magic_attack: "+magic_attack+" freeze: "+str);
	}
	public String generateChStr()//for save ch data
	{
		if(current_weapon != null)
			return job+"-"+name+"-"+lv+"-"+exp+"-"+money+"-"+hp+"-"+mp+"-"+current_weapon.getName()+"_"+current_weapon.getLV();
		else
			return job+"-"+name+"-"+lv+"-"+exp+"-"+money+"-"+hp+"-"+mp+"-"+"No current weapon"+"_"+"0";
	}
	public String getWeaponStr()//for save player's weapon //need revise
	{
		weapon_str="";
		for(int i=0;i<weapon.size();i++)
		{	
			weapon_str+=weapon.get(i);
			if(i != weapon.size()-1)
				weapon_str+="-";
		}
		return weapon_str;
	}
	public void showWeapon()
	{
		System.out.print("目前裝備: ");
		if(current_weapon!=null)
			System.out.println(current_weapon);
		else
			System.out.println("沒有裝備");
		System.out.print("裝備資訊: ");
		System.out.print(getWeaponStr());
		System.out.println();
	}
}