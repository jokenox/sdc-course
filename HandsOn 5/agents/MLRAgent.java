package agents;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

import algorithms.MLR;

public class MLRAgent extends Agent {

  private int i = 0;

  private double predictX1 = 0;
  private double predictX2 = 0;

  MLR mlr = new MLR();

  protected void setup() {
    Object[] args = getArguments();

    addBehaviour(new Train());
    addBehaviour(new Predict());

    predictX1 = Float.parseFloat((String) args[0]);
    predictX2 = Float.parseFloat((String) args[1]);

    System.out.println(getLocalName() + ": x1 = " + predictX1);
    System.out.println(getLocalName() + ": x2 = " + predictX2);

    System.out.println(getLocalName() + ": Setup ready");
    System.out.println(getLocalName() + ": Training...");
  }

  private class Train extends Behaviour {
    
    @Override
    public void action() {
      mlr.sum(i);

      i++;
    }

    @Override
    public boolean done() {
      return i == mlr.getDataLength();
    }

    @Override
    public int onEnd() {
      mlr.calculateBetas();

      return super.onEnd();
    }
  }

  private class Predict extends Behaviour {

    @Override
    public void action() {
      if (i == mlr.getDataLength()) {
        mlr.printBetas();
        mlr.printEquation();

        myAgent.doDelete();
      }
    }

    @Override
    public boolean done() {
      return i == mlr.getDataLength();
    }

    @Override
    public int onEnd() {
      System.out.print(getLocalName() + ": ");
      
      mlr.predict(predictX1, predictX2);

      System.out.println("\n" + getLocalName() + ": Finished");

      return super.onEnd();
    }
  }
}