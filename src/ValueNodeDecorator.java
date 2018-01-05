
public class ValueNodeDecorator extends NodeDecorator {
int info;
	public ValueNodeDecorator(SimpleNode decoratedNode)
	{
		super(decoratedNode);
	}
	
	@Override
	public String getType(){
		return decoratedNode.getType();
	}
	@Override
	public int getValue() {
		return this.info;
	}
	
	@Override
	public void setValue(int val) {
		this.info = val;
	}
	@Override
	public NodeDecorator accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	
	
	
		
}
