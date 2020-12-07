package tdd;

public class StringCalc 
{
	private static int counter = 0;
	
	public int getCalledCount()
	{
		return counter;
	}
	
	public void noNegative(int[] nos)
	{
		int flag = 0; 
		String msg = "Negative number not allowed ";
		for(int i = 0; i < nos.length; i++)
		{			
			if(nos[i] < 0)
			{
				flag = 1;
				msg = msg + nos[i] + " ";
			}
		}
		
		if(flag == 1)
		{
			throw new IllegalArgumentException(msg);
		}
	}
	
	public String fixSpecialCharDelim(String delim)
	{
		String newdelim = "";
		
		if(delim.contains("*") || delim.contains("+") || delim.contains("."))
		{
			for(int i = 0; i < delim.length(); i++)
			{
				if(delim.charAt(i) ==  '*' || delim.charAt(i) ==  '+' || delim.charAt(i) ==  '.')
				{
					newdelim = newdelim + "\\";
				}
				 
				newdelim = newdelim + delim.charAt(i);
			}
		}
		else
		{
			newdelim = delim;
		}
		
		return newdelim;
	}
	
	public int parseInputAndSum(String[] numbers)
	{
		int[] nos = new int[numbers.length];
		int sum = 0;
		
		for(int i = 0; i < numbers.length; i++)
		{
			nos[i] = Integer.parseInt(numbers[i]);
			
			if(nos[i] > 1000)
			{
				continue;
			}
			sum = sum + nos[i];
		}
		
		noNegative(nos);
		
		return sum;	
	}
	
	public int add(String numbers) 
	{
		this.counter++;
		//System.out.println("entered add with counter = " + counter);
		if(numbers.contentEquals(""))
		{
			return 0;
		}
		else if(numbers.startsWith("//["))
		{
			String arr[] = numbers.split("\n", 2);
			
			String delim = arr[0].substring(3, arr[0].length()-1);
			
			String newdelim = "";
			
			if(delim.contains("]["))
			{
				newdelim = delim.substring(0, delim.length());
				
				String[] dls = newdelim.split("]\\[");
				
				String dlsPattern = "";
				
				for(int i = 0; i < dls.length; i++)
				{
					String fixedDelim = fixSpecialCharDelim(dls[i]);
					dlsPattern = dlsPattern + fixedDelim + "|";
				}
				
				newdelim = dlsPattern.substring(0, dlsPattern.length()-1);
			}
			else
			{
				newdelim = fixSpecialCharDelim(delim);
			}
					
			
			String arr2[] = arr[1].split(newdelim);
			
			int sum = parseInputAndSum(arr2);
			return sum;
		}
		else if(numbers.startsWith("//"))
		{
			String arr[] = numbers.split("\n", 2);
			String delim = arr[0].substring(2,3);
			
			String arr2[] = arr[1].split(delim);
			
			int sum = parseInputAndSum(arr2);
			return sum;
		}
		else
		{
			String arr[] = numbers.split(",|\n");
			
			int sum = parseInputAndSum(arr);
			return sum;
		}
		
	}

}
