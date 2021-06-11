package GenBody;

import interfaces.RateInterface;

/**
 *created for showing time derivative of the state class
 *feeder class to state, state interface
 */
public class Rate implements RateInterface {

    private Vector[] velocity;
    private Vector[] acceleration;

    public Rate(Vector[] velocity, Vector[] accel) {

        this.velocity = new Vector[velocity.length];
        this.acceleration = new Vector[accel.length];
        System.arraycopy(velocity, 0, this.velocity, 0, velocity.length);
        System.arraycopy(accel, 0, this.acceleration, 0, accel.length);
    }


    public Vector[] getVelocity() {

        return velocity;
    }


    public void setVelocity(Vector[] veloCity) {

        System.arraycopy(veloCity, 0, this.velocity, 0, veloCity.length);
    }


    public Vector[] getAcceleration() {

        return acceleration;
    }


    public void setAcceleration(Vector[] acceleRation) {

        System.arraycopy(acceleRation, 0, this.acceleration, 0, acceleRation.length);
    }


    public Rate mul(double scalar) {

        Rate newRate = new Rate(new Vector[velocity.length], new Vector[acceleration.length]);
        newRate.setVelocity(velocity);
        newRate.setAcceleration(acceleration);

        for (int i = 0; i < acceleration.length; i++) {

            Vector newVelo = (Vector) newRate.getVelocity()[i].mul(scalar);
            Vector newAcce = (Vector) newRate.getAcceleration()[i].mul(scalar);
            newRate.getVelocity()[i] = newVelo;
            newRate.getAcceleration()[i] = newAcce;
        }
        return newRate;
    }


    public Rate addMul(double scalar, Rate vector) {

        Vector newVelocity[] = new Vector[this.velocity.length];
        Vector newAcce[] = new Vector[this.acceleration.length];

        for (int i = 0; i < acceleration.length; i++) {

            newVelocity[i] = (Vector) this.velocity[i].addMul(scalar, vector.getVelocity()[i]);
            newAcce[i] = (Vector) this.acceleration[i].addMul(scalar, vector.getAcceleration()[i]);
        }
        Rate newRate = new Rate(newVelocity, newAcce);

        return newRate;
    }
}