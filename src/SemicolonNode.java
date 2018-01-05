
public  class SemicolonNode implements SimpleNode{
	String type;
	SemicolonNode(String type)
	{
		this.type = type;
	}
	@Override
	public String getType()
	{
		return this.type;
	}
	
}
