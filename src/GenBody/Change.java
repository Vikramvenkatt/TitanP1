package GenBody;

import interfaces.*;

public class Change implements RateInterface{

    public Vector3dInterface[] a = new Vector3dInterface[11];

    //order of planets is sun[0],venus[1],mercury[2], jupiter[3], mars[4],earth[5], uranus[6], saturn[7],titan[8],moon[9],neptune[10]

    public Change(Vector3dInterface[] a){
        for(int i =0; i < a.length; i++){
            this.a[i] = a[i];
        }
    }

    public Vector3dInterface[] getA(){

        Vector3dInterface[] newA = new Vector3dInterface[11];

        for(int i =0; i< a.length; i++){
            newA[i] = a[i];
        }

        return newA;
    }

    public Vector3dInterface getAShip(){
        return a[0];
    }


}
