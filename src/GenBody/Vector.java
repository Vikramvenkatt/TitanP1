package GenBody;

import interfaces.Vector3dInterface;

public class Vector implements Vector3dInterface {

	private double x;
	private double y;
	private double z;

	
	public Vector() {
		this.setX(0);
		this.setY(0);
		this.setZ(0);

	}

	
	public Vector(Vector v) {
		this.setX(v.getX());
		this.setY(v.getY());
		this.setZ(v.getZ());
	}

	public Vector(double x, double y, double z) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);

	}

	public Vector add(Vector v) {

		return new Vector(x + v.x, y + v.y, z + v.z);

	}

	public Vector substract(Vector v) {

		return new Vector(x - v.x, y - v.y, z - v.z);

	}

	public Vector multiply(double scalar) {

		return new Vector(x * scalar, y * scalar, z * scalar);

	}
	
	
	public double distance (Vector v ) {
		double distance = Math.sqrt(Math.pow(v.getX()-this.x, 2)+Math.pow(v.getY()-this.y, 2)+Math.pow(v.getZ()-this.z, 2));
		return distance;
	}
	
	
	public double length () {
		
		
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	
	
	
	public double norm () {
		return Math.sqrt(dot(this));
		
	}

	@Override
	public double dist(Vector3dInterface other) {
		return Math.sqrt(Math.pow(other.getX()-this.x, 2)+Math.pow(other.getY()-this.y, 2)+Math.pow(other.getZ()-this.z, 2));
	}


	public double getX() {

		return x;
	}
	
	public double dot(Vector v) {
		return x * v.getX() + y * v.getY() + z * v.getZ();
	}
	
	public void setX(double x) {

		this.x = x;

	}

	public double getY() {

		return y;
	}

	public void setY(double y) {

		this.y = y;

	}

	public double getZ() {

		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	@Override
	public Vector3dInterface add(Vector3dInterface other) {
		return new Vector(x + other.getX(), y + other.getY(), z + other.getZ());
	}

	@Override
	public Vector3dInterface sub(Vector3dInterface other) {
		return new Vector(x - other.getX(), y - other.getY(), z - other.getZ());
	}

	@Override
	public Vector3dInterface mul(double scalar) {
		return new Vector(getX() * scalar, getY() * scalar, getZ() * scalar);
	}

	@Override
	public Vector3dInterface addMul(double scalar, Vector3dInterface other) {
		return new Vector(this.getX()+(scalar*other.getX()),this.getY()+(scalar*other.getY()),this.getZ()+(scalar*other.getZ()));
	}

	public String toString() {
		return "( "+x + ", " + y + ", " + z+" )";
	}
}
