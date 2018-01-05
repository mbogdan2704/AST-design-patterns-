
public class ForNodeDecorator extends NodeDecorator{
	NodeDecorator init, cond, inc, corp;
	
	public ForNodeDecorator(SimpleNode decoratedNode)
	{
		super(decoratedNode);
	}
	public void setInit(NodeDecorator nod)
	{
		this.init = nod;
	}
	@Override
	public NodeDecorator getInit()
	{
		return this.init;
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
	public void setInc(NodeDecorator nod)
	{
		this.inc = nod;
	}
	@Override
	public NodeDecorator getInc()
	{
		return this.inc;
	}
	@Override
	public void setCorp(NodeDecorator nod)
	{
		this.corp = nod;
	}
	@Override
	public NodeDecorator getCorp()
	{
		return this.corp;
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
