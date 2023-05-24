import Job.Job;
public class Store
{
	public void buyAndSell(Job job,String name,String lv,boolean buy) 
	{                                               
		if(name.equals("Sword"))
		{
			if(lv.equals("1"))
				if(buy)
					buy_handle(job,50,name,lv);
				else 
					sell_handle(job,50,name,lv);
			else if(lv.equals("2"))
				if(buy) 
					buy_handle(job,70,name,lv);
				else 
					sell_handle(job,70,name,lv);
			else if(lv.equals("3"))
				if(buy) 
					buy_handle(job,90,name,lv);
				else 
					sell_handle(job,90,name,lv);
			else
				System.out.println("輸入等級有誤");
		}
		else if(name.equals("Bow"))
		{
			if(lv.equals("1"))
				if(buy)
					buy_handle(job,50,name,lv);
				else
					sell_handle(job,50,name,lv);
			else if(lv.equals("2"))
				if(buy)
					buy_handle(job,70,name,lv);
				else
					sell_handle(job,70,name,lv);
			else if(lv.equals("3"))
				if(buy)
					buy_handle(job,90,name,lv);
				else
					sell_handle(job,90,name,lv);
			else
				System.out.println("輸入等級有誤");
		}
		else if(name.equals("Staff"))
		{
			if(lv.equals("1"))
				if(buy)
					buy_handle(job,50,name,lv);
				else
					sell_handle(job,50,name,lv);
			else if(lv.equals("2"))
				if(buy)
					buy_handle(job,80,name,lv);
				else
					sell_handle(job,80,name,lv);
			else if(lv.equals("3"))
				if(buy)
					buy_handle(job,110,name,lv);
				else
					sell_handle(job,110,name,lv);
			else
				System.out.println("輸入等級有誤");
		}
		else if(name.equals("Dagger"))
		{
			if(lv.equals("1"))
				if(buy)
					buy_handle(job,50,name,lv);
				else
					sell_handle(job,50,name,lv);
			else if(lv.equals("2"))
				if(buy)
					buy_handle(job,60,name,lv);
				else
					sell_handle(job,60,name,lv);
			else if(lv.equals("3"))
				if(buy)
					buy_handle(job,70,name,lv);
				else
					sell_handle(job,70,name,lv);
			else
				System.out.println("輸入等級有誤");
		}
		else
			System.out.println("沒有該項商品");
	}
	void buy_handle(Job job,int need_money,String name,String lv)//for buy
	{
		if(job.getMoney()>= need_money && !job.searchWeapon(name,lv) && !curWeapon(job,name,lv) ) //ur money>=item's price and dont have the item
		{
			job.addWeapon(name,lv);
			int cost=0-need_money;
			job.setMoney(cost);//cost money
			System.out.println("購買成功");
		}
		else
		{
			if(job.getMoney()>= need_money && (curWeapon(job,name,lv) || job.searchWeapon(name,lv))  )//has the item
				System.out.println("已擁有該項商品無法購買");
			else
				System.out.println("金錢不夠");
		}
	}
	void sell_handle(Job job,int sell_money,String name,String lv)//for sell
	{
		if(job.searchWeapon(name,lv))//has the item
		{
			job.removeWeapon(name,lv);
			job.setMoney(sell_money);//earn money
			System.out.println("販賣成功");
		}	
		else
			System.out.println("你沒擁有該項商品");	
	}
	boolean curWeapon(Job job,String name,String lv)
	{
		if(job.getCurrentWeapon()!=null)
		{
			boolean Name=name.equals(job.getCurrentWeapon().getName());
			String lv_str=Integer.toString(job.getCurrentWeapon().getLV() );
			boolean Lv=lv.equals(lv_str);
			if(Name && Lv) return true;
			else return false;
		}
		else return false;
	}
	public void show(Job job)
	{
		//show player information
		job.showWeapon();
		System.out.println("金錢: "+job.getMoney());
		//show weapon information
		System.out.println("Sword   LV1   50");
		System.out.println("Sword   LV2   70");
		System.out.println("Sword   LV3   90");
		
		System.out.println("Bow     LV1   50");
		System.out.println("Bow     LV2   70");
		System.out.println("Bow     LV3   90");
		
		System.out.println("Staff   LV1   50");
		System.out.println("Staff   LV2   80");
		System.out.println("Staff   LV3   110");
		
		System.out.println("Dagger   LV1   50");
		System.out.println("Dagger   LV2   60");
		System.out.println("Dagger   LV3   70");
		System.out.println("Instruction       Description");
		System.out.println("buy               buy weapon  eg. buy-Sword-1");
		System.out.println("sell              sell weapon eg  sell-Sword-1");
	}
}