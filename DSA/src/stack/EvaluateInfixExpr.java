package stack;

public class EvaluateInfixExpr {

	public static void main(String[] args) {
		EvaluateInfixExpr obj = new EvaluateInfixExpr();
		String expression = "(5+3)*(8/2)";
		System.out.println("Result of expression "+expression+" = "+obj.evaluateInOnePass(expression));

	}
	
	public int evaluateInOnePass(String expression) {
		Stack<Integer> operand = new Stack<Integer>();
		Stack<Character> operator = new Stack<Character>();
		
		for(int i=0;i<expression.length();i++) {
			char c = expression.charAt(i);
			if(isOpeningBracket(c)) {
				operator.push(c);
			}else if(isOperand(c)) {
				operand.push(Integer.parseInt(String.valueOf(c)));
			}else if(isOperator(c)) {
				if(!operator.isEmpty() && isOpeningBracket(operator.peek())) {
					operator.push(c);
				}else if(!operator.isEmpty() && getOpPrec(operator.peek()) < getOpPrec(c)) {
					char nextChar = expression.charAt(++i);
					int op2 = Integer.parseInt(String.valueOf(nextChar));
					if(operand.isEmpty()) {
						throw new IllegalStateException("Operand stack empty for a binary operation");
					}
					int result = performOperation(operand.pop(),op2,c);
					operand.push(result);
				}else if(!operator.isEmpty() && getOpPrec(operator.peek()) >= getOpPrec(c)) {
					if(operand.isEmpty()) {
						throw new IllegalStateException("Operand stack empty for a binary operation");
					}
					int op1 = operand.pop();
					int op2 = operand.pop();
					int result = performOperation(op2,op1,operator.pop());
					operand.push(result);
					operator.push(c);
				}else if(operator.isEmpty()) {
					operator.push(c);
				}
			}else if(isClosingBracket(c)) {
				if(operator.isEmpty()) {
					throw new IllegalStateException("Unmatched Paranthesis");
				}
				char temp = operator.pop();
				boolean matchingOpeningparanthesis = temp == '(';
				while(temp != '(' && !operator.isEmpty()) {					 
					int op1 = operand.pop();
					int op2 = operand.pop();
					int result = performOperation(op2,op1,temp);
					operand.push(result);
					temp = operator.pop();
					matchingOpeningparanthesis = temp == '(';
				}
				if(!matchingOpeningparanthesis) {
					throw new IllegalStateException("Unmatched Paranthesis");
				}
			}
		}
		
		while(!operator.isEmpty()) {
			int op1 = operand.pop();
			int op2 = operand.pop();
			char operation = operator.pop();
			operand.push(performOperation(op2, op1, operation));
		}
		
		return operand.pop();
	}
	
	public int performOperation(int op1, int op2, char operator) {
		switch(operator) {
		case '+' : return op1+op2;
		case '-' : return op1-op2;
		case '*' : return op1*op2;
		case '/' : return op1/op2;
		default: throw new IllegalArgumentException("Wrong operator "+operator);
		}
	}
	
	private boolean isOpeningBracket(char c) {
		// TODO Auto-generated method stub
		return c == '(';
	}
	
	private boolean isClosingBracket(char c) {
		return c == ')';
	}

	private boolean isOperator(char c) {
		// TODO Auto-generated method stub
		return c == '+' || c == '-' || c == '*' || c == '/';
	}

	private boolean isOperand(char c) {
		// TODO Auto-generated method stub
		return c >= 'a' && c<='z' || c >='A' && c<='Z' || c>= '0' && c<='9';
	}

	public int getOpPrec(char op) {
		switch(op) {
		case '(':
		case ')': return 15;
		case '*':
		case '/': return 14;
		case '+':
		case '-': return 13;
		default : return 10; //ignoring others to reduce complexity
		}
	}
}
