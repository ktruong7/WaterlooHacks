package com.kevint.waterloohacks_android.Objects;

import com.kevint.waterloohacks_android.R;

import java.util.HashMap;
import android.content.Context;
/**
 * Created by chungzheng on 1/23/2016.
 */

public class OfferMapper {
    HashMap<Integer,Offer> offerMap;

    public OfferMapper(Context context){
        offerMap = new HashMap<Integer,Offer>();
        Offer ofr;
                        // name, description, hours, Bitmap resource ID
        ofr = new Offer("Auto parts Sale","Nearby discount on auto parts",11, R.drawable.autoparts, context);
        offerMap.put(1000, ofr);
        ofr = new Offer("Shoe Sale","Nearby discount on shoes",12,R.drawable.giuseppe, context);
        offerMap.put(1001, ofr);
        ofr = new Offer("Guitar Sale","Nearby discount on guitars",13,R.drawable.bwguitar, context);
        offerMap.put(1002, ofr);
        ofr = new Offer("Electronics Sale","Nearby discount on eletronics",14,R.drawable.circuitblue,context);
        offerMap.put(1003, ofr);
        ofr = new Offer("Battery Sale","Nearby discount on batteries",23,R.drawable.duracell,context);
        offerMap.put(1004, ofr);
        ofr = new Offer("Produce Sale","Nearby discount on fresh produce",32,R.drawable.fruits_in_gardens,context);
        offerMap.put(1005, ofr);
        ofr = new Offer("Light Bulb Sale","Nearby discount on light bulbs",48,R.drawable.lightbulbs,context);
        offerMap.put(1006, ofr);
        ofr = new Offer("Graphics Card Sale","Nearby discount on graphics cards",36,R.drawable.nvidia,context);
        offerMap.put(1007, ofr);
        ofr = new Offer("Chocolate Sale","Nearby discount on chocolate",10,R.drawable.toblerone,context);
        offerMap.put(1008, ofr);
        ofr = new Offer("Stationaries Sale","Nearby discount on stationaries",8,R.drawable.stationary,context);
        offerMap.put(1009, ofr);
    }

    public Offer getOffer(int id){
        return offerMap.get(id);
    }
}
