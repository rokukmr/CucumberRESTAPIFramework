package stepDefinations;

import io.cucumber.java.Before;

import java.io.FileNotFoundException;

public class Hooks {
    StepDefination sdf = new StepDefination();
    @Before("@DeletePlace")
    public void beforeScenario() throws FileNotFoundException {
        //Executes this test hook only when we don't have Place id from Add a Api
        if(StepDefination.place_id == null){
        sdf.add_place_payload_with("Hook","Language-hook","address-hook");
        sdf.user_calls_with_request("AddPlaceAPI", "post");
        sdf.verify_place_id_created_maps_to_using("Hook","GetPlaceAPI");
        }
    }
}
