package algorithms;

public class MLR {
  double [][] dataSet = {{136897.8, 471784.1, 192261.83}, {151377.59, 443898.53, 191792.06}, {101145.55, 407934.54, 191050.39}, {118671.85, 383199.62, 182901.99}, {91391.77, 366168.42, 166187.94}, {99814.71, 362861.36, 156991.12}, {147198.87, 127716.82, 156122.51}, {145530.06, 323876.68, 155752.6}, {148718.95, 311613.29, 152211.77}, {108679.17, 304981.62, 149759.96}, {110594.11, 229160.95, 146121.95}, {91790.61, 249744.55, 144259.4}, {127320.38, 249839.44, 141585.52}, {135495.07, 252664.93, 134307.35}, {156547.42, 256512.92, 132602.65}, {122616.84, 261776.23, 129917.04}, {121597.55, 264346.06, 126992.93}, {145077.58, 282574.31, 125370.37}, {114175.79, 294919.57, 124266.9}, {113867.3, 298664.47, 118474.03}, {153773.43, 299737.29, 111313.02}, {122782.75, 303319.26, 110352.25}, {105751.03, 304768.73, 108733.99}, {99281.34, 140574.81, 108552.04}, {139553.16, 137962.62, 107404.34}, {144135.98, 134050.07, 105733.54}, {127864.55, 353183.81, 105008.31}, {182645.56, 118148.2, 103282.38}, {153032.06, 107138.38, 101004.64}, {115641.28, 91131.24, 99937.59}, {152701.92, 88218.23, 97483.56}, {129219.61, 46085.25, 97427.84}, {103057.49, 214634.81, 96778.92}, {157693.92, 210797.67, 96712.8}, {85047.44, 205517.64, 96479.51}, {127056.21, 201126.82, 90708.19}, {51283.14, 197029.42, 89949.14}, {65947.93, 185265.1, 81229.06}, {82982.09, 174999.3, 81005.76}, {118546.05, 172795.67, 78239.91}, {84710.77, 164470.71, 77798.83}, {96189.63, 148001.11, 71498.49}, {127382.3, 35534.17, 69758.98}, {154806.14, 28334.72, 65200.33}, {124153.04, 1903.93, 64926.08}, {115816.21, 297114.46, 49490.75}, {116983.8, 45173.06, 14681.4}};
  
  private int n = dataSet.length;

  final int x1 = 0;
  final int x2 = 1;
  final int y = 2;

  private double sumX1 = 0;
  private double sumX2 = 0;
  private double sumX1X2 = 0;
  private double sumX1X1 = 0;
  private double sumX2X2 = 0;
  private double sumY = 0;
  private double sumYX1 = 0;
  private double sumYX2 = 0;

  private double systemDelta = 0;
  private double beta0Delta = 0;
  private double beta1Delta = 0;
  private double beta2Delta = 0;

  private double beta0 = 0;
  private double beta1 = 0;
  private double beta2 = 0;
    
  public MLR(){
      beta0 = 0;
      beta1 = 0;
      beta2 = 0;
  }

  public void sum(int i) {
    sumX1 += dataSet[i][x1];
    sumX2 += dataSet[i][x2];
    sumX1X2 += dataSet[i][x1] * dataSet[i][x2];
    sumX1X1 += dataSet[i][x1] * dataSet[i][x1];
    sumX2X2 += dataSet[i][x2] * dataSet[i][x2];
    sumY += dataSet[i][y];
    sumYX1 += dataSet[i][y] * dataSet[i][x1];
    sumYX2 += dataSet[i][y] * dataSet[i][x2];
  }

  public void calculateBetas() {
    systemDelta = n * sumX1X1 * sumX2X2 - n * sumX1X2 * sumX1X2 - sumX1 * sumX1 * sumX2X2 + 2 * sumX1 * sumX2 * sumX1X2 - sumX1X1 * sumX2 * sumX2;
    beta0Delta = sumY * sumX1X1 * sumX2X2 - sumY * sumX1X2 * sumX1X2 - sumX1 * sumYX1 * sumX2X2 + sumX1 * sumX1X2 * sumYX2 + sumX2 * sumYX1 * sumX1X2 - sumX2 * sumX1X1 * sumYX2;
    beta1Delta = n * sumYX1 * sumX2X2 - n * sumX1X2 * sumYX2 - sumY * sumX1 * sumX2X2 + sumY * sumX1X2 * sumX2 + sumX2 * sumX1 * sumYX2 - sumX2 * sumYX1 * sumX2;
    beta2Delta = n * sumX1X1 * sumYX2 - n * sumYX1 * sumX1X2 - sumX1 * sumX1 * sumYX2 + sumX1 * sumYX1 * sumX2 + sumY * sumX1 * sumX1X2 - sumY * sumX1X1 * sumX2;
    
    
    beta0 = beta0Delta / systemDelta;
    beta1 = beta1Delta / systemDelta;
    beta2 = beta2Delta / systemDelta;
  }

  public void predict(double _x1, double _x2) {
    double prediction = beta0 + beta1 * _x1 + beta2 * _x2;

    System.out.print(String.format("Prediction for y: " + prediction));
  }

  public void printBetas() {
    System.out.print("Beta0 = " + beta0 + " | Beta1 = " + beta1 + " | Beta2 = " + beta2);
  }

  public void printEquation() {
    System.out.println("\n" + "MLR Ecuation: y = " + beta0 + " + " + beta1 + "(x1) + " + beta2 + "(x2)");
  }

  public int getDataLength() {
      return n;
  }
    
}