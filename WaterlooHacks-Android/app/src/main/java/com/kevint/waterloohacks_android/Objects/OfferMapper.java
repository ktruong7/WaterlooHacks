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

        // Put the minor IDs that you'll be using from the bluetooth beacon here
        int[] minorIDs = {
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9,
        };
                        // name, description, hours, Bitmap resource ID
        ofr = new Offer("Auto parts Sale","Nearby discount on auto parts",11, R.drawable.autoparts, context);
        offerMap.put(minorIDs[0], ofr);
        ofr = new Offer("Shoe Sale","Nearby discount on shoes",12,R.drawable.giuseppe, context);
        offerMap.put(minorIDs[1], ofr);
        ofr = new Offer("Guitar Sale","Nearby discount on guitars",13,R.drawable.bwguitar, context);
        offerMap.put(minorIDs[2], ofr);
        ofr = new Offer("Electronics Sale","Nearby discount on eletronics",14,R.drawable.circuitblue,context);
        offerMap.put(minorIDs[3], ofr);
        ofr = new Offer("Battery Sale","Nearby discount on batteries",23,R.drawable.duracell,context);
        offerMap.put(minorIDs[4], ofr);
        ofr = new Offer("Produce Sale","Nearby discount on fresh produce",32,R.drawable.fruits,context);
        offerMap.put(minorIDs[5], ofr);
        ofr = new Offer("Light Bulb Sale","Nearby discount on light bulbs",48,R.drawable.lightbulbs,context);
        offerMap.put(minorIDs[6], ofr);
        ofr = new Offer("Graphics Card Sale","Nearby discount on graphics cards",36,R.drawable.nvidia,context);
        offerMap.put(minorIDs[7], ofr);
        ofr = new Offer("Chocolate Sale","Nearby discount on chocolate",10,R.drawable.toblerone,context);
        offerMap.put(minorIDs[8], ofr);
        ofr = new Offer("Stationaries Sale","Nearby discount on stationaries",8,R.drawable.stationary,context);
        offerMap.put(minorIDs[9], ofr);
    }

    public Offer getOffer(int id){
        return offerMap.get(id);
    }
}
