
public  class IfNode implements SimpleNode {
	String type;
	IfNode(String type)
	{
		this.type = type;
	}
	@Override
	public String getType()
	{
		return this.type;
	}

}
