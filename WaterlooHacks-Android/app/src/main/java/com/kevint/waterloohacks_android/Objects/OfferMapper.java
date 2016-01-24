package com.kevint.waterloohacks_android.Objects;

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
        ofr = new Offer("name","desc",11,123456);
        offerMap.put(1000, ofr);
        ofr = new Offer("name","desc",12,123456);
        offerMap.put(1001, ofr);
        ofr = new Offer("name","desc",13,123456);
        offerMap.put(1002, ofr);
        ofr = new Offer("name","desc",14,123456);
        offerMap.put(1003, ofr);
        ofr = new Offer("name","desc",23,123456);
        offerMap.put(1004, ofr);
        ofr = new Offer("name","desc",32,123456);
        offerMap.put(1005, ofr);
        ofr = new Offer("name","desc",48,123456);
        offerMap.put(1006, ofr);
        ofr = new Offer("name","desc",36,123456);
        offerMap.put(1007, ofr);
        ofr = new Offer("name","desc",10,123456);
        offerMap.put(1008, ofr);
        ofr = new Offer("name","desc",8,123456);
        offerMap.put(1009, ofr);
    }

    public Offer getOffer(int id){
        return offerMap.get(id);
    }
}
