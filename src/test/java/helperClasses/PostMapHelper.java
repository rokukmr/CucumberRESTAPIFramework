package helperClasses;

import AddPlaceDTO.PostMapDTO;
import AddPlaceDTO.PostMapLocationDTO;

import java.util.ArrayList;

public class PostMapHelper {
    public PostMapDTO setPostMap(String name, String language, String address){
        PostMapDTO pmDTO = new PostMapDTO();
        PostMapLocationDTO pmlDTO =  new PostMapLocationDTO();
        pmDTO.setAccuracy(50);
        pmDTO.setAddress(address);
        pmDTO.setLanguage(language);
        pmDTO.setPhone_number("7018139021");
        pmDTO.setWebsite("www.abc.com");
        pmDTO.setName(name);
        ArrayList<String> list = new ArrayList<String>();
        list.add("shoe");
        list.add("shoe park");
        pmDTO.setTypes(list);
        pmlDTO.setLat(-38.383494);
        pmlDTO.setLng(33.427558);
        pmDTO.setLocation(pmlDTO);
        return pmDTO;
    }
}
