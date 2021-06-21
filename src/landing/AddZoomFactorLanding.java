package landing;

import GenBody.Planet;

public class AddZoomFactorLanding {
    public static void addFactorLanding(){
        LandingEnvironment.zoomFactorLanding-=0.1;
        LandingEnvironment.sizeFactorLanding+=0.06;
    }

    public static void decreaseFactorLanding(){
        LandingEnvironment.zoomFactorLanding+=0.1;
        LandingEnvironment.sizeFactorLanding-=0.06;
    }
}
