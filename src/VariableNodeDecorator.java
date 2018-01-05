
public class VariableNodeDecorator extends NodeDecorator{
	String character;

	public VariableNodeDecorator(SimpleNode decoratedNode)
	{
		super(decoratedNode);
	}
	@Override
	public String getType(){
		return decoratedNode.getType();
	}
	
	@Override
	public void setCharacter(String character)
	{
		this.character = character;
	}
	
	@Override
	public String getCharacter()
	{
		return this.character;
	}
	
	@Override
	public NodeDecorator accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
