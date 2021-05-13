package GenBody;

public class AddZoomFactor {
    public static void addFactor(){
        Planet.zoomFactor-=0.1;
        Planet.sizeFactor+=0.06;
    }

    public static void decreaseFactor(){
        Planet.zoomFactor+=0.1;
        Planet.sizeFactor-=0.06;
    }
}
