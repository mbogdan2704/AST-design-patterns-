
public  class ReturnNode implements SimpleNode{
	String type;
	ReturnNode(String type)
	{
		this.type = type;
	}
	@Override
	public String getType()
	{
		return this.type;
	}
	
}
