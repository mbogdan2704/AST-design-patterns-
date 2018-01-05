import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public  static Queue<String> createQueue(String name) throws IOException //Functie care construieste o coada cu inputul
	{
		BufferedReader input = null;
		Queue<String> myQueue = new LinkedList<String>();
		try{
			input = new BufferedReader(new FileReader(name));
			String line;
			while((line = input.readLine()) !=null) // Citesc toate liniile
			{
				
				for(int i=0; i< line.length(); i++)
				{
					
					if(line.charAt(i) != ' ' && line.charAt(i) != '\t' && line.charAt(i) != '\n')
					{

						if(line.charAt(i) == 'f' && line.charAt(i+1) == 'o' && line.charAt(i+2) == 'r') // Cazuri speciale
						{
							myQueue.add("for");
							i += 3;
							continue;
						}
						if(line.charAt(i) == 'i' && line.charAt(i+1) == 'f')
						{
							
							myQueue.add("if");
							i += 2;
							continue;
						}
						if(line.charAt(i) == 'a' && line.charAt(i+1) == 's' && line.charAt(i +2) == 's')
						{
						
							myQueue.add("assert");
							i += 6;
							continue;
						}

						if(line.charAt(i) == '=' && line.charAt(i+1) == '=')
						{
							myQueue.add("==");
							i += 2;
							continue;
						}
						if(line.charAt(i) >= '0' && line.charAt(i) <= '9')
						{
							
							String number = "";
							while(line.charAt(i) >= '0' && line.charAt(i) <= '9')
							{
								number += line.charAt(i);
								i++;
							}
							i--;
							myQueue.add(number);
							continue;
						}
						if(line.charAt(i) >= 'a' && line.charAt(i) <= 'z')
						{
							
							String number = "";
							while(line.charAt(i) >= 'a' && line.charAt(i) <= 'z')
							{
								number += line.charAt(i);
								i++;
							}
							i--;
							myQueue.add(number);
							continue;
						}
						
						if(line.charAt(i) == 'r' && line.charAt(i+1) == 'e' && line.charAt(i+2) == 't')
						{
							myQueue.add("return");
							i += 6;
							continue;
						}
						
						myQueue.add(line.charAt(i) + ""); //Daca nu intra pe niciunul din if-urile de mai sus inseamna ca e ";" sau "[]"

					}
				}
			}
		}
		catch(Error e)
		{
			throw e;
		}
		finally{
			input.close();
		}
		return myQueue;
	}



	public static void main(String args[])
	{
		
		Queue<String> myQueue = new LinkedList<String>();
		try{
			myQueue = createQueue(args[0]);
		}
		catch(IOException e)
		{
			System.out.println("Erorr reading from file");
		}
		CreateTree tree = new CreateTree(myQueue);
		NodeDecorator root = tree.createTree();
		Visitor vis = new EvaluateVisitor();
		NodeDecorator res = root.accept(vis);
		PrintWriter out = null;
		try{
			out = new PrintWriter(args[1]);
			if(vis.getCheckFailed() == 1)
			{
				out.print("Check failed");
			}
			else
			{
				if(vis.getMissingReturn() == 1)
				{
					out.print("Missing return");
				}
				else
				{
					if(vis.getAssertion() == 1)
					{
						out.print("Assert failed");
					}
					else
					{
						out.print(res.getValue());
					}
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			out.close();
		}
		
	}
}
