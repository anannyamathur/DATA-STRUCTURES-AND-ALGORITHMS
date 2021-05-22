import java.io.*;
import java.util.*;

class set{
		String str_set;
		String finalstr;
	
		set link;
		set(String s, String sortedstr, set linked)
		{   
			str_set=s;
			finalstr=sortedstr;
			link=linked;
		
		}
		set(String s, String sortedstr)
		{   
			str_set=s;
			finalstr=sortedstr;
		    link=null;
		}
		
		
	}

 class hash
	{
		set[] h;
		
		hash(int maxSize)
		{
			h= new set[maxSize];
			
		}
	}


public class Anagram
{   //100003 is the smallest 6-digit prime number.
	static hash Hash;
	static int N;
	
	public Anagram(int maxSize)
	{   N=maxSize;
		Hash=new hash(N);
	}
	
	
	
	
	static String sortfn(String str)
	{
		char[] characters= str.toCharArray();
		Arrays.sort(characters);
		
		return new String(characters);
	}
	
	
	
	
	
	private static int shrink( char ch )
	{
		int value= (int)ch;
		if(value==39)
		{
			value=10;
		}
		
		else if(value>=48 && value<=57)
		{
			value=value-48;
		}
		else if(value>=97)
		{
			value= value-86;
		}
		return value;
		
	}
	
	private static int generateHash(String str)
	{   
		int val=0;
		for(int i=0; i<str.length(); i++)
		{
			val= ((36*val)+ shrink(str.charAt(i)))%N;
		}
		return val;
	}
	
	
	
	
	
	
	
	
		
	static void insertfn(String str)
		{
			String finalstr= sortfn(str);
			int hashing= generateHash(finalstr);
			
			if(Hash.h[hashing]==null)
			{
				Hash.h[hashing]= new set(str, finalstr);
			}
			else{
				
					Hash.h[hashing]= new set(str, finalstr, Hash.h[hashing]);
				}
				
			}
		
	
	static Vector<String> generateWords(String str)
	{
		String finalstr=str;
		int hashing= generateHash(finalstr);
		Vector<String> list1= new Vector<String>();
		set list2= Hash.h[hashing];
		while(list2!=null)
		{
			if((list2.finalstr).equals(finalstr))
			{
				list1.add(list2.str_set);
				
			}
			
				list2=list2.link;
			
		}
		return list1;
	}
	
	
	
	static Vector<String> generatePermutation(Vector<Vector<String>> words)
	{   Vector<Vector<String>> subset;
	    Vector<String> iterate;
		Vector<String> answer= new Vector<String>();
		Vector<String> list;
		
		if(words.size()==1)
		{
		
			return words.get(0);
		}
		
		
		
		else{
			for(int i=0; i<words.size(); i++)
			{
				list= words.get(i);
				subset=new Vector<Vector<String>>();
				for(int j=0; j<words.size();j++)
				{
					if(i!=j)
					{
						subset.add(words.get(j));
					}
				}
				iterate=generatePermutation(subset);
				for(int a=0; a<list.size(); a++)
				{
					for(int b=0; b<iterate.size(); b++)
					{
						answer.add(list.get(a) + " " + iterate.get(b));
					}
				}
			}
			return answer;
		}
		
	}
	
	
	static Vector<String> AnagramGenerator(String s, String group)
	{   
		Vector<String> answer= new Vector<String>();
		Vector<Vector<String>> list= new Vector<Vector<String>>();
		
		String s1="";
	
		String s2="";
		
		String s3="";
		String str;
		for(int i=0; i<group.length(); i++)
		{
			str= group.substring(i,i+1);
			if(str.equals("0"))
			{
			
				s1=s1+s.substring(i,i+1);
				
			}
			else if(str.equals("1"))
			{
				
				s2=s2+s.substring(i,i+1);
			}
			else if(str.equals("2"))
			{
			
				s3=s3+s.substring(i,i+1);
			}
		}	
		
		if(!s1.equals(""))
		{Vector<String> l1= generateWords(s1);
		
		if(l1.size()==0)
		return answer;
		
		list.add(l1);
		}
		if(!s2.equals(""))
		{
			Vector<String> l2=generateWords(s2);
			if(l2.size()==0)
			return answer;
			list.add(l2);
		}
		if(!s3.equals(""))
		{
			Vector<String> l3= generateWords(s3);
			if(l3.size()==0)
			 return answer;
			list.add(l3);
		}
		
		answer= generatePermutation(list);
		return answer;
	}
		
