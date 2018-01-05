import java.util.HashMap;

public class EvaluateVisitor implements Visitor{
	static HashMap<String, Integer> map = new HashMap<String, Integer>();           
	static int checkFailed = 0;
	static int missingReturn = 1;
	static int assertion = 0;
	@Override
	public int getCheckFailed()
	{
		return checkFailed;
	}
	@Override
	public int getMissingReturn()
	{
		return missingReturn;
	}
	@Override
	public int getAssertion()
	{
		return assertion;
	}
	// FRUNZE. Pentru frunze trebuie sa returnam exact nodul
	@Override
	public NodeDecorator visit(VariableNodeDecorator node)
	{
		return node;
	}
	@Override
	public NodeDecorator visit(ValueNodeDecorator node)
	{
		return node;
	}
//NODURI. Pentru noduri trebuie sa realizam operatiile specifice fiecarui nod
	@Override
	public NodeDecorator visit(SemicolonNodeDecorator node) //Pentru ";" trebuie sa parcurgem ce e in stanga si sa returnam informatia care este returnata de subarborele stang
	{

		
		NodeDecorator left = node.getSt();
		left.accept(this);
		NodeDecorator right = node.getDr();
		NodeDecorator info = right.accept(this);
		return info;

	}
	@Override
	public NodeDecorator visit(EqualNodeDecorator node) //Pentru "=" trebuie sa adaugam un nou numar in HashMap
	{
		NodeDecorator left = node.getSt();
		NodeDecorator variabila = left.accept(this);
		NodeDecorator right = node.getDr();
		NodeDecorator val = right.accept(this);
		map.put(variabila.getCharacter(), val.getValue());
		return null;
	}
	public NodeDecorator visit(PlusNodeDecorator node) //Pentru "+" trebuie sa facem adunarea, obtinand prima data ce e in stanga si apoi ce e in dreapta
													   //si apoi construind un nod pe care il returnez care contine in el ca informatia suma dintre cei 2 subarbori
	{
		
		NodeDecorator left = node.getSt();
		NodeDecorator leftNod = left.accept(this);
		int info1 = 0, info2 = 0;
		if(leftNod.getType().equals("Variable"))
		{
			if(map.containsKey(leftNod.getCharacter()))
				info1 = map.get(leftNod.getCharacter());
			else
			{
				checkFailed = 1;
				info1 = 0;
			}
		}
		if(leftNod.getType().equals("Value"))
		{
			info1 = leftNod.getValue();
		}
		NodeDecorator right = node.getDr();
		NodeDecorator rightNod = right.accept(this);
		if(rightNod.getType().equals("Variable"))
		{
			if(map.containsKey(rightNod.getCharacter()))
				info2 = map.get(rightNod.getCharacter());
			else
			{
				checkFailed = 1;
				info2 = 0;
			}
		}
		if(rightNod.getType().equals("Value"))
		{
			 info2 = rightNod.getValue();
		}
		SimpleNode simpleNode = new ValueNode("Value");
		NodeDecorator newNod = new ValueNodeDecorator(simpleNode);
		newNod.setValue(info1 + info2);
		return newNod;
	}
	public NodeDecorator visit(ReturnNodeDecorator node) //Pentru return trebuie sa returnez informatia care se afla in fiu
	{
	
		missingReturn = 0;
		NodeDecorator returnValue = node.getReturnCharacter();
		NodeDecorator ret = returnValue.accept(this);
		int info = 0;
		if(ret.getType().equals("Variable"))
		{
		
			if(map.containsKey(ret.getCharacter()))
			{
				info = map.get(ret.getCharacter());
			}
			else
			{
				checkFailed = 1;
				info = 0;
			}

		}
		else //Inseamna ca e Valoare
		{
			info = ret.getValue();
		}
		SimpleNode simpleNode = new ValueNode("Value");
		NodeDecorator newNod = new ValueNodeDecorator(simpleNode);
		newNod.setValue(info);
		return newNod;
		
	}
	public NodeDecorator visit(ForNodeDecorator node) //Pentru for trebuie sa parcurgem nodul "corp" pana cand "cond" nu mai e adevarata
	{
		NodeDecorator init = node.getInit(); //initializam
		init.accept(this);
		NodeDecorator cond;
		while(true)
		{
			cond = node.getCond();
			NodeDecorator validCond = cond.accept(this);
			if(validCond == null) //Verificam conditia de oprire
			{
				break ; //Inseamna ca nu mai e adevarata conditia, ne oprim
			}
			else
			{
				//Inseamna ca e adevarata Conditia
				NodeDecorator corp = node.getCorp();
				corp.accept(this); //Facem ce trebuie
			}
			NodeDecorator inc = node.getInc();
			inc.accept(this); //Facem incrementarea
		}
		return null;
	}
	public NodeDecorator visit(IfNodeDecorator node) //Pentru if trebuie sa parcurgem subarborele stang doar daca "cond" e adevarata, iar altfel
													//trebuie sa parcurgem subarborele drept
	{
		NodeDecorator cond = node.getCond();
		NodeDecorator validCond = cond.accept(this);
		if(validCond == null) //Inseamna ca e false
		{
			NodeDecorator right = node.getDr(); //Ma duc in dreapta
			right.accept(this);
		}
		else//Inseamna ca e true
		{
			
			NodeDecorator left = node.getSt(); // Ma duc pe TRUE
			left.accept(this);
		}
	return null;
	}
	public NodeDecorator visit(AssertNodeDecorator node) //Pentru assert verificam conditia, iar daca nu e adevarata variabila assertion se face 1
	{
		NodeDecorator cond = node.getCond();
		NodeDecorator validCond = cond.accept(this);
		if(validCond == null)
		{
			assertion = 1;
		}
	
			return null;
	}
	public NodeDecorator visit(AssignNodeDecorator node) //Assign returneaza un nod != NULL daca a == b, si un nod NULL daca a != b
	{
		NodeDecorator left = node.getSt();
		NodeDecorator leftNod = left.accept(this);
		int info1 = 0, info2 = 0;
		if(leftNod.getType().equals("Variable"))
		{
			if(map.containsKey(leftNod.getCharacter()))
				info1 = map.get(leftNod.getCharacter());
			else
			{
				checkFailed = 1;
				info1 = 0;
			}
		}
		if(leftNod.getType().equals("Value"))
		{
			info1 = leftNod.getValue();
		}
		NodeDecorator right = node.getDr();
		NodeDecorator rightNod = right.accept(this);
		if(rightNod.getType().equals("Variable"))
		{
			if(map.containsKey(rightNod.getCharacter()))
				info2 = map.get(rightNod.getCharacter());
			else
			{
				checkFailed = 1;
				info2 = 0;
			}
		}
		if(rightNod.getType().equals("Value"))
		{
			 info2 = rightNod.getValue();
		}
		
		if(info1 != info2)
		{
			return null; // Returnez null in caz de false
		}
		else
		{
			return node; //Returnez un nod != null in caz de true
		}
	}
	public NodeDecorator visit(CompareNodeDecorator node) //Compare returneaza un nod != NULL daca a < b, si un nod == NULL daca a >= b
	{
	
		NodeDecorator left = node.getSt();
		NodeDecorator leftNod = left.accept(this);
		int info1 = 0, info2 = 0;
		if(leftNod.getType().equals("Variable"))
		{
			if(map.containsKey(leftNod.getCharacter()))
				info1 = map.get(leftNod.getCharacter());
			else
			{
				checkFailed = 1;
				info1 = 0;
			}
		}
		if(leftNod.getType().equals("Value"))
		{
			info1 = leftNod.getValue();
		}
		NodeDecorator right = node.getDr();
		NodeDecorator rightNod = right.accept(this);
		if(rightNod.getType().equals("Variable"))
		{
			if(map.containsKey(rightNod.getCharacter()))
				info2 = map.get(rightNod.getCharacter());
			else
			{
				checkFailed = 1;
				info2 = 0;
			}
		}
		if(rightNod.getType().equals("Value"))
		{
			 info2 = rightNod.getValue();
		}
		
		if(info1 >= info2)
		{
			return null; // Returnez null in caz de false
		}
		else
		{
			return node; //Returnez un nod != null in caz de true
		}
	}
	public NodeDecorator visit(MultiplyNodeDecorator node)//Multiply functioneaza ca +, doar ca inmulteste
	{
		NodeDecorator left = node.getSt();
		NodeDecorator leftNod = left.accept(this);
		int info1 = 0, info2 = 0;
		if(leftNod.getType().equals("Variable"))
		{
			if(map.containsKey(leftNod.getCharacter()))
				info1 = map.get(leftNod.getCharacter());
			else
			{
				checkFailed = 1;
				info1 = 0;
			}
		}
		if(leftNod.getType().equals("Value"))
		{
			info1 = leftNod.getValue();
		}
		NodeDecorator right = node.getDr();
		NodeDecorator rightNod = right.accept(this);
		if(rightNod.getType().equals("Variable"))
		{
			if(map.containsKey(rightNod.getCharacter()))
				info2 = map.get(rightNod.getCharacter());
			else
			{
				checkFailed = 1;
				info2 = 0;
			}
		}
		if(rightNod.getType().equals("Value"))
		{
			 info2 = rightNod.getValue();
		}
		SimpleNode simpleNode = new ValueNode("Value");
		NodeDecorator newNod = new ValueNodeDecorator(simpleNode);
		newNod.setValue(info1 * info2);
		return newNod;
	}
}






