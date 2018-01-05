import java.util.Queue;



public class CreateTree {

	//Functia de construire a arborelui
	private static Queue<String> myQueue; // Coada mea in care am input-ul
	
	
	CreateTree(Queue<String> myQueue)
	{
		CreateTree.myQueue = myQueue;
	}
	public static boolean isVariable(String info)
	{
		for(int i=0; i< info.length(); i++)
		{
			if(info.charAt(i) < 'a' || info.charAt(i) > 'z')
				return false;
		}
	
		return true;
	}
	public static boolean isValue(String info)
	{
		for(int i=0; i< info.length(); i++)
		{
			if(info.charAt(i) < '0' || info.charAt(i) > '9')
				return false;
		}
		return true;
	}
	
	public static boolean isFor(String info)
	{
		if(info.charAt(0) == 'f' && info.charAt(1) == 'o' && info.charAt(2) == 'r')
			return true;
		return false;
	}
	public static boolean isAssert(String info)
	{
		if(info.equals("assert"))
			return true;
		return false;
	}
	public static boolean isIf(String info)
	{
		if(info.equals("if"))
			return true;
		return false;
	}
	public static  NodeDecorator createTree()
	{
		//myQueue.poll(); //Extrag "["
		String info;
		while(true)
		{
			info = myQueue.poll(); // Extrag ce urmeaza dupa [

			if(info.equals("[") || info.equals("]"))
				continue;
			else
				break;
		}

		if(info.equals(";")) //Construiesc un nod de tipul Semicolon
		{
			SimpleNode simpleNod = new SemicolonNode("Semicolon");
			NodeDecorator nod = new SemicolonNodeDecorator(simpleNod);
			nod.setSt(createTree());
			nod.setDr(createTree());

			return nod;
		}
		if(info.equals("return")) //Construiesc un nod de tipul return
		{
			SimpleNode simpleNod = new ReturnNode("Return");
			NodeDecorator nod = new ReturnNodeDecorator(simpleNod);
			nod.setReturnCharacter(createTree());
			return nod;
		}

		if(info.equals("=")) //Construiesc un nod de tipul Egal
		{
			SimpleNode simpleNod = new EqualNode("Equal");
			NodeDecorator nod = new EqualNodeDecorator(simpleNod);
			nod.setSt(createTree());
			nod.setDr(createTree());

			return nod;
		}
		if(info.equals("+")) //Construiesc un nod de tipul Plus
		{
			SimpleNode simpleNod = new PlusNode("Plus");
			NodeDecorator nod = new PlusNodeDecorator(simpleNod);
			nod.setSt(createTree());
			nod.setDr(createTree());
			return nod;
		}
		if(info.equals("<"))//Construiesc un nod de tipul Compare
		{
			SimpleNode simpleNod = new CompareNode("Compare");
			NodeDecorator nod = new CompareNodeDecorator(simpleNod);
			nod.setSt(createTree());
			nod.setDr(createTree());
			return nod;
		}
		if(info.equals("*"))//Construiesc un nod de tipul Multiply
		{
			SimpleNode simpleNod = new MultiplyNode("Multiply");
			NodeDecorator nod = new MultiplyNodeDecorator(simpleNod);
			nod.setSt(createTree());
			nod.setDr(createTree());
			return nod;
		}
		if(info.equals("=="))
		{
			SimpleNode simpleNod = new AssignNode("Assign");
			NodeDecorator nod = new AssignNodeDecorator(simpleNod);
			nod.setSt(createTree());
			nod.setDr(createTree());
			return nod;
		}
		if(isValue(info)) //Construiesc un nod de tipul numar
		{

			SimpleNode simpleNod = new ValueNode("Value");
			NodeDecorator nod = new ValueNodeDecorator(simpleNod);
			nod.setValue(Integer.parseInt(info));

			return nod;
		}
		if(isAssert(info))//Construiesc un nod de tipul Assert
		{
			SimpleNode simpleNod = new AssertNode("Assert");
			NodeDecorator nod = new AssertNodeDecorator(simpleNod);
			nod.setCond(createTree());
			return nod;
		}
		if(isIf(info)) //Construiesc un nod de tipul if
		{
			SimpleNode simpleNod = new IfNode("If");
			NodeDecorator nod = new IfNodeDecorator(simpleNod);
			nod.setCond(createTree());
			nod.setSt(createTree());
			nod.setDr(createTree());
			return nod;
		}
		if(isFor(info))
		{
			SimpleNode simpleNod = new ForNode("For");
			NodeDecorator nod = new ForNodeDecorator(simpleNod);
			nod.setInit(createTree()); //initializarea
			nod.setCond(createTree()); //Conditia de oprire
			nod.setInc(createTree()); //Incrementarea
			nod.setCorp(createTree()); //Corpul lui for
			return nod;
			
			
		}
		if(isVariable(info)) //Construiesc un nod de tipul Variabila
		{
			SimpleNode simpleNod = new VariableNode("Variable");
			NodeDecorator nod = new VariableNodeDecorator(simpleNod);
			nod.setCharacter(info);

			return nod;
		}
		return null; //Nu ar trebuie sa ajunga niciodata aici

	}

}
