package LinearAlgebra;

import GenBody.SimulationVerlet;
import GenBody.StateOfSolarSystem;
import GenBody.Vector;
import interfaces.Vector3dInterface;
import static LinearAlgebra.AlgebraOperations.inverseMatrix;
import static LinearAlgebra.AlgebraOperations.mul;

public class NewtonRaphson {
    private SimulationVerlet sim = new SimulationVerlet();

    private Vector3dInterface[] positionOfSpacechip;

    private Vector finaltitan =
    new Vector(8.762645269228055E11+300000+2.575e6, -1.2032278963443552E12, -1.441830190223172E10);

    /**
     * @return the initial vector to get the spaceship to the finaltitan vector, within an accuracy of 100m
     */

    public Vector3dInterface findstartingVelocity(){

        Vector v0 = (Vector) new Vector(0.5896979523584819, -0.8075660030972522, -0.0096682793579059).mul(33441.856849216136);
       // Vector v0 = (Vector) new Vector(0.5896979523584819, -0.8075660030972522, -0.0096682793579059);

        positionOfSpacechip = sim.trajectory(new Vector(6371e3, 0, 0), v0, 31556926, 1000);

        Vector distanceVector = distanceVector(positionOfSpacechip);

        int iteration =1;

        while(distanceVector.norm()>100){

            v0 = step(v0,distanceVector);

            positionOfSpacechip = sim.trajectory(new Vector(6371e3, 0, 0), v0, 31556926, 1000);

            distanceVector = distanceVector(positionOfSpacechip);

            System.out.println("Distance to titan: "+distanceVector.norm()+ "on the "+iteration+" iteration.");

            iteration++;
        }

        return v0;
    }

    /** computes the distance vector from spaceship to the target
     *
     */
    private Vector distanceVector(Vector3dInterface[] positionOfSpacechip){

        Vector probe = (Vector) positionOfSpacechip[positionOfSpacechip.length-1];

        return (Vector) finaltitan.sub(probe);
    }

    /**
     *
     * @param v0 velocity vector
     * @param distanceVector vector from titan to spaceship
     * @return  starting vector after step
     */
    private Vector step(Vector v0, Vector distanceVector){

        double[][] derivativeMatrix = createMatrix(v0,distanceVector,0.01);

        Vector v1 = (Vector) v0.sub(mul(derivativeMatrix,distanceVector));

        return v1;
    }

    /**
     *
     * @param v0 vel vector
     * @param distanceVector Vector from spaceship to titan
     * @param h time-step
     * @return inverted Jacobian matrix
     */
    private double[][] createMatrix(Vector v0, Vector distanceVector, double h){

       double[][] jacobianMatrix = new double[3][3];

        Vector dv0x = new Vector(v0.getX()+h, v0.getY(), v0.getZ());
        Vector dv0y = new Vector(v0.getX(), v0.getY()+h, v0.getZ());
        Vector dv0z = new Vector(v0.getX(), v0.getY(), v0.getZ()+h);

        Vector3dInterface[] positionOfSpacechipX = sim.trajectory(new Vector(6371e3, 0, 0), dv0x, 31556926, 1000);
        Vector3dInterface[] positionOfSpacechipY = sim.trajectory(new Vector(6371e3, 0, 0), dv0y, 31556926, 1000);
        Vector3dInterface[] positionOfSpacechipZ = sim.trajectory(new Vector(6371e3, 0, 0), dv0z, 31556926, 1000);

        jacobianMatrix[0][0] = (distanceVector(positionOfSpacechipX).getX()-distanceVector.getX())/h;
        jacobianMatrix[0][1] = (distanceVector(positionOfSpacechipY).getX()-distanceVector.getX())/h;
        jacobianMatrix[0][2] = (distanceVector(positionOfSpacechipZ).getX()-distanceVector.getX())/h;

        jacobianMatrix[1][0] = (distanceVector(positionOfSpacechipX).getY()-distanceVector.getY())/h;
        jacobianMatrix[1][1] = (distanceVector(positionOfSpacechipY).getY()-distanceVector.getY())/h;
        jacobianMatrix[1][2] = (distanceVector(positionOfSpacechipZ).getY()-distanceVector.getY())/h;

        jacobianMatrix[2][0] = (distanceVector(positionOfSpacechipX).getZ()-distanceVector.getZ())/h;
        jacobianMatrix[2][1] = (distanceVector(positionOfSpacechipY).getZ()-distanceVector.getZ())/h;
        jacobianMatrix[2][2] = (distanceVector(positionOfSpacechipZ).getZ()-distanceVector.getZ())/h;

        return inverseMatrix(jacobianMatrix);
    }


}

