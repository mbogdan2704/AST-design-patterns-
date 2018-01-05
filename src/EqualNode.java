
public  class EqualNode implements SimpleNode{
	String type;
	EqualNode(String type)
	{
		this.type = type;
	}
	@Override
	public String getType()
	{
		return this.type;
	}

}