	/*static boolean check(String tocheck)
	{
		boolean ch= true;
		
		
		
		for(int i=0; i<tocheck.length(); i++)
		{
			
			if(i==0)
				{	if(tocheck.charAt(0)!= '0')
			{
				return false;
			}
			
				}
				
				else
				
				{	
				if(tocheck.charAt(i)!= '0')
				{
					
					ch= tocheck.charAt(i)== '1';
					
					
				}
				}
			
			
			
		}
		
		return ch;
	}
	*/
	
   static boolean checking(String tocheck)
   {
	   boolean ch= true;
		
		
		
		for(int i=0; i<tocheck.length(); i++)
		{
			
			if(i==0)
				{	if(tocheck.charAt(0)!= '0')
			{
				return false;
			}
			
				}
				
				else
				
				{	
				if(tocheck.charAt(i)!= '0')
				{
					
					return tocheck.charAt(i)== '2';
					
					
				}
				}
			
			
			
		}
		
		return ch;
   }
	
	

    static Vector<String> duplicate(Vector<String> ans)
	{
		Vector<String> finalans= new Vector<String>();
		int i=0;
		while(i<ans.size()-1)
		{
			if(ans.get(i).equals(ans.get(i+1)))
			{
				//finalans.add(ans.get(i));
				i=i+1;
			}
			else
			{
				finalans.add(ans.get(i));
				i=i+1;
			}
		}
		if(i==ans.size()-1)
		{
			finalans.add(ans.get(i));
		}
		return finalans;
	}
	
	static Vector<String> finalfn(String str)
	{
		Vector<String> answer = new Vector<String>();
		
        
        String s = sortfn(str);
        String group="";
		
        String temp="";
		int n=(int)Math.pow(3, s.length());
        for (int i=0; i<n/3; i++) 
		{
            group = Integer.toString(i,3);
            temp = "";
            for (int j=0; j<(s.length()-group.length()); j++) {
                temp= temp + "0";
            }
            group = temp + group;
            if (checking(group)) 
			
			   
                answer.addAll(AnagramGenerator(s, group));
				
           

        }
		
        Collections.sort(answer);
		answer=duplicate(answer);
		

        return answer;
	}
	
	
	
	public static void main(String args[])
	{
		Anagram puzzle= new Anagram(100003);
		File file1= new File(args[0]);
		File file2= new File(args[1]);
		BufferedReader vocabfile;
		BufferedReader inputfile;
		try
		{   
			vocabfile= new BufferedReader(new FileReader(file1));
		
			inputfile=new BufferedReader(new FileReader(file2));
			
			String vocabulary= vocabfile.readLine();
			vocabulary= vocabfile.readLine();
			while(vocabulary!=null)
			{
				puzzle.insertfn(vocabulary);
				vocabulary= vocabfile.readLine();
			}
			
			Vector<String> ans;
			int num=Integer.parseInt(inputfile.readLine());
			
			for(int i=0; i<num; i++)
			{
				vocabulary=inputfile.readLine();
				ans= puzzle.finalfn(vocabulary);
				
					for(int j=0; j<ans.size(); j++)
					{
						System.out.println(ans.get(j));
					}
					System.out.println("-1");
				
			}
			
		}
		catch(IOException e)
		{
			System.out.println("File not found");
		}
		
	
}
}
