package stack;

public class InfixToPostfix {

	public static void main(String[] args) {
		InfixToPostfix obj = new InfixToPostfix();
		System.out.println(obj.convert("A+B*C-D"));

	}
	/*
	 * Run in Loop 1-4
	 * 1. if operand : write to output
	 * 2. If operator : 
	 *    a. if the top of the stack contains a higher precedence operator, then pop it and add to output
	 *    b. push operator to stack
	 * 3. if opening brace, push on stack
	 * 4. if closing brace, pop all operators till opening brace and write to output.
	 *    a. if matching opening brace is not found, throw exception
	 * 
	 * 5. if stack is not empty, pop all operators and write to output. 
	 */
	public String convert(String infix) {
		StringBuilder sb = new StringBuilder();
		Stack<Character> _stack = new Stack<Character>();
		for(char c : infix.toCharArray()) {
			if(isOperand(c)) {
				sb.append(c);
			}else if(isOperator(c)) {
				if(!_stack.isEmpty() && getOpPrec(c) <= getOpPrec(_stack.peek())) {
					sb.append(_stack.pop());
					_stack.push(c);
				}else {
					_stack.push(c);
				}
			}else if(isOpeningBracket(c)) {
				_stack.push(c);
			}else if(isClosingBracket(c)) {
				if(_stack.isEmpty()) {
					throw new IllegalStateException("Unmatched Paranthesis");
				}
				char temp = _stack.pop();
				boolean matchingOpeningparanthesis = temp == '(';
				while(temp != '(' && !_stack.isEmpty()) {					 
					sb.append(temp);
					temp = _stack.pop();
					matchingOpeningparanthesis = temp == '(';
				}
				if(!matchingOpeningparanthesis) {
					throw new IllegalStateException("Unmatched Paranthesis");
				}
			}
		}
		while(!_stack.isEmpty()) {
			sb.append(_stack.pop());
		}
		return sb.toString();
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
		return c >= 'a' && c<='z' || c >='A' && c<='Z';
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
