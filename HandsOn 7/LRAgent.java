package agents;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class LRAgent extends Agent {
  
  double [][] dataSet = {{1,1,1,0},{1,4,2,1},{1,2,4,1},{1,1,1,1}};

  int [] inputValues = {0, 0, 0};
  
  private int n = dataSet.length;
  private int i = 0;

  double [] weights = {0, 0, 0};
    
  double error;
  double learningRate;
  int iterations;
  int iteration;

  protected void setup() {
    Object[] args = getArguments();
        
    iterations = Integer.parseInt((String) args[2]);
    learningRate = Double.parseDouble((String) args[3]);

    addBehaviour(new Train());
    addBehaviour(new Predict());

    inputValues[0] = 1;
    inputValues[1] = Integer.parseInt((String) args[0]);
    inputValues[2] = Integer.parseInt((String) args[1]);

    System.out.println(getLocalName() + ": Input values = {" + inputValues[1] + ", " + inputValues[2] + "}");

    System.out.println(getLocalName() + ": Setup ready");
    System.out.println(getLocalName() + ": Training...");
  }

  private double sigmoid(double z){
    return 1 / (1 + Math.exp(-z));
  }

  private double[] calculateSigmoid(){
    double [] sigmoid = new double[this.n];
    double [] z = new double[this.n];

    for(int j = 0; j < this.n; j++){
      z[j] = 0;

      for(int k = 0; k < 3; k++) {
        z[j] += this.weights[k] * this.dataSet[j][k];
      }
    }

    for(int j = 0; j < this.n; j++) {
      sigmoid[j] = sigmoid(z[j]);
    }
    
    return sigmoid;
  }

  private void calculateError(double [] sigmoid){
    this.error = 0;

    for(int j = 0; j < this.n; j++) {
      this.error += this.dataSet[j][3] * Math.log10(sigmoid[j]) + (1.0 - this.dataSet[j][3]) * Math.log10(1.0 - sigmoid[j]);
    }

    this.error = - (this.error / this.n);
  }

  private class Train extends Behaviour {
    
    @Override
    public void action() {
      double [] sigmoid = calculateSigmoid();
      
      for(int j = 0; j < 3; j++) {
        for(int k = 0; k < sigmoid.length; k++) {
          weights[j] -= learningRate * (sigmoid[k] - dataSet[k][3]) * dataSet[k][j];
        }
      }

      calculateError(sigmoid);

      i++;
    }

    @Override
    public boolean done() {
      return i == iterations;
    }

    @Override
    public int onEnd() {
      return super.onEnd();
    }
  }

  private class Predict extends Behaviour {

    @Override
    public void action() {
      doDelete();
    }

    @Override
    public boolean done() {
      return i != iterations;
    }

    @Override
    public int onEnd() {
      double z = weights[0];
      
      for(int j = 1; j < 3; j++){
        z += inputValues[j] * weights[j];
      }
      
      double prediction = sigmoid(z);

      System.out.print(getLocalName() + ": ");
      System.out.print(String.format("Prediction: " + prediction));

      System.out.println("\n" + getLocalName() + ": Finished");

      return super.onEnd();
    }
  }
}