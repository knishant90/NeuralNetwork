

public class algebra {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double testoutput[] = {0,1,0,1};
		double testinput[][] = {{1,1},{1,0},{0,0},{0,1}};
		
		layer a = new layer(2);
		layer b = new layer(2);
		layer c = new layer(1);
		a.setnext(b);
		b.setprevious(a);
		b.setnext(c);
		c.setprevious(b);
		 
		for(int i=0;i<5000;i++)
		{
			for(int j=0;j<4;j++)
			{ 
				 a.setinputs(testinput[j]);
				 c.setexpectedoutput(testoutput[j]);
				 a.forwardpropagate();
				}
		}
	}

}
