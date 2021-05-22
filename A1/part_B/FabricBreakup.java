import java.io.*;
import java.util.Scanner;
public class FabricBreakup{
	StackInterface goodShirts=new Stack();
	int n=0;
	public class FabricObj
	{int topRate;
	int pos;
	public FabricObj(int ratings, int position)
	{topRate=ratings;
	pos=position;
		
	}}
	

	public void heapTopile(int ratings) throws EmptyStackException
	{
    try 
	{
		int valueOftop;
	 
		if(goodShirts.isEmpty())
		{
		 	valueOftop=-1;
		}
	 else
	    { FabricObj obj= (FabricObj)goodShirts.top();
		 valueOftop=obj.topRate;
			
		}
			
	if(valueOftop<=ratings)
			{ FabricObj obj=new FabricObj(ratings, n);
			  goodShirts.push(obj);	
			}
		
	n=n+1;    
		
	}
	
	catch(EmptyStackException e)
	{
		System.out.println("Empty Stack");
	}
	}
	 public int party() throws EmptyStackException
	 {
	 
	 
		 if(n==0)
		 {
			 return -1;
		 }
		 else
		 { FabricObj obj= (FabricObj)goodShirts.pop();
		 int position= obj.pos;
		 int shirtsTopple= n-position-1;
		 n=n-shirtsTopple-1;
		 return shirtsTopple;
		 }
	
	 }
	 
		
	
	public static void main(String args[])throws EmptyStackException{
		// Implement FabricBreakup puzzle using Stack interface
		FabricBreakup breakupParty= new FabricBreakup();
		String inputLine;
		String inputFile=args[0];
		String inputs;
		BufferedReader br; 
		try
		{File file=new File(inputFile);
		br= new BufferedReader(new FileReader(file));
		inputs= br.readLine();
		
		
		
		
		for(int i=0; i<Integer.parseInt(inputs); i++)
		{inputLine= br.readLine();
		String[] arr=inputLine.split(" ");
		if(arr[1].equals("1"))
		{
			breakupParty.heapTopile(Integer.parseInt(arr[2]));
		}
		else
		{
			System.out.println(arr[0].toString() + " " + breakupParty.party());
		}
		
		}
		
		}
		catch(IOException e)
		{
			System.out.println("File not found");
		}
		
		
			
		}
		
		
		
		
	
}
