
public  class AssignNode implements SimpleNode {
	String type;
	AssignNode(String type)
	{
		this.type = type;
	}
	@Override
	public String getType()
	{
		return this.type;
	}

}

