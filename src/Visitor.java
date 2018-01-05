
interface Visitor {
	
	NodeDecorator visit(AssertNodeDecorator node);
	NodeDecorator visit(AssignNodeDecorator node);
	NodeDecorator visit(CompareNodeDecorator node);
	NodeDecorator visit(EqualNodeDecorator node);
	NodeDecorator visit(ForNodeDecorator node);
	NodeDecorator visit(IfNodeDecorator node);
	NodeDecorator visit(MultiplyNodeDecorator node);
	NodeDecorator visit(PlusNodeDecorator node);
	NodeDecorator visit(ReturnNodeDecorator node);
	NodeDecorator visit(SemicolonNodeDecorator node);
	NodeDecorator visit(ValueNodeDecorator node);
	NodeDecorator visit(VariableNodeDecorator node);
	int getCheckFailed();
	int getMissingReturn();
	int getAssertion();

}
