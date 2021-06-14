package LinearAlgebra;

import GenBody.Vector;
import interfaces.Vector3dInterface;

import java.util.Arrays;

public class AlgebraOperations {

    /**
     *
     *
     * @param matrix 3*3
     * @return the inverse of matrix
     */
    public static double[][] inverseMatrix(double[][] matrix){
        double det = det(matrix);
        if (det==0)
            throw new RuntimeException("det = 0 therefore not inversible");
        double[][] adjoint = adjoint(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                adjoint[i][j] = adjoint[i][j]*1/det;
            }
        }
        return adjoint;
    }

    /**
     * https://www.geeksforgeeks.org/determinant-of-a-matrix/
     * @param m 3*3 matrix
     * @return the determinant of the 3*3 matrix
     */
    private static double det(double[][] m){
        if (m.length !=3)
            throw new RuntimeException("only 3*3 matrix");
        return m[0][0]*((m[1][1]*m[2][2])-(m[1][2]*m[2][1])) - m[0][1]*((m[1][0]*m[2][2])-(m[2][0]*m[1][2])) + m[0][2]*((m[1][0]*m[2][1])-(m[1][1]*m[2][0]));
    }

    /**
     * Computes the adjoint matrix of a 3*3 matrix, to inverse a matrix.
     *
     * @param mat 3*3 matrix
     * @return the adjoint matrix of the 3*3 matrix
     */
    private static double[][] adjoint(double[][] mat){
        double[][] newMatrix = new double[3][3];

            	newMatrix[0][0] = (((mat[1][1])*(mat[2][2]))-((mat[1][2])*(mat[2][1])));
            	newMatrix[1][0] = (-1)*(((mat[1][0])*(mat[2][2]))-((mat[1][2])*(mat[2][0])));
            	newMatrix[2][0] = (((mat[1][0])*(mat[2][1]))-((mat[1][1])*(mat[2][0])));
            	newMatrix[0][1] = (-1)*(((mat[0][1])*(mat[2][2]))-((mat[0][2])*(mat[2][1])));
            	newMatrix[1][1] = (((mat[0][0])*(mat[2][2]))-((mat[0][2])*(mat[2][0])));
            	newMatrix[2][1] = (-1)*(((mat[0][0])*(mat[2][1]))-((mat[0][1])*(mat[2][0])));
            	newMatrix[0][2] = (((mat[0][1])*(mat[1][2]))-((mat[0][2])*(mat[1][1])));
            	newMatrix[1][2] = (-1)*(((mat[0][0])*(mat[1][2]))-((mat[0][2])*(mat[1][0])));
            	newMatrix[2][2] = (((mat[0][0])*(mat[1][1]))-((mat[0][1])*(mat[1][0])));

        return newMatrix;
    }

    public static Vector3dInterface mul(double [][] m, Vector3dInterface v){
        double x = m[0][0]*v.getX()+m[0][1]*v.getY()+m[0][2]*v.getZ();
        double y = m[1][0]*v.getX()+m[1][1]*v.getY()+m[1][2]*v.getZ();
        double z = m[2][0]*v.getX()+m[2][1]*v.getY()+m[2][2]*v.getZ();
        return new Vector(x,y,z);
    }

    public static void main(String[] args) {


        long start = System.nanoTime();
        NewtonRaphson Newton = new NewtonRaphson();
        System.out.println("Starting Velocity: "+Newton.findstartingVelocity().toString());
        long end = System.nanoTime();
        long exec = end - start;
        double inSeconds = (double)exec / 1_000_000_000.0;
        System.out.println("The program takes "+exec+" nanoseconds that is "+inSeconds+" seconds to execute.");

    }
}
