
public  class PlusNode implements SimpleNode {
	String type;
	PlusNode(String type)
	{
		this.type = type;
	}
	@Override
	public String getType()
	{
		return this.type;
	}

}
