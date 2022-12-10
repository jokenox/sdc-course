package agents;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

import algorithms.SLR;

public class SLRAgent extends Agent {
  
  private int i = 0;

  private double predictX = 0;

  SLR slr = new SLR();

  protected void setup() {
    Object[] args = getArguments();

    addBehaviour(new Train());
    addBehaviour(new Predict());

    predictX = Float.parseFloat((String) args[0]);
    System.out.println(getLocalName() + ": x = " + predictX);

    System.out.println(getLocalName() + ": Setup ready");
    System.out.println(getLocalName() + ": Training...");
  }

  private class Train extends Behaviour {
    
    @Override
    public void action() {
      slr.sum(i);

      i++;
    }

    @Override
    public boolean done() {
      return i == slr.getDataLength();
    }

    @Override
    public int onEnd() {
      slr.calculateBetas();

      return super.onEnd();
    }
  }

  private class Predict extends Behaviour {

    @Override
    public void action() {
      if (i == slr.getDataLength()) {
        slr.printBetas();
        slr.printEquation();

        myAgent.doDelete();
      }
    }

    @Override
    public boolean done() {
      return i == slr.getDataLength();
    }

    @Override
    public int onEnd() {
      System.out.print(getLocalName() + ": ");
      slr.predict(predictX);

      System.out.println("\n" + getLocalName() + ": Finished");

      return super.onEnd();
    }
  }
}