
public class AssertNodeDecorator extends NodeDecorator {
	NodeDecorator cond;

	public AssertNodeDecorator(SimpleNode decoratedNode)
	{
		super(decoratedNode);
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
	public String getType(){
		return decoratedNode.getType();
	}
	@Override
	public NodeDecorator accept(Visitor visitor) {
		return visitor.visit(this);
	}
}
