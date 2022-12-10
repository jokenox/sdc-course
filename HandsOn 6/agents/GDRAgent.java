package agents;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

import algorithms.GradientDescent;

public class GDRAgent extends Agent {

  private int i = 0;

  private double predictX = 0;

  GradientDescent gd = new GradientDescent();

  protected void setup() {
    Object[] args = getArguments();

    gd.setMaxIterations(Integer.parseInt((String) args[1]));
    gd.setLearningRate(Double.parseDouble((String) args[2]));

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
      gd.descend(i);

      i++;
    }

    @Override
    public boolean done() {
      return gd.hasFinished(i);
    }

    @Override
    public int onEnd() {
      return super.onEnd();
    }
  }

  private class Predict extends Behaviour {

    @Override
    public void action() {
      if (gd.hasFinished(i)) {
        gd.printBetas();
        gd.printEquation();

        myAgent.doDelete();
      }
    }

    @Override
    public boolean done() {
      return gd.hasFinished(i);
    }

    @Override
    public int onEnd() {
      System.out.print(getLocalName() + ": ");

      gd.predict(predictX);

      System.out.println("\n" + getLocalName() + ": Finished");

      return super.onEnd();
    }
  }
}