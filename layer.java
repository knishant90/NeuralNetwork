import java.util.Random;
//import java.math.*;

public class layer {
layer previous,next;
int NumberOfNodes=0;	
double weights[][] = new double[1000][1000];
double threshold[] = new double[1000];
double outputs[] = new double[1000];
double inputs[] = new double[1000];
double expectedoutput,error = 0.00;
double errorgradient[]= new double[1000];
double updatedweight[][] = new double[10][10];
double deltaweight[][] = new double[10][10];

layer(int x)
{
	NumberOfNodes = x;
	threshold = new double[NumberOfNodes];
	outputs = new double[NumberOfNodes];
}

void setnext(layer a)
{
	next = a;
	initializeweights(weights);
	initializethreshold();
}

void setprevious(layer a)
{
	previous = a;
	weights = new double[previous.getnodes()][NumberOfNodes];
	initializeweights(weights);
	initializethreshold();
}

void setexpectedoutput(double x)
{
	expectedoutput = x;
}

void forwardpropagate()
{
	if(previous == null)
	{
		next.setinputs(inputs);
		next.forwardpropagate();
	}
	else if(next == null)
	{
		calculateoutput();
		backwardpropagate();
	}
	else
	{
		calculateoutput();
		next.setinputs(outputs);
		next.forwardpropagate();
	}
}

void backwardpropagate()
{
	if(previous == null)
	{
		
		next.updatevalues();
	}
	else if(next == null)
	{
		error = expectedoutput - outputs[0]; 
		errorgradient[0] = outputs[0]*(1 - outputs[0]) * error;
		for(int i=0;i<previous.NumberOfNodes;i++)
		{
			deltaweight[i][0] = 0.7 * inputs[i] * errorgradient[0];
		}
		for(int i=0;i<previous.NumberOfNodes;i++)
		{
			updatedweight[i][0] = weights[i][0] + deltaweight[i][0];
		}
		double deltathreshold = 0.7 * (-1) * errorgradient[0];
		threshold[0] = threshold[0] + deltathreshold;
		previous.backwardpropagate();
	}
	else
	{
		double deltathresholds[] = new double[NumberOfNodes];
		for(int i=0;i<NumberOfNodes;i++)
		{
			errorgradient[i] = outputs[i]* (1-outputs[i]) * (next.errorgradient[0] * next.weights[i][0]);
		}
		for(int i=0;i<previous.NumberOfNodes;i++)
		{
			for(int j=0;j<NumberOfNodes;j++)
			{
				deltaweight[i][j] = 0.7 * inputs[i] * errorgradient[j];
				updatedweight[i][j] = weights[i][j] + deltaweight[i][j];
			}
			deltathresholds[i] = 0.7 * (-1) * (errorgradient[i]);
			threshold[i] = deltathresholds[i] + threshold[i]; 
		}
		previous.backwardpropagate();
	}
}

void updatevalues()
{
	if(next == null)
	{
		calculateoutput();
		System.out.println(outputs[0]);
	}
	else
	{
		
		weights = updatedweight;
		next.updatevalues();
	}
}

void setinputs(double x[])
{
	inputs = x;
}

int getnodes()
{
	return NumberOfNodes;
}

void calculateoutput()
{
	if(previous == null)
	{
	outputs = inputs;	
	}
	else{
	for( int i=0;i<NumberOfNodes;i++)
	{
		double midsum = 0;
		for( int j=0;j<previous.getnodes();j++)
		{
			midsum+= weights[j][i] * inputs[j];
		}
		outputs[i] = 1/(1 + Math.exp(-(midsum - threshold[i])));
	}
	}
}

void initializethreshold()
{
	Random random = new Random();
	double midrandom;
	if(previous == null)
	{
		
	}
	else if(next == null)
	{
		midrandom = (random.nextDouble() * 2) - 1;
		threshold[0] = (midrandom * 2.4)/previous.getnodes();
	}
	else{
	for(int i=0;i<NumberOfNodes;i++)
	{
		midrandom = (random.nextDouble() * 2) - 1;
		threshold[i] = (midrandom * 2.4)/previous.getnodes();
	}
	}
}

void initializeweights(double x[][])
{
	Random random = new Random();
	double midrandom;
	if(next == null)
	{
		for(int i = 0; i < previous.NumberOfNodes ;i++)
		{
			midrandom = (random.nextDouble()*2) - 1;
			x[i][0] = ((2.4 * midrandom) / previous.getnodes());
		}
	}
	else if(previous == null)
	{
		
	}
	else{
	for(int i = 0; i<previous.getnodes(); i++)
	{
		for(int j=0; j<NumberOfNodes ;j++)
		{
			midrandom = (random.nextDouble()*2) - 1;
			x[i][j] = ((2.4 * midrandom) / previous.getnodes());
		}
	}}
}

}
