
public class IfNodeDecorator extends NodeDecorator {

	NodeDecorator st;
	NodeDecorator dr;
	NodeDecorator cond;
	public IfNodeDecorator(SimpleNode decoratedNode)
	{
		super(decoratedNode);
	}
	@Override
	public String getType(){
		return decoratedNode.getType();
	}

	@Override
	public void setSt(NodeDecorator nod)
	{
		this.st = nod;
	}

	@Override
	public void setDr(NodeDecorator nod)
	{
		this.dr = nod;
	}
	@Override
	public NodeDecorator getSt()
	{
		return this.st;
	}
	
	@Override
	public NodeDecorator getDr()
	{
		return this.dr;
	}
	@Override
	public void setCond(NodeDecorator nod)
	{
		this.cond = nod;
	}
	@Override
	public NodeDecorator getCond()
	{
		return this.cond;
	}	
	@Override
	public NodeDecorator accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
}
