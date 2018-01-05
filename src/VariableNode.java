
public class VariableNode implements SimpleNode {
	String type;
	VariableNode(String type)
	{
		this.type = type;
	}
	@Override
	public String getType()
	{
		return this.type;
	}
	
}
