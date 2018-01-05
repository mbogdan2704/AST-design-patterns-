
public class ReturnNodeDecorator extends NodeDecorator{
	NodeDecorator character;

	public ReturnNodeDecorator(SimpleNode decoratedNode)
	{
		super(decoratedNode);
	}
	@Override
	public String getType(){
		return decoratedNode.getType();
	}
	
	@Override
	public void setReturnCharacter(NodeDecorator character)
	{
		this.character = character;
	}
	
	@Override
	public NodeDecorator getReturnCharacter()
	{
		return this.character;
	}
	@Override
	public NodeDecorator accept(Visitor visitor) {
		return visitor.visit(this);
	}
}
