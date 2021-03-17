package GenBody;

import interfaces.Vector3dInterface;

public class Spaceship extends Body{
    //create simple spaceship with no mass, only vectors
    public Spaceship(Vector position, Vector3dInterface velocity) {
        super(position, (Vector) velocity);
    }
}
