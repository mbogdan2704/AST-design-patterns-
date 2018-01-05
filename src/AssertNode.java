
public  class AssertNode implements SimpleNode {
	String type;
	AssertNode(String type)
	{
		this.type = type;
	}
	@Override
	public String getType()
	{
		return this.type;
	}


}

