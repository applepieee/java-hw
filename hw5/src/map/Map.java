package map;
public class Map{
	private int player_position_x;
	private int player_position_y;
	private char[][] map=new char[7][7];
	private Map door;
	public Map(char[][] map)
	{
		this.map=map;
		search_player_position();
	}
	public Map(char[][] map,int x,int y)
	{
		this.map=map;
		player_position_x=x;
		player_position_y=y;
	}
	void search_player_position()
	{
		boolean find=false;
		for(int i=0;i<7;i++)
			for(int j=0;j<7;j++)
				if(map[i][j]=='P')
				{
					find=true;
					player_position_x=i;
					player_position_y=j;
				}	
		if(!find)
		{
			for(int i=0;i<7;i++)
				for(int j=0;j<7;j++)
					if(map[i][j]=='*')
					{
						player_position_x=i;
						player_position_y=j;
					}	
		}				
	}
	public void setDoor(Map map)
	{
		this.door=map;
	}
	public void show()
	{
		for(int i=0;i<7;i++)
		{
			for(int j=0;j<7;j++)
			{
				if(i==player_position_x && j==player_position_y)
					System.out.print("P");
				else System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println("x: "+player_position_x+"  y: "+player_position_y);
	}
	public void setPlayerPosition(String dir)
	{	
		switch(dir)
		{
			case "w":
			{
				if( (player_position_x-1) >= 0 )
				{
					clearP();
					player_position_x-=1;
				}
				else System.out.println("超出邊界");
				break;
			}
			case "a":
			{
				if( (player_position_y-1) >= 0 )
				{
					clearP();
					player_position_y-=1;
				}
				else System.out.println("超出邊界");
				break;
			}
			case "s":
			{
				if( (player_position_x+1) <= 6 )
				{
					clearP();
					player_position_x+=1;
				}
				else System.out.println("超出邊界");
				break;
			}
			case "d":
			{
				if( (player_position_y+1) <= 6 )
				{
					clearP();
					player_position_y+=1;
				}
				else System.out.println("超出邊界");
				break;
			}
		}
	}
	
	private void clearP()
	{
		if(map[player_position_x][player_position_y]=='P') map[0][0]='N';
	}
	public void setMap()
	{
		map[player_position_x][player_position_y]='N';
	}
	public char getPlayerPosition()//return a map char 
	{
		return map[player_position_x][player_position_y];
	}
	public int getPlayerPosition_x()
	{
		return player_position_x;
	}
	public int getPlayerPosition_y()
	{
		return player_position_y;
	}
	public Map getMap()
	{
		return door;
	}
	public char getMap(int x,int y)
	{
		return map[x][y];
	}
}
