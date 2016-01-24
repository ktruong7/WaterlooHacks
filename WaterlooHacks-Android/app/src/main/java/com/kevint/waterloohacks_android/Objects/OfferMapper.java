package com.kevint.waterloohacks_android.Objects;

import com.kevint.waterloohacks_android.R;

import java.util.HashMap;
/**
 * Created by chungzheng on 1/23/2016.
 */

public class OfferMapper {
    HashMap<Integer,Offer> offerMap;

    public OfferMapper(){
        offerMap = new HashMap<Integer,Offer>();
        Offer ofr;
                        // name, description, hours, Bitmap resource ID
        ofr = new Offer("Auto parts Sale","desc",11, R.drawable.autoparts);
        offerMap.put(1000, ofr);
        ofr = new Offer("Shoe Sale","desc",12,R.drawable.giuseppe);
        offerMap.put(1001, ofr);
        ofr = new Offer("Guitar Sale","desc",13,R.drawable.bwguitar);
        offerMap.put(1002, ofr);
        ofr = new Offer("Electronics Sale","desc",14,R.drawable.circuitblue);
        offerMap.put(1003, ofr);
        ofr = new Offer("Battery Sale","desc",23,R.drawable.duracell);
        offerMap.put(1004, ofr);
        ofr = new Offer("Produce Sale","desc",32,R.drawable.fruits_in_gardens);
        offerMap.put(1005, ofr);
        ofr = new Offer("Light Bulb Sale","desc",48,R.drawable.lightbulbs);
        offerMap.put(1006, ofr);
        ofr = new Offer("Graphics Card Sale","desc",36,R.drawable.nvidia);
        offerMap.put(1007, ofr);
        ofr = new Offer("Chocolate Sale","desc",10,R.drawable.toblerone);
        offerMap.put(1008, ofr);
        ofr = new Offer("Staionaries Sale","desc",8,R.drawable.stationary);
        offerMap.put(1009, ofr);
    }

    public Offer getOffer(int id){
        return offerMap.get(id);
    }
}
