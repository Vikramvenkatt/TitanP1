package test;


import static org.junit.jupiter.api.Assertions.*;

import GenBody.Vector;
import org.junit.jupiter.api.Test;

public class TestVector {
    Vector a = new Vector(2, 5, -4);
    Vector b = new Vector(-2, -3, -5);

    // Eucl norm test (Vector length) test
    @Test
    void testEuclNorm() {
        assertEquals(6.708203932499369, a.norm());
    }

    // Add two vectors
    @Test
    void testAddVector() {
        Vector q = a.add(b);
        assertEquals(0, q.getX());
        assertEquals(2, q.getY());
        assertEquals(-9, q.getZ());
    }

    // Subtract two vectors
    @Test
    void testSubtractVector() {
        Vector q = (Vector) a.sub(b);
        assertEquals(4, q.getX());
        assertEquals(8, q.getY());
        assertEquals(1, q.getZ());
    }

    // Dot product test
   /* void testDotProduct()
    {
        assertEquals(1,a.dotProduct(b));
    }

    // Unit vector test
    void testUnitVector()
    {
        Vector3d q = a.unitVector();
        assertEquals(0.29814239699997197, q.getX());
        assertEquals(0.7453559924999299, q.getY());
        assertEquals(-0.5962847939999439, q.getZ());
    }
    */


    // Scalar multiple test
    @Test
    void testScalarMultiple() {
        Vector q = (Vector) a.mul(5);
        assertEquals(10, q.getX());
        assertEquals(25, q.getY());
        assertEquals(-20, q.getZ());
    }

    // Distance between two vectors test
    @Test
    void testDistance() {
        assertEquals(9, a.dist(b));
    }

    // Add a multiple of another vector test
    @Test
    void testAddMul() {
        Vector q = (Vector) a.addMul(5, b);
        assertEquals(8, q.getX());
        assertEquals(22, q.getY());
        assertEquals(-25, q.getZ());
    }



}






