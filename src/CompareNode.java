
public  class CompareNode implements SimpleNode {
	String type;
	CompareNode(String type)
	{
		this.type = type;
	}
	@Override
	public String getType()
	{
		return this.type;
	}
	

}
