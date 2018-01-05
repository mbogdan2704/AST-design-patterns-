
public abstract class NodeDecorator implements SimpleNode {
protected SimpleNode decoratedNode;
	public NodeDecorator(SimpleNode decoratedNode)
	{
		this.decoratedNode = decoratedNode;
	}
	
	public String getType() {
		return decoratedNode.getType();
	}
	
	public int getValue(){ return 0;}
	public void setValue(int val){ }
	public void setCharacter(String a) {}
	public String getCharacter() { return null;}
	public void setReturnCharacter(NodeDecorator a) {}
	public NodeDecorator getReturnCharacter() { return null;}
	public void setSt(NodeDecorator nod) { }
	public void setDr(NodeDecorator nod) { }
	public NodeDecorator getSt(){ return null; }
	public NodeDecorator getDr(){ return null; }
	public void setCond(NodeDecorator nod){ }
	public NodeDecorator getCond() {return null;}
	public void setInit(NodeDecorator nod) {}
	public NodeDecorator getInit() { return null;}
	public void setCorp(NodeDecorator nod){ }
	public NodeDecorator getCorp() { return null; }
	public void setInc(NodeDecorator nod){ }
	public NodeDecorator getInc() { return null; }
	public NodeDecorator accept(Visitor visitor) {return null;}


}
