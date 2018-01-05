
public  class ForNode implements SimpleNode{
	String type;
	ForNode(String type)
	{
		this.type = type;
	}
	@Override
	public String getType()
	{
		return this.type;
	}

}
