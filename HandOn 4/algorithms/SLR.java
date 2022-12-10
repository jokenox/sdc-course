package algorithms;

public class SLR {
  double [][] dataSet = {{471784.1, 192261.83}, {443898.53, 191792.06}, {407934.54, 191050.39}, {383199.62, 182901.99}, {366168.42, 166187.94}, {362861.36, 156991.12}, {127716.82, 156122.51}, {323876.68, 155752.6}, {311613.29, 152211.77}, {304981.62, 149759.96}, {229160.95, 146121.95}, {249744.55, 144259.4}, {249839.44, 141585.52}, {252664.93, 134307.35}, {256512.92, 132602.65}, {261776.23, 129917.04}, {264346.06, 126992.93}, {282574.31, 125370.37}, {294919.57, 124266.9}, {298664.47, 118474.03}, {299737.29, 111313.02}, {303319.26, 110352.25}, {304768.73, 108733.99}, {140574.81, 108552.04}, {137962.62, 107404.34}, {134050.07, 105733.54}, {353183.81, 105008.31}, {118148.2, 103282.38}, {107138.38, 101004.64}, {91131.24, 99937.59}, {88218.23, 97483.56}, {46085.25, 97427.84}, {214634.81, 96778.92}, {210797.67, 96712.8}, {205517.64, 96479.51}, {201126.82, 90708.19}, {197029.42, 89949.14}, {185265.1, 81229.06}, {174999.3, 81005.76}, {172795.67, 78239.91}, {164470.71, 77798.83}, {148001.11, 71498.49}, {35534.17, 69758.98}, {28334.72, 65200.33}, {1903.93, 64926.08}, {297114.46, 49490.75}, {45173.06, 14681.4}};
  
  private int n = dataSet.length;

  private double sigmaX = 0;
  private double sigmaY = 0;
  private double sigmaXY = 0;
  private double sigmaXX = 0;

  private double systemDelta = 0;
  private double beta0Delta = 0;
  private double beta1Delta = 0;

  private double beta1 = 0;
  private double beta0 = 0;
    
  public SLR(){
      beta0 = 0;
      beta1 = 0;
  }

  public void sum(int i) {
    sigmaX += dataSet[i][0];
    sigmaXX += dataSet[i][0] * dataSet[i][0];
    sigmaXY += dataSet[i][0] * dataSet[i][1];
    sigmaY += dataSet[i][1];
  }

  public void calculateBetas() {
    systemDelta = n * sigmaXX - sigmaX * sigmaX;
    beta0Delta = sigmaY * sigmaXX - sigmaXY * sigmaX;
    beta1Delta = n * sigmaXY - sigmaX * sigmaY;

    beta1 = beta1Delta / systemDelta;
    beta0 = beta0Delta / systemDelta;
  }

  public void predict(double x) {
    double prediction = beta0 + (beta1 * x);

    System.out.print(String.format("Prediction for y: " + prediction));
  }

  public void printBetas() {
    System.out.print("Beta0 = " + beta0 + " | Beta1 = " + beta1);
  }

  public void printEquation() {
    System.out.println("\n" + "SLR Ecuation: y = " + beta0 + " + " + beta1 + " * x");
  }

  public int getDataLength() {
      return n;
  }
    
}