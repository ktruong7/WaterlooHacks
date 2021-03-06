package com.kevint.waterloohacks_android.Objects;

import com.kevint.waterloohacks_android.R;

import java.util.HashMap;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by chungzheng on 1/23/2016.
 */

public class OfferMapper {
    HashMap<Integer,Offer> offerMap;
    Context context;
    public OfferMapper(Context con){
        offerMap = new HashMap<Integer,Offer>();
        context = con;
        Offer ofr;

        // Put the minor IDs that you'll be using from the bluetooth beacon here
        int[] minorIDs = {1,2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        // name, description, hours, Bitmap resource ID
        // NOTE: The Offers in the OfferMap don't contain an actual bitmap, only the ID of that bitmap
        ofr = new Offer("Auto parts Sale","Nearby discount on auto parts",11, R.drawable.autoparts,minorIDs[0]);
        offerMap.put(minorIDs[0], ofr);
        ofr = new Offer("Shoe Sale","Nearby discount on shoes",12,R.drawable.giuseppe,minorIDs[1]);
        offerMap.put(minorIDs[1], ofr);
        ofr = new Offer("Guitar Sale","Nearby discount on guitars",13,R.drawable.bwguitar,minorIDs[2]);
        offerMap.put(minorIDs[2], ofr);
        ofr = new Offer("Electronics Sale","Nearby discount on eletronics",14,R.drawable.circuitblue,minorIDs[3]);
        offerMap.put(minorIDs[3], ofr);
        ofr = new Offer("Battery Sale","Nearby discount on batteries",23,R.drawable.duracell,minorIDs[4]);
        offerMap.put(minorIDs[4], ofr);
        ofr = new Offer("Produce Sale","Nearby discount on fresh produce",32,R.drawable.fruits,minorIDs[5]);
        offerMap.put(minorIDs[5], ofr);
        ofr = new Offer("Light Bulb Sale","Nearby discount on light bulbs",48,R.drawable.lightbulbs,minorIDs[6]);
        offerMap.put(minorIDs[6], ofr);
        ofr = new Offer("Graphics Card Sale","Nearby discount on graphics cards",36,R.drawable.nvidia,minorIDs[7]);
        offerMap.put(minorIDs[7], ofr);
        ofr = new Offer("Chocolate Sale","Nearby discount on chocolate",10,R.drawable.toblerone,minorIDs[8]);
        offerMap.put(minorIDs[8], ofr);
        ofr = new Offer("Stationaries Sale","Nearby discount on stationaries",8,R.drawable.stationary, minorIDs[9]);
        offerMap.put(minorIDs[9], ofr);
        ofr = new Offer("Apple sale","75 cents for one apple",8,R.drawable.apples, minorIDs[10]);
        offerMap.put(minorIDs[10], ofr);
        ofr = new Offer("Fish sale","Buy two Tilapia fillets for $4",8,R.drawable.tilapia,minorIDs[11]);
        offerMap.put(minorIDs[11], ofr);
    }

    // Create and return a new Offer object that actually has a bitmap image
    public Offer getOffer(int id){
        if(offerMap.containsKey(id)) {
            Offer of = offerMap.get(id);
            return of;
        } else {
            return null;
        }
//        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), of.getOfferImageID());
//        return new Offer(of.getOfferName(),of.getOfferDescription(),of.getValidHours(),bmp);
    }
}
