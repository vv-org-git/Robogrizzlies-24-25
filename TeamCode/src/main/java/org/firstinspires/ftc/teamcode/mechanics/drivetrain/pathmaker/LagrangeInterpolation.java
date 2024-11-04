package org.firstinspires.ftc.teamcode.mechanics.drivetrain.pathmaker;

public class LagrangeInterpolation {

    // Method to compute the Lagrange polynomial

    public static double[] lagrangeCoefficients(double[] x, double[] y) {
        int n = x.length;
        double[] coefficients = new double[n];

        // Loop through each point to calculate Lagrange basis polynomial
        for (int i = 0; i < n; i++) {
            // Initialize temporary coefficients for the i-th basis polynomial
            double[] tempCoefficients = new double[n];
            tempCoefficients[0] = 1; // Start with 1

            // Construct the i-th Lagrange basis polynomial
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    // Create a new polynomial to include the j-th term
                    double[] newCoefficients = new double[n];
                    double factor = (1.0 / (x[i] - x[j]));

                    // Multiply the existing polynomial by (x - x[j])
                    for (int k = 0; k < n; k++) {
                        newCoefficients[k] += tempCoefficients[k] * -x[j] * factor;
                    }

                    for (int k = 0; k < n - 1; k++) {
                        newCoefficients[k + 1] += tempCoefficients[k] * factor;
                    }

                    tempCoefficients = newCoefficients;
                }
            }

            // Add the contribution of the y[i] to the coefficients
            for (int k = 0; k < n; k++) {
                coefficients[k] += y[i] * tempCoefficients[k];
            }
        }

        return coefficients;
    }

}
