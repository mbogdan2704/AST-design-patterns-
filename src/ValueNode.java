
public  class ValueNode implements SimpleNode{
	String type;
	ValueNode(String type)
	{
		
		this.type = type;

	}
	@Override
	public String getType()
	{
		return this.type;
	}
	
}
