package Weapon;
import Job.*;
public abstract class Weapon
{
	protected int lv,money;
	protected String name;
	public Weapon(String name,int lv,int money)
	{
		this.name=name;
		this.lv=lv;
		this.money=money;
	}
	public abstract void give(Job job); 
	public abstract void unGive(Job job); 
	public int getLV()
	{
		return lv;
	}
	public String getName()
	{
		return name;
	}
	public String toString()
	{
		return "name: "+name+" lv: "+lv;
	}
}