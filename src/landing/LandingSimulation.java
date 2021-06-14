package landing;

public class LandingSimulation {
    private StateOfModule stateOfModule;


    public static void main(String[] args) {
        StateOfModule stateOfModule = new StateOfModule();
        stateOfModule.updateModule();

        while(stateOfModule.hasModuleLanded() == false)
        {
            stateOfModule.updatePosition();
        }
    }


}
